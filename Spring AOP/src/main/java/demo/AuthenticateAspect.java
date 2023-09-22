package demo;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthenticateAspect {
    /*Execution define for which particular method we want to execute
    Within for which particular type of class we want to execute
    for all the particular methods*/
    @Pointcut("within(demo..*)")
    public void authenticatingPointCut(){

    }
    @Pointcut("within(demo..*)")
    public void autherizationPointCut(){

    }
    /*call this method with our above two defined point cuts*/
    @Before("authenticatingPointCut() && autherizationPointCut()")
    public void authenticate(){
        System.out.println("Authenticating the request");
    }

}
