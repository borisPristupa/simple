package com.boris.simple.springaop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.util.Arrays;

@Aspect
@Component
public class LoggerAspect {
    private Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @Pointcut("@annotation(com.boris.simple.springaop.logging.Logging)")
    private void byAnnotation() {
    }

    @Before(value = "byAnnotation()", argNames = "joinPoint" /*injection of a join point*/)
    public void annotateBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Annotation[] annotations = signature.getMethod().getAnnotations();

        boolean loggingNeeded = Arrays.stream(annotations)
                .filter(annotation -> annotation.annotationType().equals(Logging.class))
                .anyMatch(annotation ->
                        Arrays.stream(signature.getDeclaringType().getMethods())
                                .filter(method -> method.getName().equals(((Logging) annotation).needed()))
                                .filter(method -> method.getReturnType().getName().toLowerCase().equals("boolean"))
                                .findAny()
                                .map(method -> (Boolean) ReflectionUtils.invokeMethod(method, joinPoint.getTarget()))
                                .orElse(true));
        if (loggingNeeded)
            logger.info("Wow! AOP logging worked before executing method " + signature.getName());
        else
            logger.info("Well, AOP logging was rejected for method " + signature.getName());
    }

}
