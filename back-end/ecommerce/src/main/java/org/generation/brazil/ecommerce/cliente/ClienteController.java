package org.generation.brazil.ecommerce.cliente;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.generation.brazil.ecommerce.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;


    @ApiOperation(value = "Cadastra um novo cliente",
            notes = "Cadastra um novo cliente",
            response = Cliente.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente cadastrado com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }


    @ApiOperation(value = "Lista todos os clientes",
            notes = "Lista todos os clientes",
            response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clientes listados com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @GetMapping("/clientes")
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @ApiOperation(value = "Busca um cliente, utilizando seu id",
            notes = "Busca um cliente, utilizando seu id",
            response = Optional.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente buscado com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @GetMapping("/clientes/{id}")
    public Optional<Cliente> findById(@PathVariable Long id) throws ResourceNotFoundException {
        if (clienteRepository.findById(id).isPresent()) {
            return clienteRepository.findById(id);
        } else throw new ResourceNotFoundException("Não há cliente com o id " + id);
    }

    @ApiOperation(value = "Atualiza os dados do cliente",
            notes = "Atualiza os dados do cliente",
            response = Cliente.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente atualizado com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @PutMapping("/clientes/{id}")
    public Cliente update(@PathVariable Long id, @RequestBody Cliente cliente) throws ResourceNotFoundException{
        return  clienteRepository.findById(id).map(clienteAtualizado -> {
            clienteAtualizado.setIdDadosPessoais(cliente.getIdDadosPessoais());
            clienteAtualizado.setIdEndereco(cliente.getIdEndereco());
            clienteAtualizado.setIdPedido(cliente.getIdPedido());
            return clienteRepository.save(clienteAtualizado);
        }).orElseThrow(() ->
                new ResourceNotFoundException("Não há cliente com o id " +id));
    }

    @ApiOperation(value = "Deleta um cliente",
            notes = "Deleta um cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente deletado com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @DeleteMapping("/clientes/delete/{id}")
    public void delete(@PathVariable Long id){
        clienteRepository.deleteById(id);
    }
}
