package com.common.core.validation;

import com.common.core.enumset.EnumSet;
import com.common.core.exception.MyException;
import com.common.core.validation.Validator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/*
可以使用@order注解指定切面的优先级，值越小优先级越高
*/
@Order(1)
@Component
@Aspect
public class FormValidation {

    private static final String METHODNAME = "validate";

    //注解式表单验证 在加了该注解方法之前执行 aop 切面 加了该注解的所有方法
    @Around("@annotation(com.common.core.validation.Validate)")
    public Object formValidation(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();//方法签名
        Method method = ((MethodSignature) signature).getMethod();
        //这个方法才是目标对象上有注解的方法
        Method realMethod = point.getTarget().getClass().getDeclaredMethod(signature.getName(), method.getParameterTypes());
        //获取到方法上的自己已知的注解
        Validate annotation = realMethod.getAnnotation(Validate.class);
        //获取到注解上的值
        String name = annotation.value().getName();
        //通过反射调用注解中的方法
        Class<?> clazz = Class.forName(name);
        Method clazzMethod = clazz.getMethod(METHODNAME, Object[].class);
        Object invoke = null;
        try {
            //获取到抽象类具体的继承
            Object o = clazz.newInstance();
            //提交的参数
            Object[] args = point.getArgs();
            invoke = clazzMethod.invoke(o, args);
        } catch (Exception e) {
            throw new MyException(EnumSet.FAILURE);
        }
        if (null != invoke) {
            return invoke;
        }
        return point.proceed();
    }
}
