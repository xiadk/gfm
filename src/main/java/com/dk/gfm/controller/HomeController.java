package com.dk.gfm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName HomeController
 * @Description TODO
 * @Author xiadekang
 * @Date 2018/9/7 9:59
 * @Version 1.0
 **/
@Controller
public class HomeController {

    @RequestMapping(value = "index")
    public String index(){

        System.out.println("我进来了");
        return "index";
    }

    @RequestMapping(value = "home")
    public String home(){

        System.out.println("ss");
        return "home";
    }
}
