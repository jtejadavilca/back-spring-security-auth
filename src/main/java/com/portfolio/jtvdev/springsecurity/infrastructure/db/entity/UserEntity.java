package com.portfolio.jtvdev.springsecurity.infrastructure.db.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.portfolio.jtvdev.springsecurity.infrastructure.rest.dto.UserDto;
import com.portfolio.jtvdev.springsecurity.infrastructure.rest.response.RegisterResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;

  @Email
  @Column(unique = true)
  private String email;
  @Column
  private String password;

  @NotNull
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_role_relate",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<RoleEntity> roles = new HashSet<>();

  public RegisterResponse toRegisterResponse() {
    return RegisterResponse.builder()
        .data(UserDto.builder()
                .id(this.id)
            .firstName(firstName)
            .lastName(lastName)
            .email(email)
            .roles(roles.stream().map(RoleEntity::getRoleName).map(Enum::name).collect(Collectors.toSet()))
            .build())
        .build();
    }

  public void addRole(RoleEntity roleEntity) {
    if(this.roles == null) {
      this.roles = new HashSet<>();
    }
    this.roles.add(roleEntity);
  }
}



