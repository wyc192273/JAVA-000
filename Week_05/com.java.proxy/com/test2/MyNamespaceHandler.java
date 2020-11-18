package com.test2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by yuchen.wu on 2020-11-15
 */

public class MyNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("student", new StudentBeanDefinitionParser());
        registerBeanDefinitionParser("klass", new ClassBeanDefinitionParser());
        registerBeanDefinitionParser("school", new SchoolDefinitionParser());
    }

    private static class StudentBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

        @Override
        protected Class<?> getBeanClass(Element element) {
            return Student.class;
        }

        @Override
        protected void doParse(Element element, BeanDefinitionBuilder builder) {
            String name = element.getAttribute("name");
            String age = element.getAttribute("age");
            if (StringUtils.hasText(name)) {
                builder.addPropertyValue("name", name);
            }
            if (StringUtils.hasText(age)) {
                builder.addPropertyValue("age", age);
            }
        }
    }

    private static class ClassBeanDefinitionParser extends AbstractSingleBeanDefinitionParser  {


        @Override
        protected Class<?> getBeanClass(Element element) {
            return KlassFactoryBean.class;
        }

        @Override
        protected void doParse(Element element, BeanDefinitionBuilder builder) {
            String name = element.getAttribute("name");
            NodeList nodeList = element.getChildNodes();
            if (StringUtils.hasText(name)) {
                builder.addPropertyValue("name", name);
            }
            List<String> studentBeanNameList = new ArrayList<>();
            int length = nodeList.getLength();
            for (int i = 0; i < length; i++) {
                Node node = nodeList.item(i);
                if (node instanceof Element) {
                    String value = ((Element) node).getAttribute("value");
                    studentBeanNameList.add(value);
                }
            }
            builder.addPropertyValue("studentBeanNameList", studentBeanNameList);

        }
    }

    private static class SchoolDefinitionParser extends AbstractSingleBeanDefinitionParser {

        @Override
        protected Class<?> getBeanClass(Element element) {
            return SchoolFactoryBean.class;
        }

        @Override
        protected void doParse(Element element, BeanDefinitionBuilder builder) {
            String name = element.getAttribute("name");
            NodeList nodeList = element.getChildNodes();
            if (StringUtils.hasText(name)) {
                builder.addPropertyValue("name", name);
            }
            List<String> klassBeanNameList = new ArrayList<>();
            int length = nodeList.getLength();
            for (int i = 0; i < length; i++) {
                Node node = nodeList.item(i);
                if (node instanceof Element) {
                    String value = ((Element) node).getAttribute("value");
                    klassBeanNameList.add(value);
                }
            }
            builder.addPropertyValue("klassBeanNameList", klassBeanNameList);
        }
    }


    private static class KlassFactoryBean extends AbstractFactoryBean<Klass> implements ApplicationContextAware {

        private ApplicationContext applicationContext;
        private List<String> studentBeanNameList;
        private String name;

        @Override
        public Class<?> getObjectType() {
            return Klass.class;
        }

        @Override
        protected Klass createInstance() throws Exception {
            List<Student> students = new ArrayList<>();
            for (String studentBeanName : studentBeanNameList) {
                Student student = applicationContext.getBean(studentBeanName, Student.class);
                students.add(student);
            }
            Klass klass = BeanUtils.instantiateClass(Klass.class);
            klass.setName(this.name);
            klass.setStudents(students);
            return klass;
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
        }

        public void setStudentBeanNameList(List<String> studentBeanNameList) {
            this.studentBeanNameList = studentBeanNameList;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static class SchoolFactoryBean extends AbstractFactoryBean<School> implements ApplicationContextAware {

        private ApplicationContext applicationContext;
        private List<String> klassBeanNameList;
        private String name;

        @Override
        public Class<?> getObjectType() {
            return School.class;
        }

        @Override
        protected School createInstance() throws Exception {
            List<Klass> klasses = new ArrayList<>();
            for (String klassBeanName : klassBeanNameList) {
                Klass klass = applicationContext.getBean(klassBeanName, Klass.class);
                klasses.add(klass);
            }
            School school = BeanUtils.instantiateClass(School.class);
            school.setName(name);
            school.setClasses(klasses);
            return school;
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
        }

        public void setKlassBeanNameList(List<String> klassBeanNameList) {
            this.klassBeanNameList = klassBeanNameList;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
