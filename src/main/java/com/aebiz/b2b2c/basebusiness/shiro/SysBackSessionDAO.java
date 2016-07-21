package com.aebiz.b2b2c.basebusiness.shiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danga.MemCached.MemCachedClient;

@Service
public class SysBackSessionDAO extends AbstractSessionDAO {
	private static final int EXPIRE_TIMES = 1 * 60;
	@Autowired
	private MemCachedClient mcc;

	public void update(Session session) throws UnknownSessionException {
		mcc.set(session.getId().toString(), session, new Date(EXPIRE_TIMES));
	}

	public void delete(Session session) {
		mcc.delete(session.getId().toString());
	}

	public Collection<Session> getActiveSessions() {
		return new ArrayList<Session>();
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);

		mcc.add(sessionId.toString(), session, new Date(EXPIRE_TIMES));

		return sessionId;

	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
//		System.out.println("get sessionId===" + sessionId);
		Session s = (Session) mcc.get(sessionId.toString());
		// 同时更新时间
		mcc.set(sessionId.toString(), s, new Date(EXPIRE_TIMES));
		return s;
	}
}
