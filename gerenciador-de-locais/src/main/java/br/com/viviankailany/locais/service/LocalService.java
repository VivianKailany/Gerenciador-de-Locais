package br.com.viviankailany.locais.service;

import br.com.viviankailany.locais.model.Local;
import br.com.viviankailany.locais.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LocalService {

    @Autowired
    private LocalRepository localRepository;

    public Local salvar(Local local) {
        local.setDataAtualizacao(LocalDateTime.now());
        return localRepository.save(local);
    }

    public List<Local> listar() {
        return localRepository.findAllByOrderByDataCriacaoAsc();
    }

    public Optional<Local> buscarPorId(Long id) {
        return localRepository.findById(id);
    }

    public void remover(Long id) {
        localRepository.deleteById(id);
    }
}
