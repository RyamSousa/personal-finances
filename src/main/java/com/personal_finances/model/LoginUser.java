package com.personal_finances.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String username;
    private String password;

    @ManyToMany(fetch = EAGER)
    private List<Role> roles = new ArrayList<>();
}
