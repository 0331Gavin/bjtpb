package com.eastrise.web.bjtpb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * create by gzq on 2018/10/30 10:30
 */
@RestController
public class TestController {



    @RequestMapping(value = {"/test"}, method = RequestMethod.GET)
    public String err(HttpServletRequest request) {

        return "erroraaaaa";
    }
}
