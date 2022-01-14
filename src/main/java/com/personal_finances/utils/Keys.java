package com.personal_finances.utils;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class Keys {

    //@Value("${jwt.key}")
    public String JWT_KEY = "key";
}
