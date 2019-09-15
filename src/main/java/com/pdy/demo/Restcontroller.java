package com.pdy.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Restcontroller {

    @GetMapping("/api/test")
    public ResponseEntity<String> getHi(){
        return ResponseEntity.ok("ok");
    }

}
