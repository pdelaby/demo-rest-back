package com.pdy.demo;

import java.util.function.Function;

public class SomethingGood implements Function<String, String> {
    @Override
    public String apply(final String s) {
        if(s == null){
            return "null";
        }else{
            return s.toLowerCase();
        }

    }
}
