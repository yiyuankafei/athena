package com.athena.spring.context.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class TestAutoBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        /**
         * 获取A类型的BeanDefinition
         */
        GenericBeanDefinition abd = (GenericBeanDefinition)beanFactory.getBeanDefinition("a");
        /**
         * 设置A类的自动装配类型为自动装配，被自动装配的属性，需要有set方法，或者构造方法
         */
        abd.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);

        /**
         * 如果A的B属性上有@Autowired，则这行代码不起作用，这也证明了@Autowired不是自动装配
         */
        //beanFactory.ignoreDependencyType(B.class);
    }
}
