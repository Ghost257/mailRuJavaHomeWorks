import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.annotation.Nullable;

public class Interceptor implements MethodInterceptor {
    private int counter = 0;
    @Nullable
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        counter++;
        if (counter % 3 == 0)
        {
            System.out.print("3-fold input: ");
        }
        return invocation.proceed();
    }
}
