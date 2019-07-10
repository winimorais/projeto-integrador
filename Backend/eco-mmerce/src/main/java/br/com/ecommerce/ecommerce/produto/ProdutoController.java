package br.com.ecommerce.ecommerce.produto;

import br.com.ecommerce.ecommerce.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/produto")
    public Produto save(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @GetMapping("/produtos")
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @GetMapping("/produto/{id}")
    public Optional<Produto> findById(@PathVariable Long id) throws ResourceNotFoundException {
        if (produtoRepository.findById(id).isPresent()) {
            return produtoRepository.findById(id);
        } else throw new ResourceNotFoundException("Não há produto com o id " + id);
    }

    @PutMapping("/produto/{id}")
    public Produto update(@PathVariable Long id, @RequestBody Produto produto) throws ResourceNotFoundException {
        return produtoRepository.findById(id).map(produtoAtualizado -> {
            produtoAtualizado.setNome(produto.getNome());
            produtoAtualizado.setDescricao(produto.getDescricao());
            produtoAtualizado.setPreco((produto.getPreco()));
            return produtoRepository.save(produtoAtualizado);
        }).orElseThrow(() ->
                new ResourceNotFoundException("Não há produto com o id " + id));

    }

    @DeleteMapping("/produto/delete")
    public void delete(@RequestParam Long id){
        produtoRepository.deleteById(id);
    }

}

