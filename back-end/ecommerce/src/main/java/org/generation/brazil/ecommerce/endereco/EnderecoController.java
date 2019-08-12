package org.generation.brazil.ecommerce.endereco;

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
public class EnderecoController {

    @Autowired
    EnderecoRepository enderecoRepository;

    @ApiOperation(value = "Cadastra um novo endereço",
            notes = "Cadastra um novo endereço",
            response = Endereco.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço cadastrado com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @PostMapping("/enderecos")
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco save(@RequestBody Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @ApiOperation(value = "Lista todos os endereços",
            notes = "Lista todos os endereços",
            response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereços listados com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @GetMapping("/enderecos")
    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    @ApiOperation(value = "Busca um endereço, utilizando seu id",
            notes = "Busca um endereço, utilizando seu id",
            response = Optional.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço buscado com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @GetMapping("/enderecos/{id}")
    public Optional<Endereco> findById(@PathVariable Long id) throws ResourceNotFoundException {
        if (enderecoRepository.findById(id).isPresent()) {
            return enderecoRepository.findById(id);
        } else throw new ResourceNotFoundException("Não há endereço com o id " + id);
    }

    @ApiOperation(value = "Atualiza um endereço, utilizando seu id",
            notes = "Atualiza um endereço, utilizando seu id",
            response = Endereco.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço atualizado com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @PutMapping("/enderecos/{id}")
    public Endereco update(@PathVariable Long id, @RequestBody Endereco endereco) throws ResourceNotFoundException{
        return  enderecoRepository.findById(id).map(enderecoAtualizado -> {
            enderecoAtualizado.setLogradouro(endereco.getLogradouro());
            enderecoAtualizado.setNumero(endereco.getNumero());
            enderecoAtualizado.setCep(endereco.getCep());
            enderecoAtualizado.setBairro(endereco.getBairro());
            enderecoAtualizado.setCidade(endereco.getCidade());
            enderecoAtualizado.setEstado(endereco.getEstado());
            enderecoAtualizado.setComplemento(endereco.getComplemento());
            return enderecoRepository.save(enderecoAtualizado);
        }).orElseThrow(() ->
                new ResourceNotFoundException("Não há endereço com o id " +id));
    }

    @ApiOperation(value = "Deleta um endereço",
            notes = "Deleta um endereço")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço deletado com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @DeleteMapping("/enderecos/delete/{id}")
    public void delete(@PathVariable Long id){
        enderecoRepository.deleteById(id);
    }
}
