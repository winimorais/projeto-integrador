package org.generation.brazil.ecommerce.endereco;

import org.generation.brazil.ecommerce.EcommerceApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import java.util.Collections;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EnderecoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl(String path) {
        return "http://localhost:" + port + "/api/v1/" + path;
    }

    private String token;

    @Before
    public void init() {
        this.token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTY0NjkwODA0LCJleHAiOjE1NjU1NTQ4MDR9.WBuk585l04GxkjYmsiqG0aw2DNT6D3-qAOKgjt2xp7QGEVBVEIVaePs--OLky4fMaIrWEAD0GnqThHW6F9VEEQ";
    }

    @Test
    public void save() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + this.token);

        HttpEntity<Endereco> entity = new HttpEntity<>(EnderecoMock.getEnderecoMock(), headers);

        ResponseEntity<Endereco> responseEntity = testRestTemplate.exchange(
                getRootUrl("/enderecos/"),
                HttpMethod.POST,
                entity,
                Endereco.class
        );

        assertNotNull(responseEntity);
        assertEquals(201, responseEntity.getStatusCodeValue());
    }

    @Test
    public void findAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = testRestTemplate.exchange(
                getRootUrl("/enderecos/"),
                HttpMethod.GET,
                entity,
                String.class);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void findById() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<Endereco> response = testRestTemplate.exchange(
                getRootUrl("/enderecos/1"),
                HttpMethod.GET,
                entity,
                Endereco.class);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void update() {
        int id = 1;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + this.token);

        HttpEntity<Endereco> entity = new HttpEntity<>(EnderecoMock.getEnderecoMock(), headers);

        ResponseEntity<Endereco> responseEntity = testRestTemplate.exchange(
                getRootUrl("/enderecos/" + id),
                HttpMethod.PUT,
                entity,
                Endereco.class
        );

        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void delete() {
        int id = 1;
        Endereco endereco = testRestTemplate.getForObject(getRootUrl("/enderecos/delete/" + id), Endereco.class);
        assertNotNull(endereco);
        testRestTemplate.delete(getRootUrl("/enderecos/delete/" + id));
        try {
            testRestTemplate.getForObject(getRootUrl("/enderecos/delete/" + id), Endereco.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
        }
    }
}