package com.aebiz.b2b2c.baseframework.moniter.mysql;

import com.aebiz.b2b2c.baseframework.moniter.mysql.vo.MySQLModel;

import java.sql.*;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MySQLMonitor {

    public MySQLMonitor() {
        ds = null;
    }

    public MySQLModel getMySQLModel() {
        MySQLModel m = new MySQLModel();
        m.setThreadsConnected(calc("show status like 'Threads_connected'"));
        m.setMaxUsedConnections(calc("show status like 'Max_used_connections'"));
        m.setQcacheHits(calc("show status like 'Qcache_hits'"));
        m.setQuestions(calc("show status like 'Questions'"));
        m.setSelectScan(calc("show status like 'Select_scan'"));
        m.setSlowQueries(calc("show status like 'Slow_queries'"));
        m.setTableLocksWaited(calc("show status like 'Table_locks_waited'"));
        m.setTableLocksImmediate(calc("show status like 'Table_locks_immediate'"));
        int qcacheInserts = calc("show status like 'Qcache_inserts'");
        if(m.getQcacheHits() > 0)
            m.setFitCacheRate((1.0D * (double)(m.getQcacheHits() - qcacheInserts)) / (double)m.getQcacheHits());
        int handler_read_rnd_next = calc("show status like 'Handler_read_rnd_next'");
        int com_select = calc("show status like 'Com_select'");
        m.setTableScanRate((1.0D * (double)handler_read_rnd_next) / (double)com_select);
        m.setLockWaitRate((1.0D * (double)m.getTableLocksWaited()) / (double)(m.getTableLocksImmediate() + m.getTableLocksWaited()));
        return m;
    }

    private int calc(String sql) {
        int v = 0;
    	Connection conn = null;
        ResultSet rs = null;
    	PreparedStatement pstmt = null;
    	try {
    		conn = ds.getConnection();
    		pstmt = conn.prepareStatement(sql);
    		rs = pstmt.executeQuery();
    		if(rs.next())
    			v = rs.getInt(2);
    	} catch (Exception e) {
    		//
    	} finally {
    		try {
    			if(conn != null) conn.close();
    		} catch (Exception e) {}
    	}
        return v;
    }

    public static void main(String args[])
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        MySQLMonitor t = (MySQLMonitor)ctx.getBean("mySQLMonitor");
        System.out.println(t.getMySQLModel());
    }

    @Resource(name = "dataSource")
    private DataSource ds;
}