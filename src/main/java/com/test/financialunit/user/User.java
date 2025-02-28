package com.test.financialunit.user;

import com.test.financialunit.user.dto.UserRole;
import com.test.financialunit.user.dto.UserState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
 public class User implements UserDetails {

    @Id
    @Column(name = "id")
    UUID id;

    @Column(name = "password")
    String password;

    @Column(name = "username")
    String username;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    UserState state;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    UserRole role;

    @Column(name = "created_at")
    Timestamp createdAt;

    @Column(name = "updated_at")
    Timestamp updatedAt;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return state.equals(UserState.ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return state.equals(UserState.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return state.equals(UserState.ACTIVE);
    }

    @Override
    public boolean isEnabled() {
        return state.equals(UserState.ACTIVE);
    }
}
