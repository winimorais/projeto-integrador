package br.com.ecommerce.ecommerce.cliente;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //@OneToOne
    //@JoinColumn(name = "id_dados_pessoais", referencedColumnName = "id")
    @NotNull
    private Long idDadosPessoais;

    //@OneToOne
    //@JoinColumn(name = "id_endereco", referencedColumnName = "id")
    @NotNull
    private Long idEndereco;

    //@OneToOne
    //@JoinColumn(name = "id_pedido", referencedColumnName = "id")
    @NotNull
    private Long idPedido;

}
