package com.barbara.crudusuario.controller;

import com.barbara.crudusuario.model.Usuario;
import com.barbara.crudusuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    public Usuario criarNovoUsuario(@RequestBody Usuario usuario){
        return repository.save(usuario);
    }

    @GetMapping
    public List<Usuario> listarTodosOsUsuarios(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuarioPorId(@PathVariable Long id){
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuarioPorId(@PathVariable Long id, @RequestBody Usuario novoUsuario){
        return repository.findById(id).map(usuario -> {
            usuario.setNome(novoUsuario.getNome());
            usuario.setEmail(novoUsuario.getEmail());
            return repository.save(usuario);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuarioPorId(@PathVariable Long id){
        repository.deleteById(id);
    }
}
