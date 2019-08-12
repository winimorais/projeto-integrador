package org.generation.brazil.ecommerce.dadospessoais;

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
public class DadosPessoaisController {

    @Autowired
    DadosPessoaisRepository dadosPessoaisRepository;


    @ApiOperation(value = "Cadastra novos dados pessoais",
            notes = "Cadastra novos dados pessoais",
            response = DadosPessoais.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Dados pessoais cadastrados com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @PostMapping("/dados-pessoais")
    @ResponseStatus(HttpStatus.CREATED)
    public DadosPessoais save(@RequestBody DadosPessoais cliente) {
        return dadosPessoaisRepository.save(cliente);
    }


    @ApiOperation(value = "Lista todos os dados pessoais",
            notes = "Lista todos os dados pessoais",
            response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Dados pessoais listados com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @GetMapping("/dados-pessoais")
    public List<DadosPessoais> findAll() {
        return dadosPessoaisRepository.findAll();
    }

    @ApiOperation(value = "Busca os dados pessoais, utilizando seu id",
            notes = "Busca os dados pessoais, utilizando seu id",
            response = Optional.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Dados pessoais buscados com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @GetMapping("/dados-pessoais/{id}")
    public Optional<DadosPessoais> findById(@PathVariable Long id) throws ResourceNotFoundException {
        if (dadosPessoaisRepository.findById(id).isPresent()) {
            return dadosPessoaisRepository.findById(id);
        } else throw new ResourceNotFoundException("Não há cliente com o id " + id);
    }

    @ApiOperation(value = "Atualiza os dados pessoais, utilizando o id",
            notes = "Atualiza os dados pessoais, utilizando o id",
            response = DadosPessoais.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Dados pessoais atualizados com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @PutMapping("/dados-pessoais/{id}")
    public DadosPessoais update(@PathVariable Long id, @RequestBody DadosPessoais dadosPessoais) throws ResourceNotFoundException{
        return  dadosPessoaisRepository.findById(id).map(dadosPessoaisAtualizados -> {
            dadosPessoaisAtualizados.setNome(dadosPessoais.getNome());
            dadosPessoaisAtualizados.setSobrenome(dadosPessoais.getSobrenome());
            dadosPessoaisAtualizados.setCpf(dadosPessoais.getCpf());
            dadosPessoaisAtualizados.setTelefone(dadosPessoais.getTelefone());
            dadosPessoaisAtualizados.setEmail(dadosPessoais.getEmail());
            return dadosPessoaisRepository.save(dadosPessoaisAtualizados);
        }).orElseThrow(() ->
                new ResourceNotFoundException("Não há dados pessoais com o id " +id));
    }

    @ApiOperation(value = "Deleta os dados pessoais, utilizando o id",
            notes = "Deleta os dados pessoais, utilizando o id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Dados pessoais deletados com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @DeleteMapping("/dados-pessoais/delete/{id}")
    public void delete(@PathVariable Long id){
        dadosPessoaisRepository.deleteById(id);
    }
}
