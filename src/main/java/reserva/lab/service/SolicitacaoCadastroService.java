package reserva.lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reserva.lab.model.SolicitacaoCadastro;
import reserva.lab.model.Status;
import reserva.lab.model.Usuario;
import reserva.lab.repository.SolicitacaoCadastroRepository;
import reserva.lab.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoCadastroService {

    @Autowired
    private SolicitacaoCadastroRepository solicitacaoCadastroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Criar uma nova solicitação de cadastro
    public SolicitacaoCadastro criarSolicitacao(SolicitacaoCadastro solicitacao) {
        return solicitacaoCadastroRepository.save(solicitacao);
    }

    // Listar todas as solicitações pendentes
    public List<SolicitacaoCadastro> listarSolicitacoesPendentes() {
        return solicitacaoCadastroRepository.findByStatus(Status.PENDENTE);
    }

    // Buscar uma solicitação por ID
    public Optional<SolicitacaoCadastro> buscarPorId(int id) {
        return solicitacaoCadastroRepository.findById(id);
    }

    // Aprovar uma solicitação e criar um novo usuário
    public Usuario aprovarSolicitacao(int id) {
        SolicitacaoCadastro solicitacao = solicitacaoCadastroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));

        if (solicitacao.getStatus() != Status.PENDENTE) {
            throw new RuntimeException("Solicitação já foi processada");
        }

        solicitacao.setStatus(Status.APROVADA);
        solicitacaoCadastroRepository.save(solicitacao);

        // Criar um novo usuário com os dados da solicitação aprovada
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(solicitacao.getNome());
        novoUsuario.setEmail(solicitacao.getEmail());
        novoUsuario.setSenha(solicitacao.getSenha());

        return usuarioRepository.save(novoUsuario);
    }

    // Recusar uma solicitação
    public void recusarSolicitacao(int id) {
        SolicitacaoCadastro solicitacao = solicitacaoCadastroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));

        if (solicitacao.getStatus() != Status.PENDENTE) {
            throw new RuntimeException("Solicitação já foi processada");
        }

        solicitacao.setStatus(Status.RECUSADA);
        solicitacaoCadastroRepository.save(solicitacao);
    }
}
