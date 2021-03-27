package com.aiden.springbootannotation.service.impl;

import com.aiden.springbootannotation.service.ConstructorService;
import org.springframework.stereotype.Service;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2021-3-27 11:33:24
 */

@Service
public class ConstructorServiceImpl implements ConstructorService {
    @Override
    public void testCon() {
        System.err.println("testCon:::");
    }
}
