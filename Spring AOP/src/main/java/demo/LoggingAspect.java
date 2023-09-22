package demo;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    /*How will the system know that is a aspect ?
    thus we add point cuts here logger method is a point cut
    *what is something that we want to call and where we want to call it*
    */
    /*we want to call logger method before checkout method is called
    thus we use before Annotation
     * -> indicates return type */
    /*Configure it in BeanConfig also make LoggingAspect a Component*/
    @Before("execution(* demo.ShoppingCart.checkout(..))")
    /*JoinPoint is used to fetch info about argument [status]*/
    public void beforeLogger(JoinPoint jp){
//        System.out.println(jp.getSignature());
        String arg = jp.getArgs()[0].toString();
        System.out.println("Before Loggers with Argument " + arg);
    }

    @After("execution(* *.*.checkout(..))")
    public void afterLogger(){
        System.out.println("After Loggers");
    }

    /*This Pointcut will be executed at quantity method of shopping cart
    and below is the execution for that*/
    @Pointcut("execution(* demo.ShoppingCart.quantity())")
    public void afterReturningPointCut(){}
    /*AfterReturning whatever has been returned from quantity method
    afterReturningPointCut is called
    and returning value should be passed as a argument in retVal*/
    @AfterReturning(pointcut = "afterReturningPointCut()", returning = "retVal")
    public void afterReturning(String retVal){
        System.out.println("After Returning " + retVal);
    }
}
