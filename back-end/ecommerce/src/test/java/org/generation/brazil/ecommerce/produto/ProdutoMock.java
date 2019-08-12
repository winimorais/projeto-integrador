package org.generation.brazil.ecommerce.produto;

import com.github.javafaker.Faker;

import java.math.BigDecimal;

public class ProdutoMock {
    public static Produto getProdutoMock() {
        Faker faker = new Faker();
        Produto produto = new Produto();
        produto.setNome(faker.beer().name());
        produto.setDescricao(faker.zelda().character());
        produto.setPreco(BigDecimal.valueOf(faker.number().numberBetween(1,55)));
        return produto;
    }
}
