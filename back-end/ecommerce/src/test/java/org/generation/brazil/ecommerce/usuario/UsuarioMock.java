package org.generation.brazil.ecommerce.usuario;

import com.github.javafaker.Faker;
import java.util.Locale;

public class UsuarioMock {

    public static Usuario getUsuarioMock(){
        Faker faker = new Faker(new Locale("pt-BR"));
        Usuario usuario = new Usuario();
        usuario.setLogin(faker.name().username());
        usuario.setSenha(faker.internet().password());
        return usuario;
    }
}

