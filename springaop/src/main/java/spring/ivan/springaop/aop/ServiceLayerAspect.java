package spring.ivan.springaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import spring.ivan.springaop.bean.User;

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
	 * 连接点 前置通知
	 * 
	 * @param joinPoint
	 * @throws Exception
	 */
	@Before(value = "executionService()")
	public void doBeforeAdvice(JoinPoint joinPoint) throws Exception {

		System.out.println(String.format("Starting service method %s %s",
				joinPoint.getSignature().getName(), joinPoint.getClass()));
		// throw new Exception();
	}

	/**
	 * 后置通知
	 * 
	 * @param joinPoint
	 */
	@After(value = "executionService()")
	public void doAfterAdvice(JoinPoint joinPoint) {
		System.out.println(String.format("Ended service method %s %s",
				joinPoint.getClass(), joinPoint.getKind()));

	}

	/**
	 * 返回通知
	 * 
	 * @param result
	 */
	@AfterReturning(
			value = "execution(spring.ivan.springaop.bean.User spring.ivan.springaop.service.UserServiceImpl.login(..))",
			returning = "result")
	public void doAfterReturning(JoinPoint joinPoint, Object result) {
		System.out.println(joinPoint.getSignature().getClass());
		System.out.println(joinPoint.getSignature().getDeclaringTypeName());
		User u = (User) result;
		System.out.println(u);
	}

	/**
	 * 异常通知
	 * 
	 * @param ex
	 */
	@AfterThrowing(
			value = "execution(void spring.ivan.springaop.service.UserServiceImpl.updateUser(..)))",
			throwing = "ex")
	public void doAfterThrowing(Throwable ex) {
		System.out.println("AfterThrowing starting...");
		System.out.println(ex.getMessage());
	}

	/**
	 * 环绕通知 <br>
	 * 可以决定是否调用目标方法<br>
	 * 可以放回自定义的result<br>
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around(value = "execution(* spring.ivan.springaop.controller.*.*(..))")
	public Object doAround(ProceedingJoinPoint pjp) {
		System.out.println(String.format("Starting around method: {}",
				pjp.getSignature().getName()));

		Object obj = null;
		try {
			obj = pjp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		User u = new User();
		u.setFirstName("Emon");
		u.setLastName("Huang");
		System.out.println("Ending around....");
		return ResponseEntity.ok().body(u);

	}

	/**
	 * 环绕通知可以
	 * 
	 * @param pjp
	 */
	@Around(value = "execution(* spring.ivan.springaop.service.UserServiceImpl.deleteUser(..))")
	public void doAroundDeleteUser(ProceedingJoinPoint pjp) {
		try {
			pjp.proceed(new Object[] { 12l });
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
