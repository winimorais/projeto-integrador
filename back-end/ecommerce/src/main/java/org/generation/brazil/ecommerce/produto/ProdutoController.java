package org.generation.brazil.ecommerce.produto;

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
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @ApiOperation(value = "Cadastra um novo produto",
            notes = "Cadastra um novo produto",
            response = Produto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Produto cadastrado com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @PostMapping("/produtos")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @ApiOperation(value = "Lista todos os produtos",
            notes = "Lista todos os produtos",
            response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produtos listados com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @GetMapping("/produtos")
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @ApiOperation(value = "Busca um produto, utilizando seu id",
            notes = "Busca um produto, utilizando seu id",
            response = Optional.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto buscado com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @GetMapping("/produtos/{id}")
    public Optional<Produto> findById(@PathVariable Long id) throws ResourceNotFoundException {
        if (produtoRepository.findById(id).isPresent()) {
            return produtoRepository.findById(id);
        } else throw new ResourceNotFoundException("Não há produto com o id " + id);
    }

    @ApiOperation(value = "Atualiza um produto, utilizando seu id",
            notes = "Atualiza um produto, utilizando seu id",
            response = Produto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto atualizado com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @PutMapping("/produtos/{id}")
    public Produto update(@PathVariable Long id, @RequestBody Produto produto) throws ResourceNotFoundException {
        return produtoRepository.findById(id).map(produtoAtualizado -> {
            produtoAtualizado.setNome(produto.getNome());
            produtoAtualizado.setDescricao(produto.getDescricao());
            produtoAtualizado.setPreco((produto.getPreco()));
            produtoAtualizado.setImagem(produto.getImagem());
            return produtoRepository.save(produtoAtualizado);
        }).orElseThrow(() ->
                new ResourceNotFoundException("Não há produto com o id " + id));
    }

    @ApiOperation(value = "Deleta um produto",
            notes = "Deleta um produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto deletado com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @DeleteMapping("/produtos/delete")
    public void delete(@RequestParam Long id){
        produtoRepository.deleteById(id);
    }

}

