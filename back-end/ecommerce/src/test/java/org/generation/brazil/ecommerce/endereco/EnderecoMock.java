package org.generation.brazil.ecommerce.endereco;

import com.github.javafaker.Faker;

import java.math.BigDecimal;

public class EnderecoMock {
    public static Endereco getEnderecoMock() {
        Faker faker = new Faker();
        Endereco endereco = new Endereco();
        endereco.setLogradouro(faker.address().streetName());
        endereco.setNumero(faker.address().buildingNumber());
        endereco.setCep(faker.address().zipCode());
        endereco.setBairro(faker.address().firstName());
        endereco.setCidade(faker.address().city());
        endereco.setEstado(faker.address().state());
        endereco.setComplemento(faker.address().streetAddressNumber());
        endereco.setReferencia(faker.address().fullAddress());
        return endereco;
    }
}
