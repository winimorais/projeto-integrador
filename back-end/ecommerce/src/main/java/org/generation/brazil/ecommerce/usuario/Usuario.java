package org.generation.brazil.ecommerce.usuario;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "id_cliente")
  private Long idCliente;

  @NotNull
  private String login;

  @NotNull
  private String senha;

}
