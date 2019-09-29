package com.pdy.demo;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SomethingGoodTest {

    private SomethingGood subject;

    @Before
    public void test(){
        this.subject = new SomethingGood();
    }

    @Test
    public void shoudReturnNull(){
        final String actual = this.subject.apply(null);
        Assert.assertEquals(actual, "null");
    }

    @Test
    public void shoudREturnLower(){
        final String actual = this.subject.apply("BOB");
        Assert.assertEquals(actual, "bob");
    }
}
