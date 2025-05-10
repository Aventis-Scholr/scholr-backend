package com.scholr.scholr_paltform.iam.domain.model.aggregates;

import com.scholr.scholr_paltform.iam.domain.model.entities.Role;
import com.scholr.scholr_paltform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User aggregate root
 * This class represents the aggregate root for the User entity.
 *
 * @see AuditableAbstractAggregateRoot
 */
@Getter
@Setter
@Entity
public class User extends AuditableAbstractAggregateRoot<User> implements UserDetails {

  @NotBlank
  @Size(max = 50)
  @Column(unique = true)
  private String username;

  @NotBlank
  @Size(max = 120)
  private String password;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(	name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  //*********************************************************
  //Añadiendo compañia , dni y codigo colaborador
  @Column(nullable = false)
  @NotBlank
  private String compania; // Compañía a la que pertenece el usuario

  @Column(nullable = false, unique = true)
  @NotBlank
  @Size(min = 7, max = 12)
  private String dni; // DNI del usuario

  @Column(nullable = false, unique = true)
  @NotBlank
  @Size(max = 20)
  private String cod_colaborador; // Código de colaborador

  //****************************************************

  @Column(columnDefinition = "TEXT")
  private String proofingEntrepreneure; // Added attribute

  public User() {
    this.roles = new HashSet<>();
  }

  public User(String username, String password,String compania, String dni, String cod_colaborador) {
    this.username = username;
    this.password = password;
    this.compania = compania;
    this.dni = dni;
    this.cod_colaborador = cod_colaborador;
    this.roles = new HashSet<>();
  }

  public User(String username, String password,String compania,String dni, String cod_colaborador, List<Role> roles) {
    this(username, password,compania, dni, cod_colaborador);
    addRoles(roles);
  }

  /**
   * Add a role to the user
   * @param role the role to add
   * @return the user with the added role
   */
  public User addRole(Role role) {
    this.roles.add(role);
    return this;
  }

  /**
   * Add a list of roles to the user
   * @param roles the list of roles to add
   * @return the user with the added roles
   */
  public User addRoles(List<Role> roles) {
    var validatedRoleSet = Role.validateRoleSet(roles);
    this.roles.addAll(validatedRoleSet);
    return this;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.roles;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}