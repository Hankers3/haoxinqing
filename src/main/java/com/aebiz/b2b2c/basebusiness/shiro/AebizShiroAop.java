package com.aebiz.b2b2c.basebusiness.shiro;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import bsh.Interpreter;

import com.aebiz.b2b2c.baseframework.exception.AppException;

/**
 * 使用权限过滤功能的AOP，用来判断是否能调用某个功能方法
 * 
 * @author cc
 * 
 */
@Aspect
public class AebizShiroAop {
	/**
	 * 需要进行权限过滤的配置，通过spring来设置
	 */
	private List<String> list = new ArrayList<String>();
	/**
	 * 自定义的权限表达式中，permit的开头字符串
	 */
	private final String PERMIT_PRE = "P{";
	/**
	 * 自定义的权限表达式中，role的开头字符串
	 */
	private final String ROLE_PRE = "R{";
	/**
	 * 自定义的权限表达式中，单个表达式结尾的字符串
	 */
	private final String STR_END = "}";

	public void setList(List<String> List) {
		this.list = List;
	}

	/**
	 * 根据xml中配置，进行权限判断，以决定是否能调用相应的功能
	 * 
	 * @param jp
	 */
	@Before("execution(* com.aebiz..*.*(..))")
	public void hasPermitsXML(JoinPoint jp) {
		// PointcutParser pp =
		// PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(
		// new HashSet(),this.getClass().getClassLoader()
		// );
		// PointcutExpression pe =
		// pp.parsePointcutExpression("(* com.aebiz..*.*(..))");
		// boolean f =
		// pe.matchesAdviceExecution(jp.getTarget().getClass().getMethod(jp.getSignature().getName()
		// ,((MethodSignature)jp.getSignature()).getMethod().getParameterTypes())).maybeMatches();
		//
		// System.out.println("f==="+f);

		// System.out.println("target==="+jp.getTarget().getClass().equals(Class.forName("com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.service.impl.SysManagerServiceImpl")));
		// System.out.println("kind=="+jp.getKind());
		// System.out.println("longstring=="+jp.toLongString());
		// System.out.println("shrotstring=="+jp.toShortString());
		// System.out.println("class==="+jp.getClass());
		// System.out.println("signa=="+jp.getSignature());
		// System.out.println("SourceLocation=="+jp.getSourceLocation());
		System.out.println(jp.getSignature().toLongString()
				+ " ,  hasPermitsXML===" + list);
		String str = needExecuteExpr(jp);
		if (str != null && str.trim().length() > 0) {
			String expr = this.parseExpr(str);
			boolean f = executeExpr(expr);
			System.out.println("str===" + str);
			System.out.println("expr===" + expr);
			System.out.println("f===" + f);
			if (!f) {
				throw new AppException("user.no.permit");
			}
		}
	}

	/**
	 * 获取该切入点的方法，是否需要进行权限判断
	 * 
	 * @param jp
	 *            切入点
	 * @return 如果需要进行判断，返回在xml配置中相应的配置数据
	 */
	private String needExecuteExpr(JoinPoint jp) {
		for (String str : list) {
			String[] ss = str.split("#");
			if (jp.getSignature().toLongString().contains(ss[0].trim())) {
				return ss[1].trim();
			}
		}
		return "";
	}

	/**
	 * 按照自定义权限注解来执行权限判断，以判断是否能调用某个功能方法
	 * 
	 * @param jp
	 */
	@Before("execution(@com.aebiz.b2b2c.basebusiness.shiro.AebizAuthenticationAnno * *(..))")
	public void hasPermitsAnno(JoinPoint jp) {
		Method mth = this.getNowMethod(jp);

		System.out.println(jp.getSignature().toLongString()
				+ " ,  annotation===");

		Subject currentUser = SecurityUtils.getSubject();

		if (mth != null) {
			String permits = ((AebizAuthenticationAnno) mth
					.getAnnotation(AebizAuthenticationAnno.class)).expression();

			System.out.println("annotationpermits===" + permits);

			String expr = this.parseExpr(permits);
			boolean f = executeExpr(expr);
			// System.out.println("permits==="+permits);
			// System.out.println("expr==="+expr);
			// System.out.println("f==="+f);
			if (!f) {
				throw new AppException("user.no.permit");
			}
		}

	}

	// @AebizHasPermitsAnno(permits="(P{sysback:sysmanager:ok} && P{sysback:menu:view}) || R{sys1}")
	/**
	 * 执行表达式，使用beanshell，这里的表达式已经都被替换成true和false了 也就是执行一个boolean表达式，完全按照Java的语法
	 * 
	 * @param expr
	 *            符合java语法的boolean表达式
	 * @return 执行的结果
	 */
	private boolean executeExpr(String expr) {
		Interpreter bsh = new Interpreter();
		try {
			Object obj = bsh.eval("return " + expr + ";");
			return Boolean.valueOf(obj.toString());
		} catch (Exception err) {
			err.printStackTrace();
		}
		return false;
	}

	/**
	 * 解析自定义的权限表达式
	 * 
	 * @param permits
	 *            需要进行处理的自定义的权限表达式
	 * @return 把权限表达式的各个部分进行判断处理，然后替换回到表达式中，形成一个boolean表达式
	 */
	private String parseExpr(String permits) {

		// 如果不包含"P{" 且不包含 "R{"
		// 例如 (P{sysback:sysmanager:ok} &amp;&amp; P{sysback:menu:view}) ||
		// R{sys1}
		if (!permits.contains(PERMIT_PRE) && !permits.contains(ROLE_PRE)) {
			return permits;
		}
		int siteP = permits.indexOf(PERMIT_PRE);
		int siteR = permits.indexOf(ROLE_PRE);

		Subject currentUser = SecurityUtils.getSubject();

		if (siteP >= 0 && siteP < siteR) {

			// 处理Permit
			int siteP2 = permits.indexOf(STR_END, siteP + PERMIT_PRE.length()
					+ 1);
			String tempP = permits.substring(siteP + PERMIT_PRE.length(),
					siteP2);
			boolean f = currentUser.isPermitted(tempP);

			// 替换回permits
			permits = permits.substring(0, siteP) + f
					+ permits.substring(siteP2 + 1);
		} else {
			// 处理Role
			int siteR2 = permits
					.indexOf(STR_END, siteR + ROLE_PRE.length() + 1);
			String tempR = permits.substring(siteR + ROLE_PRE.length(), siteR2);
			boolean f = currentUser.hasRole(tempR);

			// 替换回permits
			permits = permits.substring(0, siteR) + f
					+ permits.substring(siteR2 + 1);
		}
		// 然后递归
		return parseExpr(permits);
	}

	/**
	 * 获得当前正在调用的方法，是否能满足权限判断条件，如果满足，返回这个方法
	 * 
	 * @param jp
	 *            切入点
	 * @return 满足条件的方法
	 */
	private Method getNowMethod(JoinPoint jp) {
		Method ms[] = jp.getTarget().getClass().getMethods();
		Method mth = null;
		for (Method m : ms) {
			if (m.getName().equals(jp.getSignature().getName())) {
				mth = m;
			}
		}
		return mth;
	}

	/**
	 * 参数类型检测，判断该方法是否跟要求的参数类型一致
	 * 
	 * @param m
	 *            要判断的方法
	 * @param ps
	 *            参数类型列表
	 * @return true为一致
	 */
	private boolean paramTypeCheck(Method m, Object[] ps) {
		Type ts[] = m.getParameterTypes();

		for (int i = 0; i < ts.length; i++) {
			if (!ts[i].equals(ps[i].getClass())) {
				return false;
			}
		}
		return true;
	}

}