package com.personal_finances.service;

import com.personal_finances.mapper.RolesMapper;
import com.personal_finances.model.Role;
import com.personal_finances.model.RoleMother;
import com.personal_finances.model.dto.RoleDTO;
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

    @Mock
    private RolesMapper rolesMapper;

    @InjectMocks
    private RoleService roleService;

    @Test
    void givenRequestValid_whenCreateRole_thenCreateAndReturnUser(){
        RoleDTO roleDto = RoleMother.getRoleDto();
        Role role = RoleMother.getRole();

        given(rolesMapper.toDto(role)).willReturn(roleDto);
        given(rolesMapper.toRole(roleDto)).willReturn(role);
        given(roleRepository.save(any(Role.class))).willReturn(role);
        given(roleRepository.findByName(any(String.class))).willReturn(Optional.empty());

        RoleDTO save = roleService.save(roleDto);

        BDDAssertions.assertThat(save).usingRecursiveComparison().isEqualTo(roleDto);
    }
}