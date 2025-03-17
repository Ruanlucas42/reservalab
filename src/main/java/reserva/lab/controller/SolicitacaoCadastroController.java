package reserva.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reserva.lab.dto.SolicitacaoCadastroDTO;
import reserva.lab.dto.UsuarioDTO;
import reserva.lab.service.SolicitacaoCadastroService;

import java.util.List;

@RestController
@RequestMapping("/solicitacoes-cadastro")
public class SolicitacaoCadastroController {

    @Autowired
    private SolicitacaoCadastroService solicitacaoCadastroService;

    @PostMapping
    public ResponseEntity<SolicitacaoCadastroDTO> criarSolicitacao(@RequestBody SolicitacaoCadastroDTO solicitacaoDTO) {
        try {
            SolicitacaoCadastroDTO novaSolicitacao = solicitacaoCadastroService.criarSolicitacao(solicitacaoDTO);
            return new ResponseEntity<>(novaSolicitacao, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/aprovar")
    public ResponseEntity<?> aprovarSolicitacao(@PathVariable int id, @RequestBody UsuarioDTO administradorDTO) {
        try {
            UsuarioDTO novoUsuario = solicitacaoCadastroService.aprovarSolicitacao(id, administradorDTO);
            return new ResponseEntity<>(novoUsuario, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/{id}/recusar")
    public ResponseEntity<?> recusarSolicitacao(@PathVariable int id, @RequestBody UsuarioDTO administradorDTO) {
        try {
            solicitacaoCadastroService.recusarSolicitacao(id, administradorDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<SolicitacaoCadastroDTO>> listarSolicitacoesPendentes() {
        List<SolicitacaoCadastroDTO> pendentes = solicitacaoCadastroService.listarSolicitacoesPendentes();
        return ResponseEntity.ok(pendentes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoCadastroDTO> buscarSolicitacaoPorId(@PathVariable int id) {
        SolicitacaoCadastroDTO solicitacao = solicitacaoCadastroService.buscarSolicitacaoPorId(id);
        return ResponseEntity.ok(solicitacao);
    }

    @GetMapping
    public ResponseEntity<List<SolicitacaoCadastroDTO>> listarTodasSolicitacoes() {
        List<SolicitacaoCadastroDTO> solicitacoes = solicitacaoCadastroService.listarTodasSolicitacoes();
        return ResponseEntity.ok(solicitacoes);
    }
}