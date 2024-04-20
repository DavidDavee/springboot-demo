package org.example.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @program: springboot-demo
 * @description:
 * @author: David
 * @create: 2024-04-19 00:18
 **/
@CrossOrigin
@RestController
@RequestMapping("say")
public class SayController {
    @GetMapping("hi")
    public String hi(){
        return  "hi";
    }
}


