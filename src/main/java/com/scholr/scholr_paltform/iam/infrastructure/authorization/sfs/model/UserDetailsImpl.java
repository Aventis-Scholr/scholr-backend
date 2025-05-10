package com.scholr.scholr_paltform.iam.infrastructure.authorization.sfs.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scholr.scholr_paltform.iam.domain.model.aggregates.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

// * This class is responsible for providing the user details to the Spring Security framework.
// * It implements the UserDetails interface.
@Getter
@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {

  private final String username;
  @JsonIgnore
  private final String password;
  private final boolean accountNonExpired;
  private final boolean accountNonLocked;
  private final boolean credentialsNonExpired;
  private final boolean enabled;
  private final Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(String username, String password,
      Collection<? extends GrantedAuthority> authorities) {
    this.username = username;
    this.password = password;
    this.authorities = authorities;
    this.accountNonExpired = true;
    this.accountNonLocked = true;
    this.credentialsNonExpired = true;
    this.enabled = true;
  }

  // * This method is responsible for building the UserDetailsImpl object from the User object.
  // * @param user The user object.
  // * @return The UserDetailsImpl object.

  public static UserDetailsImpl build(User user) {
    var authorities = user.getRoles().stream()
        .map(role -> role.getName().name())
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());

    return new UserDetailsImpl(user.getUsername(), user.getPassword(), authorities);
  }
}


/*

package com.scholr.scholr_paltform.iam.infrastructure.authorization.sfs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scholr.scholr_paltform.iam.domain.model.aggregates.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;


// * This class is responsible for providing the user details to the Spring Security framework.
// * It implements the UserDetails interface.

@Getter
@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {

  private final String username;
  //Añadiendo los atributos
  private final String compania; // Compañía a la que pertenece el usuario
  private final String dni; // DNI del usuario
  private final String cod_colaborador; // Código de colaborador
  @JsonIgnore
  private final String password;

  private final boolean accountNonExpired;
  private final boolean accountNonLocked;
  private final boolean credentialsNonExpired;
  private final boolean enabled;
  private final Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(String username, String password,String compania, String dni, String cod_colaborador,
                         Collection<? extends GrantedAuthority> authorities) {
    this.username = username;
    this.password = password;
    this.compania = compania;
    this.dni = dni;
    this.cod_colaborador = cod_colaborador;
    this.authorities = authorities;
    this.accountNonExpired = true;
    this.accountNonLocked = true;
    this.credentialsNonExpired = true;
    this.enabled = true;
  }

//   * This method is responsible for building the UserDetailsImpl object from the User object.
//   * @param user The user object.
//   * @return The UserDetailsImpl object.
  public static UserDetailsImpl build(User user) {
    var authorities = user.getRoles().stream()
            .map(role -> role.getName().name())
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

    return new UserDetailsImpl(user.getUsername(), user.getPassword(), user.getCompania(), user.getDni(), user.getCod_colaborador(),authorities);
  }
}
*/
