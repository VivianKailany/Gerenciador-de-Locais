package br.com.viviankailany.locais.service;

import br.com.viviankailany.locais.exception.LocalNotFoundException;
import br.com.viviankailany.locais.model.Local;
import br.com.viviankailany.locais.repository.LocalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Serviço para gerenciar operações relacionadas aos locais.
 * Fornece métodos para criar, atualizar, buscar e deletar locais.
 */
@Service
public class LocalService {

    private final LocalRepository localRepository;

    /**
     * Construtor para inicializar o {@link LocalRepository}.
     *
     * @param localRepository o repositório de locais
     */
    public LocalService(LocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    /**
     * Salva um novo local.
     *
     * @param local o local a ser salvo
     * @return o local salvo
     */
    @Transactional
    public Local salvar(Local local) {
        local.setDataAtualizacao(LocalDateTime.now());
        return localRepository.save(local);
    }

    /**
     * Lista todos os locais ordenados pela data de criação.
     *
     * @return uma lista de locais ordenados pela data de criação
     */
    public List<Local> listarDataCriacao() {
        return localRepository.findAllByOrderByDataCriacaoAsc();
    }

    /**
     * Busca um local pelo seu identificador.
     *
     * @param id o identificador do local
     * @return um {@link Optional} contendo o local se encontrado, ou vazio se não encontrado
     */
    public Optional<Local> buscarPorId(Long id) {
        return localRepository.findById(id);
    }

    /**
     * Busca locais pelo nome.
     *
     * @param nome o nome do local
     * @return uma lista de locais que correspondem ao nome fornecido
     */
    public List<Local> buscaPorNome(String nome) {
        return localRepository.findByNome(nome);
    }

    /**
     * Atualiza um local existente.
     *
     * @param id o identificador do local a ser atualizado
     * @param localAtualizado o local com as informações atualizadas
     * @return o local atualizado
     * @throws LocalNotFoundException se o local com o identificador fornecido não for encontrado
     */
    @Transactional
    public Local atualizar(Long id, Local localAtualizado) {
        Optional<Local> optionalLocal = localRepository.findById(id);
        if (optionalLocal.isPresent()) {
            Local local = optionalLocal.get();
            local.setNome(localAtualizado.getNome());
            local.setBairro(localAtualizado.getBairro());
            local.setCidade(localAtualizado.getCidade());
            local.setEstado(localAtualizado.getEstado());
            local.setDataAtualizacao(LocalDateTime.now());
            return localRepository.save(local);
        }
        throw new LocalNotFoundException(id);
    }

    /**
     * Deleta um local pelo seu identificador.
     *
     * @param id o identificador do local a ser deletado
     */
    @Transactional
    public void deletarPorId(Long id) {
        localRepository.deleteById(id);
    }

    /**
     * Deleta locais pelo nome.
     *
     * @param nome o nome dos locais a serem deletados
     */
    @Transactional
    public void deletarPorNome(String nome) {
        List<Local> locaisParaDeletar = localRepository.findByNome(nome);
        localRepository.deleteAll(locaisParaDeletar);
    }
}
