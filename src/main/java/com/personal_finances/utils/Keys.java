package com.personal_finances.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Keys {

    @Value("${jwt.key}")
    public String JWT_KEY;

}
