package com.pdy.fac.dockerdemo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.boot.actuate.info.Info;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Info greeting(@RequestParam(value="name", defaultValue="World") final String name) {
        return new Info.Builder().withDetail("id", counter.incrementAndGet()).withDetail("name", name).build();
    }
}