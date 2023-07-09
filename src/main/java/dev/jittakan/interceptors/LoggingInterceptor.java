package dev.jittakan.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Logger;

@Logging
@Interceptor
public class LoggingInterceptor {

    @Inject
    private Logger log;

    @AroundInvoke
    public Object logging(InvocationContext invocationContext) throws Exception {

        log.info(" ***** entering before invoking the method " +
                invocationContext.getMethod().getName() +
                " of the class " + invocationContext.getMethod().getDeclaringClass());

        Object resultado = invocationContext.proceed();

        log.info(" ***** Getting out of the method invasion " +
                invocationContext.getMethod().getName() +
                " of the class " + invocationContext.getMethod().getDeclaringClass());

        return resultado;
    }
}
