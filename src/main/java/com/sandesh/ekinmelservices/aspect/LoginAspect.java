package com.sandesh.ekinmelservices.aspect;

import com.sandesh.ekinmelservices.model.ChangeLogin;
import com.sandesh.ekinmelservices.model.Login;
import com.sandesh.ekinmelservices.model.UserActionAudit;
import com.sandesh.ekinmelservices.service.UserActionAuditService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Aspect
@Component
public class LoginAspect {

    private final UserActionAuditService userActionAuditService;

    public LoginAspect(UserActionAuditService userActionAuditService) {
        this.userActionAuditService = userActionAuditService;
    }

    @Pointcut("execution(* com.sandesh.ekinmelservices.controller.LoginController.validateUser(..))")
    public void pointcutAuthenticate() {
    }

    @Pointcut("execution(* com.sandesh.ekinmelservices.controller.LoginController.changePassword(..))")
    public void pointcutLoginChange() {
    }

    @Around("pointcutAuthenticate() || pointcutLoginChange()")
    public Object onUserAuthenticationAround(ProceedingJoinPoint pJoinPoint) throws Throwable {
        log.info("Beginning of Around");
        Throwable exception = null;
        boolean hasPassed = true;
        String action = "LOGIN";
        Object responseObject = null;
        Object object = pJoinPoint.getArgs()[0];
        Login login = (Login) object;
        if (object instanceof ChangeLogin) {
            action = "RESET PASSWORD";
        }
        try {
            responseObject = pJoinPoint.proceed();
        } catch (Throwable throwable) {
            hasPassed = false;
            exception = throwable;
            throwable.printStackTrace();
        }
        auditToTheDatabase(hasPassed, login.getUsername(), action);
        log.info("End of Around");
        if (!hasPassed) throw exception;
        return responseObject;
    }

    private void auditToTheDatabase(boolean hasPassed, String username, String action) {
        UserActionAudit actionAudit = UserActionAudit.builder()
                .username(username).action(action)
                .actionStatus(hasPassed ? "PASSED" : "FAILED")
                .performedDate(new Date())
                .seqFailureCount(!hasPassed ? 1 : 0).build();
        this.userActionAuditService.saveUserActionAudit(actionAudit);
    }
}
