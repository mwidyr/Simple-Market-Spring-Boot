package com.example.simplemarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloController {

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @GetMapping(value = "/hello")
    public String hello(@RequestHeader(name = "Accept-Languange", required = false) Locale locale) {

        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }
}
