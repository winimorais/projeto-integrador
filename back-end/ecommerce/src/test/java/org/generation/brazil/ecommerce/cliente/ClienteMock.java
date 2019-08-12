package org.generation.brazil.ecommerce.cliente;

import com.github.javafaker.Faker;

public class ClienteMock {
    public static Cliente getClienteMock(){
        Faker faker = new Faker();
        Cliente cliente = new Cliente();
        cliente.setIdDadosPessoais(Long.valueOf(faker.number().digits(1)));
        cliente.setIdEndereco(Long.valueOf(faker.number().digits(1)));
        cliente.setIdPedido(Long.valueOf(faker.number().digits(1)));
        return cliente;
    }
}
