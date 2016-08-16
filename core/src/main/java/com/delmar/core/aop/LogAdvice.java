/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
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
			StringBuilder sb=new StringBuilder();
			sb.append(joinPoint.getTarget().getClass().getName());
			sb.append("  ");
			sb.append(joinPoint.getSignature());
			dmLog.error(sb.toString(), ex);
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
			StringBuilder sb=new StringBuilder();
			sb.append(joinPoint.getTarget().getClass().getName());
			sb.append("  ");
			sb.append(joinPoint.getSignature());
			dmLog.error(sb.toString(), ex);
		
		}
	 

}
