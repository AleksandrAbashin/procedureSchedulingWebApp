package com.firstline.configs;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import java.lang.annotation.Annotation;


public class MvcAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer implements Order{
    @Override
    public int value() {
        return 1;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {

        return new Class<?>[0];
    }

    @Override
    protected String getServletName() {
        return "rest";
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                MvcConfiguration.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/*"};
    }

}