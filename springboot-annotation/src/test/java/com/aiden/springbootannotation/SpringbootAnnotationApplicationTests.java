package com.aiden.springbootannotation;

import com.aiden.springbootannotation.config.SelfConfiguration;
import com.aiden.springbootannotation.javabean.Account;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootAnnotationApplicationTests {
    @Autowired
    Account account;
    @Autowired
    SelfConfiguration selfConfiguration;

    @Test
    void contextLoads() {
        BigDecimal bigDecimal = new BigDecimal("1221.5165");
        BigDecimal accountMoney = account.getAccountMoney(bigDecimal);
        System.err.println(accountMoney);

        String testConfiguration = selfConfiguration.getTest("testConfiguration");
        System.err.println(testConfiguration);
    }

}
