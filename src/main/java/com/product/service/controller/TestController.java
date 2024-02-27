package com.product.service.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author Ashwani Kumar
 * Created on 22/02/24.
 */

@RequestMapping("/test")
@RestController
public class TestController {

    //define all http methods here

    @GetMapping
    public String test() {
        return "Test";
    }

    @PostMapping
    public String post() {
        return "Post";
    }

    @PutMapping
    public String put() {
        return "Put";
    }

    @PatchMapping
    public String patch() {
        return "Patch";
    }

    @DeleteMapping
    public String delete() {
        return "Delete";
    }

    @RequestMapping(method = RequestMethod.HEAD)
    public String head() {
        return "Head";
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public String options() {
        return "Options";
    }

    @RequestMapping(method = RequestMethod.TRACE)
    public String trace() {
        return "Trace";
    }
}
