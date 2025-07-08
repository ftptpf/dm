package ru.ftptpf.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@Order(1)
public class FirstAspect {
    /**
     * this - check AOP proxy class type
     * target - check target object class type
     */
    @Pointcut("this(org.springframework.data.repository.Repository)")
    public void isRepositoryLayer() {
    }

    /**
     * @annotation - check annotation on method level
     */
    @Pointcut("ru.ftptpf.aop.CommonPointcuts.isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping() {
    }

    /**
     * args - check method param type
     * - any param type
     * .. - 0+ any params type
     */
    @Pointcut("ru.ftptpf.aop.CommonPointcuts.isControllerLayer() && args(org.springframework.ui.Model,..)")
    public void hasModelParam() {
    }

    /**
     * @args - check annotation on the param type
     * - any param type
     * .. - 0+ any params type
     */
    @Pointcut("ru.ftptpf.aop.CommonPointcuts.isControllerLayer() && @args(ru.ftptpf.validation.UserInfo,..)")
    public void hasUserInfoParamAnnotation() {
    }

    /**
     * bean - check bean name
     */
    @Pointcut("bean(*Service)")
    public void isServiceLayerBean() {
    }

    /**
     * execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)
     */
    @Pointcut("execution(public * ru.ftptpf.service.*Service.findById(*))")
    public void anyFindByIdServiceMethod() {
    }

    @Before(value = "anyFindByIdServiceMethod() "
                    + "&& args(id) "
                    + "&& target(service) "
                    + "&& this(serviceProxy)",
            argNames = "joinPoint,id,service,serviceProxy")
    public void addLogging(JoinPoint joinPoint,
                           Object id,
                           Object service,
                           Object serviceProxy) {
        log.info("invoked findById method in class {}, with id {}", service, id);
    }

    @AfterReturning(value = "anyFindByIdServiceMethod() && target(service)", returning = "result")
    public void addLoggingAfterReturning(Object result, Object service) {
        log.info("after returning - invoked findById method in class {}, result {}", service, result);
    }

    @AfterThrowing(value = "anyFindByIdServiceMethod() && target(service)", throwing = "ex")
    public void addLoggingAfterThrowing(Throwable ex, Object service) {
        log.info("after throwing - invoked findById method in class {}, exception {}: {}",
                service, ex.getClass(), ex.getMessage());
    }

    @After("anyFindByIdServiceMethod() && target(service)")
    public void addLoggingAfterFinally(Object service) {
        log.info("after (finally) - invoked findById method in class {}", service);
    }
}
