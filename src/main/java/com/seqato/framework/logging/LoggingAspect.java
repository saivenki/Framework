package com.seqato.framework.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

/*	@Before("execution(public * *Controller*(..))")
	public void loggingAdvice(){
		System.out.println("Get Method called...");
	}*/
	
	@Pointcut("within(com.beachpartner.*.controller.*)")
	public void allControllerMethods(){}

	@Pointcut("within(com.beachpartner.*.service.*)")
	public void allServiceMethods(){}
	
/*	@Before("args(id) && allControllerMethods()")
	public void methodWithOneArg(Long id){
		System.out.println("Before Executing  Method with One Long Argument "+id);
	}
	*/
	@Before("allControllerMethods()")
	public void beforeControllerMethods(JoinPoint joinPoint){
		System.out.println("\n\n\n\n\nBefore Executing Controller Method "+joinPoint.getSignature());
		beforeExecutingMethods(joinPoint);
	}
	
	@Before("allServiceMethods()")
	public void beforeServicesMethods(JoinPoint joinPoint){
		System.out.println("\n\nBefore Executing Service Method "+joinPoint.getSignature());
		beforeExecutingMethods(joinPoint);
	}
	
	private void beforeExecutingMethods(JoinPoint joinPoint){
		Object[] args = joinPoint.getArgs();
		if (args != null  && args.length > 0) {
			int len = args.length;
			int index = 0;
			while (index < len){
				System.out.print(" Arg["+index+"] ==");
				if (args[index] != null){
					System.out.print(args[index].toString());
				}
				index++;
			}
			System.out.println(" ");
		}
	}
	
	@AfterReturning(pointcut="allControllerMethods()",returning="returnValue")
	public void afterControllerMethods(JoinPoint joinPoint, Object returnValue){
		System.out.println("\n\n\n\n\nAfter Executing Controller Method "+joinPoint.getSignature()+"\n Return Value == ");
		if(returnValue != null){
			System.out.println(returnValue.toString());
		}
		
	}
	
	@AfterReturning(pointcut="allServiceMethods()",returning="returnValue")
	public void afterServiceMethods(JoinPoint joinPoint, Object returnValue){
		System.out.println("\nAfter Executing Service Method "+joinPoint.getSignature()+"\n Return Value == ");
		if(returnValue != null){
			System.out.println(returnValue.toString());
		}
	}
	
	
}
