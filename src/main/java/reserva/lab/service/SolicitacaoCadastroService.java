package reserva.lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reserva.lab.dto.SolicitacaoCadastroDTO;
import reserva.lab.dto.UsuarioDTO;
import reserva.lab.model.SolicitacaoCadastro;
import reserva.lab.model.Status;
import reserva.lab.model.Usuario;
import reserva.lab.repository.SolicitacaoCadastroRepository;
import reserva.lab.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitacaoCadastroService {

    @Autowired
    private SolicitacaoCadastroRepository solicitacaoCadastroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public SolicitacaoCadastroDTO criarSolicitacao(SolicitacaoCadastroDTO solicitacaoDTO) {
        SolicitacaoCadastro solicitacao = convertToEntity(solicitacaoDTO);
        SolicitacaoCadastro novaSolicitacao = solicitacaoCadastroRepository.save(solicitacao);
        return convertToDTO(novaSolicitacao);
    }


    public List<SolicitacaoCadastroDTO> listarSolicitacoesPendentes() {
        List<SolicitacaoCadastro> pendentes = solicitacaoCadastroRepository.findByStatus(Status.PENDENTE);
        return pendentes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    public SolicitacaoCadastroDTO buscarSolicitacaoPorId(int id) {
        SolicitacaoCadastro solicitacao = solicitacaoCadastroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));
        return convertToDTO(solicitacao);
    }


    public List<SolicitacaoCadastroDTO> listarTodasSolicitacoes() {
        List<SolicitacaoCadastro> solicitacoes = solicitacaoCadastroRepository.findAll();
        return solicitacoes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public UsuarioDTO aprovarSolicitacao(int id, UsuarioDTO administradorDTO) {

        if (administradorDTO == null || !administradorDTO.isAdministrador()) {
            throw new RuntimeException("Apenas administradores podem aprovar solicitações");
        }

        SolicitacaoCadastro solicitacao = solicitacaoCadastroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));

        if (solicitacao.getStatus() != Status.PENDENTE) {
            throw new RuntimeException("Solicitação já foi processada");
        }

        solicitacao.setStatus(Status.APROVADA);
        solicitacaoCadastroRepository.save(solicitacao);

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(solicitacao.getNome());
        novoUsuario.setEmail(solicitacao.getEmail());
        novoUsuario.setSenha(solicitacao.getSenha());

        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);
        return convertToDTO(usuarioSalvo);
    }

    public void recusarSolicitacao(int id, UsuarioDTO administradorDTO) {

        if (administradorDTO == null || !administradorDTO.isAdministrador()) {
            throw new RuntimeException("Apenas administradores podem recusar solicitações");
        }

        SolicitacaoCadastro solicitacao = solicitacaoCadastroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));

        if (solicitacao.getStatus() != Status.PENDENTE) {
            throw new RuntimeException("Solicitação já foi processada");
        }

        solicitacao.setStatus(Status.RECUSADA);
        solicitacaoCadastroRepository.save(solicitacao);
    }

    private SolicitacaoCadastro convertToEntity(SolicitacaoCadastroDTO dto) {
        SolicitacaoCadastro solicitacao = new SolicitacaoCadastro();
        solicitacao.setNome(dto.getNome());
        solicitacao.setEmail(dto.getEmail());
        solicitacao.setSenha(dto.getSenha());

        if (dto.getStatus() != null) {
            solicitacao.setStatus(Status.valueOf(dto.getStatus()));
        } else {
            solicitacao.setStatus(Status.PENDENTE);
        }

        if (dto.getDataSolicitacao() != null) {
            solicitacao.setDataSolicitacao(dto.getDataSolicitacao());
        } else {
            solicitacao.setDataSolicitacao(LocalDateTime.now());
        }

        return solicitacao;
    }

    private SolicitacaoCadastroDTO convertToDTO(SolicitacaoCadastro solicitacao) {
        SolicitacaoCadastroDTO dto = new SolicitacaoCadastroDTO();
        dto.setId(solicitacao.getId());
        dto.setNome(solicitacao.getNome());
        dto.setEmail(solicitacao.getEmail());
        dto.setSenha(solicitacao.getSenha());
        dto.setStatus(solicitacao.getStatus().name());
        dto.setDataSolicitacao(solicitacao.getDataSolicitacao());
        return dto;
    }

    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setSenha(usuario.getSenha());
        return dto;
    }
}