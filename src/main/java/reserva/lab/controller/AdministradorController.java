package reserva.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reserva.lab.model.Administrador;
import reserva.lab.model.Usuario;
import reserva.lab.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

        @Autowired
        private UsuarioService usuarioService;

        @PostMapping
        public ResponseEntity<Administrador> criarAdministrador(@RequestBody Administrador administrador) {
            Administrador novoAdministrador = usuarioService.criarAdministrador(administrador);
            return new ResponseEntity<>(novoAdministrador, HttpStatus.CREATED);
        }


    @GetMapping
    public ResponseEntity<List<Administrador>> listarAdministradores() {
        List<Administrador> administradores = usuarioService.listarAdministradores();
        return new ResponseEntity<>(administradores, HttpStatus.OK);
    }



    @GetMapping("/{id}")
        public ResponseEntity<Usuario> buscarAdministradorPorId(@PathVariable int id) {
            return usuarioService.buscarUsuarioPorId(id)
                    .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Usuario> atualizarAdministrador(@PathVariable int id, @RequestBody Administrador administradorAtualizado) {
            Usuario administrador = usuarioService.atualizarUsuario(id, administradorAtualizado);
            return new ResponseEntity<>(administrador, HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletarAdministrador(@PathVariable int id) {
            usuarioService.deletarUsuario(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

