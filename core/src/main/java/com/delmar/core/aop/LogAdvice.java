/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.delmar.utils.DmLog;

/**
 * @author 刘大磊 2015年7月28日 上午8:06:13
 */
@Aspect
@Component
public class LogAdvice {
	/**
	 * 业务层异常捕获
	 * @param joinPoint
	 * @param ex
	 */
	 @AfterThrowing(throwing="ex",pointcut="execution(* com.delmar..*Service*.*(..))")
	public void logServiceException(JoinPoint joinPoint,Throwable ex)
	{
		
		 DmLog dmLog=DmLog.getLogger(joinPoint.getTarget().getClass());
		String sb = joinPoint.getTarget().getClass().getName() +
				"  " +
				joinPoint.getSignature();
		dmLog.error(sb, ex);
	}
	 /**
	  * 数据交互层异常捕获
	  * @param joinPoint
	  * @param ex
	  */
	 @AfterThrowing(throwing="ex",pointcut="execution(* com.delmar..*Dao*.*(..))")
	 public void logDaoException(JoinPoint joinPoint,Throwable ex)
		{
		 	DmLog dmLog=DmLog.getLogger("SQLLogger."+joinPoint.getTarget().getClass());
			String sb = joinPoint.getTarget().getClass().getName() +
					"  " +
					joinPoint.getSignature();
			dmLog.error(sb, ex);
		
		}
	 

}
