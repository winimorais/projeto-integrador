package org.generation.brazil.ecommerce.dadospessoais;

import com.github.javafaker.Faker;
import org.generation.brazil.ecommerce.cliente.Cliente;

public class DadosPessoaisMock {
    public static DadosPessoais getDadosPessoaisMock(){
        Faker faker = new Faker();
        DadosPessoais dadosPessoais = new DadosPessoais();
        dadosPessoais.setNome(faker.name().firstName());
        dadosPessoais.setSobrenome(faker.name().lastName());
        dadosPessoais.setCpf(faker.numerify("###.###.###-##"));
        dadosPessoais.setTelefone(faker.numerify("(##)#####-####"));
        dadosPessoais.setEmail(faker.internet().emailAddress());
        return dadosPessoais;
    }
}
