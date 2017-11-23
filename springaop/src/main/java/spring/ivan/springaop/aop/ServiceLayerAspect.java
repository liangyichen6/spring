package spring.ivan.springaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Service Layer Aspect
 * 
 * @author liangyi
 * @Date 2017年11月23日
 */
@Component
@Scope("singleton")
@Aspect
public class ServiceLayerAspect {

	/**
	 * 切点
	 */
	@Pointcut("execution(public * spring.ivan.springaop.service.*.*(..))")
	public void executionService() {

	}

	/**
	 * 连接点
	 * 
	 * @param joinPoint
	 */
	@Before(value = "executionService()")
	public void doBeforeAdvice(JoinPoint joinPoint) {
		System.out.println(String.format("Starting service method %s",
				joinPoint.getSignature().getName()));
	}

}
