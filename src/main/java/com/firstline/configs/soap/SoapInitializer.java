package com.firstline.configs.soap;

import org.springframework.core.annotation.Order;
import org.springframework.ws.transport.http.support.AbstractAnnotationConfigMessageDispatcherServletInitializer;

import java.lang.annotation.Annotation;


public class SoapInitializer extends
        AbstractAnnotationConfigMessageDispatcherServletInitializer
implements Order {
    @Override
    public int value() {
        return 0;
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[0];
    }

    @Override
    protected String getServletName() {
        return "soap";
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{SoapConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/ws/*"};
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}