package reserva.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reserva.lab.model.SolicitacaoCadastro;
import reserva.lab.model.Usuario;
import reserva.lab.service.SolicitacaoCadastroService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solicitacoes-cadastro")
public class SolicitacaoCadastroController {

    @Autowired
    private SolicitacaoCadastroService solicitacaoCadastroService;

    // Criar uma nova solicitação de cadastro
    @PostMapping
    public ResponseEntity<SolicitacaoCadastro> criarSolicitacao(@RequestBody SolicitacaoCadastro solicitacao) {
        SolicitacaoCadastro novaSolicitacao = solicitacaoCadastroService.criarSolicitacao(solicitacao);
        return ResponseEntity.ok(novaSolicitacao);
    }

    // Listar todas as solicitações pendentes
    @GetMapping("/pendentes")
    public ResponseEntity<List<SolicitacaoCadastro>> listarPendentes() {
        return ResponseEntity.ok(solicitacaoCadastroService.listarSolicitacoesPendentes());
    }

    // Buscar uma solicitação específica por ID
    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoCadastro> buscarPorId(@PathVariable int id) {
        Optional<SolicitacaoCadastro> solicitacao = solicitacaoCadastroService.buscarPorId(id);
        return solicitacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Aprovar uma solicitação de cadastro e criar o usuário
    @PostMapping("/{id}/aprovar")
    public ResponseEntity<Usuario> aprovarSolicitacao(@PathVariable int id) {
        Usuario novoUsuario = solicitacaoCadastroService.aprovarSolicitacao(id);
        return ResponseEntity.ok(novoUsuario);
    }

    // Recusar uma solicitação de cadastro
    @PostMapping("/{id}/recusar")
    public ResponseEntity<Void> recusarSolicitacao(@PathVariable int id) {
        solicitacaoCadastroService.recusarSolicitacao(id);
        return ResponseEntity.noContent().build();
    }
}
