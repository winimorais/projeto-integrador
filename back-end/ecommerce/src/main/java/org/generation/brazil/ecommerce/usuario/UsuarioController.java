package org.generation.brazil.ecommerce.usuario;

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
@RequestMapping("/api/v1")
public class UsuarioController {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @ApiOperation(value = "Insere um novo usuário",
          notes = "Insere um novo usuário",
          response = Usuario.class)
  @ApiResponses(value = {
          @ApiResponse(code = 201, message = "Usuário inserido com sucesso!"),
          @ApiResponse(code = 401, message = "Sem autorização"),
          @ApiResponse(code = 403, message = "Proibido"),
          @ApiResponse(code = 404, message = "Não encontrado")
  })
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/usuarios")
  public Usuario save(@RequestBody Usuario usuario) {
    return usuarioRepository.save(usuario);
  }

  @ApiOperation(value = "Lista todos os usuários",
          notes = "Lista todos os usuários",
          response = List.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Usuários listados com Sucesso!"),
          @ApiResponse(code = 401, message = "Sem autorização"),
          @ApiResponse(code = 403, message = "Proibido"),
          @ApiResponse(code = 404, message = "Não encontrado")
  })

  @GetMapping("/usuarios")
  public List<Usuario> findAll() {
    return usuarioRepository.findAll();
  }

  @ApiOperation(value = "Lista um usuário específico",
          notes = "Lista usuário específico",
          response = Optional.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Usuário listado com Sucesso!"),
          @ApiResponse(code = 401, message = "Sem autorização"),
          @ApiResponse(code = 403, message = "Proibido"),
          @ApiResponse(code = 404, message = "Não encontrado")
  })
  @GetMapping("/usuarios/{id}")
  public Optional<Usuario> findById(@PathVariable Long id) throws ResourceNotFoundException {
    if (usuarioRepository.findById(id).isPresent()) {
      return usuarioRepository.findById(id);
    } else throw new ResourceNotFoundException("Não há usuário registrado com o id " + id);
  }

  @ApiOperation(value = "Atualiza os dados do usuário",
          notes = "Atualiza os dados do usuário",
          response = Usuario.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Usuário atualizado com sucesso!"),
          @ApiResponse(code = 401, message = "Sem autorização"),
          @ApiResponse(code = 403, message = "Proibido"),
          @ApiResponse(code = 404, message = "Não encontrado")
  })
  @PutMapping("/usuarios/{id}")
  public Usuario update(@PathVariable Long id, @RequestBody Usuario usuario) throws ResourceNotFoundException {
    return usuarioRepository.findById(id).map(usuarioAtualizado -> {
      usuarioAtualizado.setLogin(usuario.getLogin());
      usuarioAtualizado.setSenha(usuario.getSenha());
      return usuarioRepository.save(usuarioAtualizado);
    }).orElseThrow(() ->
        new ResourceNotFoundException("Não há usuário registrado com o id " + id));

  }

  @ApiOperation(value = "Deleta um usuário",
          notes = "Deleta um usuário",
          response = List.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Usuário deletado com sucesso!"),
          @ApiResponse(code = 401, message = "Sem autorização"),
          @ApiResponse(code = 403, message = "Proibido"),
          @ApiResponse(code = 404, message = "Não encontrado")
  })
  @DeleteMapping("/usuarios/")
  public void delete(@RequestParam Long id){
    usuarioRepository.deleteById(id);
  }

}
