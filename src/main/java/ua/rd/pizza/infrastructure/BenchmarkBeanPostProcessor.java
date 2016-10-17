package ua.rd.pizza.infrastructure;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import ua.rd.pizza.annotation.Benchmark;
import ua.rd.pizza.services.OrderService;
import ua.rd.pizza.services.PizzaService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Artem_Pryzhkov on 11-Oct-16.
 */
public class BenchmarkBeanPostProcessor implements BeanPostProcessor {
    Class<?>[] interfaces;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("    Created: " + beanName);
        System.out.println(Arrays.toString(getAllInterfaces(bean)));
        interfaces = getAllInterfaces(bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("    Initialized: " + beanName);
        return createBeanProxy(bean, beanName);
    }

    public Object createBeanProxy(final Object bean, String beanName) {
        if (interfaces.length == 0) {
            return bean;
        }
        return Proxy.newProxyInstance(bean.getClass().getClassLoader(), interfaces,
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Method[] methods = bean.getClass().getMethods();
                        //Method[] methods = proxy.getClass().getMethods();
                        for (Method m : methods) {
                            if (m.isAnnotationPresent(Benchmark.class)) {
                                Benchmark annotation = m.getAnnotation(Benchmark.class);
                                if (annotation.value()) {
                                    long startTime = System.nanoTime();
                                    Object returnObject = m.invoke(bean, args);
                                    long finishTime = System.nanoTime();
                                    System.out.println("time=" + (finishTime - startTime));
                                    return returnObject;
                                }
                            }
                        }
                        return method.invoke(bean, args);
                    }
                });
    }

    public Class<?>[] getAllInterfaces(Object object) {
        List<Class<?>> interfaces = new ArrayList<>();
        Class clazz = object.getClass();
        while (clazz != Object.class) {
            for (Class<?> i : clazz.getInterfaces()) {
                interfaces.add(i);
            }
            clazz = clazz.getSuperclass();
        }
        return interfaces.toArray(new Class<?>[interfaces.size()]);
    }
}
