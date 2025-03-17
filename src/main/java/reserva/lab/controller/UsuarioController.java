package reserva.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import reserva.lab.service.UsuarioService;
import reserva.lab.dto.UsuarioDTO;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    public UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO novoUsuarioDTO = usuarioService.criarUsuario(usuarioDTO);
        return new ResponseEntity<>(novoUsuarioDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        List<UsuarioDTO> usuarioDTOs = usuarioService.listarUsuarios();
        return new ResponseEntity<>(usuarioDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable int id) {
        return usuarioService.buscarUsuarioPorId(id)
                .map(usuarioDTO -> new ResponseEntity<>(usuarioDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable int id, @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioAtualizadoDTO = usuarioService.atualizarUsuario(id, usuarioDTO);
        return new ResponseEntity<>(usuarioAtualizadoDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable int id) {
        usuarioService.deletarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
