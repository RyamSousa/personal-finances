package com.personal_finances.service;

import com.personal_finances.model.Role;
import com.personal_finances.model.RoleMother;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.BDDAssertions;
import com.personal_finances.repository.RoleRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.BDDMockito.*;

@ExtendWith({MockitoExtension.class})
@ActiveProfiles("Test")
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;


    @InjectMocks
    private RoleService roleService;

    @Test
    void givenRequestValid_whenCreateRole_thenCreateAndReturnRole(){
        Role role = RoleMother.getRole();

        given(roleRepository.save(any(Role.class))).willReturn(role);
        given(roleRepository.findByName(any(String.class))).willReturn(Optional.empty());

        Role save = roleService.save(role);

        BDDAssertions.assertThat(save).usingRecursiveComparison().isEqualTo(role);
    }

    @Test
    void givenRequestValid_whenDeleteRole_thenDeleteAndReturnRole(){
        Role role = RoleMother.getRole();

        given(roleRepository.findByName(any(String.class))).willReturn(Optional.of(role));
        Role delete = roleService.delete(role.getName());

        BDDAssertions.assertThat(delete).usingRecursiveComparison().isEqualTo(role);
    }

    @Test
    void givenRequestValid_whenFindRoleByName_thenGetAndReturnRole(){
        Role role = RoleMother.getRole();

        given(roleRepository.findByName(any(String.class))).willReturn(Optional.of(role));

        Role roleFinded = roleService.findByName(role.getName());

        BDDAssertions.assertThat(role).usingRecursiveComparison().isEqualTo(roleFinded);
    }

}