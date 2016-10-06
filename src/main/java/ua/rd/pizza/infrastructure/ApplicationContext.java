package ua.rd.pizza.infrastructure;

import ua.rd.pizza.annotation.Benchmark;
import ua.rd.pizza.annotation.PostCreate;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
public class ApplicationContext implements Context {

    private final Config config;
    private Map<String, Object> beans = new HashMap<>();

    public ApplicationContext(Config config) {
        this.config = config;
    }

    @Override
    public <T> T getBean(String beanName) {
        Class<?> type = config.getImpl(beanName);
        Object bean = beans.get(beanName);
        if (bean != null) {
            return (T) bean;
        }
        BeanBuilder builder = new BeanBuilder(type);
        builder.createBean();

        builder.callPostCreateMethod();
        builder.callInitMethod();
        builder.createBeanProxy();

        bean = builder.build();
        beans.put(beanName, bean);
        return (T) bean;
    }

    class BeanBuilder {
        private Object bean;
        private final Class<?> type;

        BeanBuilder(Class<?> type) {
            this.type = type;
        }

        public void createBean() {
            int constructorsAmount = type.getConstructors().length;
            Constructor<?> constructor = null;
            if (constructorsAmount == 1) {
                constructor = type.getConstructors()[0];
            } else {
                throw new RuntimeException();
            }

            Object[] constructorParameters = instanceConstructorParameters(constructor);
            bean = instance(constructor, constructorParameters);
        }

        public Object build() {
            return bean;
        }

        private Object instance(Constructor<?> constructor, Object... parameters) {
            try {
                return constructor.newInstance(parameters);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private Object[] instanceConstructorParameters(Constructor<?> constructor) {
            Class<?>[] constructorParametersType = constructor.getParameterTypes();
            int parametersAmount = constructorParametersType.length;
            Object[] args = new Object[parametersAmount];
            for (int i = 0; i < parametersAmount; i++) {
                String beanName = firstLetterToLowerCase(constructorParametersType[i].getSimpleName());
                args[i] = getBean(beanName);
            }
            return args;
        }

        private String firstLetterToLowerCase(String name) {
            char[] nameInChar = name.toCharArray();
            nameInChar[0] = Character.toLowerCase(nameInChar[0]);
            return new String(nameInChar);
        }

        public void callInitMethod() {
            Method initMethod = null;
            try {
                initMethod = type.getMethod("init");
                initMethod.invoke(bean);
            } catch (NoSuchMethodException e) {
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }

        public void callPostCreateMethod() {
            Method[] methods = type.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(PostCreate.class)) {
                    try {
                        method.invoke(bean);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        public void createBeanProxy() {
            Object prevBean = bean;
            bean = Proxy.newProxyInstance(bean.getClass().getClassLoader(),bean.getClass().getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            Method[] methods = type.getMethods();
                            for (Method m : methods) {
                                if (m.isAnnotationPresent(Benchmark.class)) {
                                    Benchmark annotation=m.getAnnotation(Benchmark.class);
                                    if(annotation.value()){
                                        long startTime = System.nanoTime();
                                        Object returnObject = m.invoke(prevBean, args);
                                        long finishTime = System.nanoTime();
                                        System.out.println("time="+(finishTime - startTime));
                                        return returnObject;
                                    }
                                }
                            }
                            return method.invoke(prevBean,args);
                        }
                    });
        }
    }
}



