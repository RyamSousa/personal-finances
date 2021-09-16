package com.personal_finances.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;
    private String name;
    // Alter cpf to string
    private Long cpf;
    // Alter name
    private Date birthdate;
    //private List<Acounts> acounts;
}
