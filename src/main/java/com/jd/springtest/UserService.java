package com.jd.springtest;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String sayHello(String username){
        return "hello, "+username;
    }
}
