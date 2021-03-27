package com.learn.spring5ioc;

import com.learn.spring5ioc.interf.UserService;
import com.learn.spring5ioc.javaBean.Star;
import com.learn.spring5ioc.javaBean.Student;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Scanner;

@RunWith(SpringRunner.class)
@SpringBootTest
class Spring5IocApplicationTests {

    @Test
    void contextLoads() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("my-beans.xml");
        Student student = (Student) applicationContext.getBean("student");
        student.learn();
    }

    @Test
    void test1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("my-beans.xml");
        Star star = applicationContext.getBean("factoryStar", Star.class);

        System.err.println(star);
    }

    @Test
    public void testProxyObj() {
        UserService proxyInstance = (UserService) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{UserService.class}, (proxy, method, args) -> proxy);
        proxyInstance.hello();
        System.out.println(proxyInstance.getClass().toString());
    }

    static class MyInvocationHandler implements InvocationHandler {
        /**
         * Processes a method invocation on a proxy instance and returns
         * the result.  This method will be invoked on an invocation handler
         * when a method is invoked on a proxy instance that it is
         * associated with.
         *
         * @param proxy  the proxy instance that the method was invoked on
         * @param method the {@code Method} instance corresponding to
         *               the interface method invoked on the proxy instance.  The declaring
         *               class of the {@code Method} object will be the interface that
         *               the method was declared in, which may be a superinterface of the
         *               proxy interface that the proxy class inherits the method through.
         * @param args   an array of objects containing the values of the
         *               arguments passed in the method invocation on the proxy instance,
         *               or {@code null} if interface method takes no arguments.
         *               Arguments of primitive types are wrapped in instances of the
         *               appropriate primitive wrapper class, such as
         *               {@code java.lang.Integer} or {@code java.lang.Boolean}.
         * @return the value to return from the method invocation on the
         * proxy instance.  If the declared return type of the interface
         * method is a primitive type, then the value returned by
         * this method must be an instance of the corresponding primitive
         * wrapper class; otherwise, it must be a type assignable to the
         * declared return type.  If the value returned by this method is
         * {@code null} and the interface method's return type is
         * primitive, then a {@code NullPointerException} will be
         * thrown by the method invocation on the proxy instance.  If the
         * value returned by this method is otherwise not compatible with
         * the interface method's declared return type as described above,
         * a {@code ClassCastException} will be thrown by the method
         * invocation on the proxy instance.
         * @throws Throwable the exception to throw from the method
         *                   invocation on the proxy instance.  The exception's type must be
         *                   assignable either to any of the exception types declared in the
         *                   {@code throws} clause of the interface method or to the
         *                   unchecked exception types {@code java.lang.RuntimeException}
         *                   or {@code java.lang.Error}.  If a checked exception is
         *                   thrown by this method that is not assignable to any of the
         *                   exception types declared in the {@code throws} clause of
         *                   the interface method, then an
         *                   {@link UndeclaredThrowableException} containing the
         *                   exception that was thrown by this method will be thrown by the
         *                   method invocation on the proxy instance.
         * @see UndeclaredThrowableException
         */


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }
    }

    @Test
    public void testRegisterBeanDefination() {
        // 实现了BeanDefinationRegistry接口，具有注册BeanDefination的能力
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("stu123", new RootBeanDefinition(Student.class));

        System.out.println(beanFactory.getBean("stu123"));
    }

    @Test
    public void testProcess() {
        BufferedReader br = null;
        try {
            Process proc = Runtime.getRuntime().exec("tasklist");
            br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            @SuppressWarnings("unused")
            String line = null;
            System.out.println("打印所有正在运行的进程信息");
            while ((line = br.readLine()) != null) {
                String p = br.readLine();

                System.out.println(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testProcess2() throws IOException {
            Runtime.getRuntime().exec("taskkill /im QQ.exe /f");
    }

    @Test
    public void testRef(){
        Class<? extends Spring5IocApplicationTests> aClass = this.getClass();
        for (Annotation annotation : aClass.getAnnotations()) {
            System.err.println(annotation.toString());
        }
        System.err.println(aClass.toString());
    }
}




























