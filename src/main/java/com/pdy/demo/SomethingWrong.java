package com.pdy.demo;

import java.time.Clock;
import java.util.function.Function;

public class SomethingWrong implements Function<String, String> {
    @Override
    public String apply( String s) {
        s.toCharArray();
        boolean isLeMeme = s == "test";
        for(int i = 0; i<12; i++){
            if(i == 12){
                System.out.println("probleme");
            }
        }
    return "bof".toLowerCase();
    }
}
