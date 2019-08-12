package org.generation.brazil.ecommerce.cliente;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@OneToOne
    //@JoinColumn(name = "id_dados_pessoais", referencedColumnName = "id")
    @NotNull
    @Column(name = "id_dados_pessoais")
    private Long idDadosPessoais;

    //@OneToOne
    //@JoinColumn(name = "id_endereco", referencedColumnName = "id")
    @NotNull
    @Column(name = "id_endereco")
    private Long idEndereco;

    //@OneToOne
    //@JoinColumn(name = "id_pedido", referencedColumnName = "id")
    @NotNull
    @Column(name = "id_pedido")
    private Long idPedido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDadosPessoais() {
        return idDadosPessoais;
    }

    public void setIdDadosPessoais(Long idDadosPessoais) {
        this.idDadosPessoais = idDadosPessoais;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }
}
