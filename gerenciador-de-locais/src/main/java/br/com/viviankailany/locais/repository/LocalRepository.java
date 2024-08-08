package br.com.viviankailany.locais.repository;

import br.com.viviankailany.locais.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalRepository extends JpaRepository<Local, Long> {
    List<Local> findAllByOrderByDataCriacaoAsc();
}