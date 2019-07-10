package br.com.ecommerce.ecommerce.cliente;

import br.com.ecommerce.ecommerce.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cliente")
    public Cliente save(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @GetMapping("/clientes")
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @GetMapping("/cliente/{id}")
    public Optional<Cliente> findById(@PathVariable Long id) throws ResourceNotFoundException {
        if (clienteRepository.findById(id).isPresent()) {
            return clienteRepository.findById(id);
        } else throw new ResourceNotFoundException("Não há cliente com o id " + id);
    }

    @PutMapping("/cliente/{id}")
    public Cliente update(@PathVariable Long id, @RequestBody Cliente cliente) throws ResourceNotFoundException{
        return  clienteRepository.findById(id).map(clienteAtualizado -> {
            clienteAtualizado.setIdDadosPessoais(cliente.getIdDadosPessoais());
            clienteAtualizado.setIdEndereco(cliente.getIdEndereco());
            clienteAtualizado.setIdPedido(cliente.getIdPedido());
            return clienteRepository.save(clienteAtualizado);
        }).orElseThrow(() ->
                new ResourceNotFoundException("Não há cliente com o id " +id));
    }

    @DeleteMapping("/cliente/delete")
    public void delete(@RequestParam Long id){
        clienteRepository.deleteById(id);
    }
}
