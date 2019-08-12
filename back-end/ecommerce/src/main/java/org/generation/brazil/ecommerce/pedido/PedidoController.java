package org.generation.brazil.ecommerce.pedido;

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
public class PedidoController {

    @Autowired
    PedidoRepository pedidoRepository;

    @ApiOperation(value = "Cadastra um novo pedido",
            notes = "Cadastra um novo pedido",
            response = Pedido.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pedido cadastrado com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @PostMapping("/pedidos")
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido save(@RequestBody Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @ApiOperation(value = "Lista todos os pedidos",
            notes = "Lista todos os pedidos",
            response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pedidos listados com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @GetMapping("/pedidos")
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @ApiOperation(value = "Busca um pedido, utilizando seu id",
            notes = "Busca um pedido, utilizando seu id",
            response = Pedido.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pedido buscado com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @GetMapping("/pedidos/{id}")
    public Optional<Pedido> findById(@PathVariable Long id) throws ResourceNotFoundException {
        if (pedidoRepository.findById(id).isPresent()) {
            return pedidoRepository.findById(id);
        } else throw new ResourceNotFoundException("Não há pedido com o id " + id);
    }

    @ApiOperation(value = "Atualiza um pedido, utilizando seu id",
            notes = "Atualiza um pedido, utilizando seu id",
            response = Pedido.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pedido atualizado com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @PutMapping("/pedidos/{id}")
    public Pedido update(@PathVariable Long id, @RequestBody Pedido pedido) throws ResourceNotFoundException{
        return  pedidoRepository.findById(id).map(pedidoAtualizado -> {
            pedidoAtualizado.setPrecoTotal(pedido.getPrecoTotal());
            pedidoAtualizado.setFrete(pedido.getFrete());
            pedidoAtualizado.setIdProduto(pedido.getIdProduto());
            pedidoAtualizado.setNumeroPedido(pedido.getNumeroPedido());
            return pedidoRepository.save(pedidoAtualizado);
        }).orElseThrow(() ->
                new ResourceNotFoundException("Não há pedido com o id " +id));
    }

    @ApiOperation(value = "Deleta um pedido",
            notes = "Deleta um pedido")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pedido deletado com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @DeleteMapping("/pedidos/delete/{id}")
    public void delete(@PathVariable Long id){
        pedidoRepository.deleteById(id);
    }
}
