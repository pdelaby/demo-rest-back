package com.pdy.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Restcontroller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Restcontroller.class);

    @GetMapping("/api/test")
    public ResponseEntity<String> getHi(){
        LOGGER.warn("test");
        return ResponseEntity.ok("ok");
    }

}
