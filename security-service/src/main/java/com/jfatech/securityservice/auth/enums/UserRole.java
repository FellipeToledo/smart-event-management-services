package com.jfatech.securityservice.auth.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.jfatech.securityservice.auth.enums.UserPermission.ADMIN_CREATE;
import static com.jfatech.securityservice.auth.enums.UserPermission.ADMIN_DELETE;
import static com.jfatech.securityservice.auth.enums.UserPermission.ADMIN_READ;
import static com.jfatech.securityservice.auth.enums.UserPermission.ADMIN_UPDATE;
import static com.jfatech.securityservice.auth.enums.UserPermission.MANAGER_CREATE;
import static com.jfatech.securityservice.auth.enums.UserPermission.MANAGER_DELETE;
import static com.jfatech.securityservice.auth.enums.UserPermission.MANAGER_READ;
import static com.jfatech.securityservice.auth.enums.UserPermission.MANAGER_UPDATE;

/**
 * @author Fellipe Toledo
 */
@RequiredArgsConstructor
public enum UserRole {

    USER(Collections.emptySet()),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE,
                    MANAGER_CREATE
            )
    ),
    MANAGER(
            Set.of(
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE,
                    MANAGER_CREATE
            )
    );

    @Getter
    private final Set<UserPermission> userPermissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getUserPermissions()
                .stream()
                .map(userPermission -> new SimpleGrantedAuthority(userPermission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
