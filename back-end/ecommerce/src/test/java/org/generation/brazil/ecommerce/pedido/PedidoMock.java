package org.generation.brazil.ecommerce.pedido;

import com.github.javafaker.Faker;

import java.math.BigDecimal;

public class PedidoMock {
    public static Pedido getPedidoMock(){
        Faker faker = new Faker();
        Pedido pedido = new Pedido();
        pedido.setPrecoTotal(BigDecimal.valueOf(faker.number().numberBetween(1, 300)));
        pedido.setFrete(BigDecimal.valueOf(faker.number().numberBetween(1, 300)));
        pedido.setIdProduto((long) faker.number().numberBetween(1, 100));
        pedido.setNumeroPedido(faker.numerify("######"));
        return pedido;
    }
}
