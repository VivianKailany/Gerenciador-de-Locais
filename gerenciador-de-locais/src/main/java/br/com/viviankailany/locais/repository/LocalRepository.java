package br.com.viviankailany.locais.repository;

import br.com.viviankailany.locais.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface de repositório para manipulação de entidades {@link Local}.
 * Extende {@link JpaRepository} para fornecer operações CRUD e consultas personalizadas.
 */
public interface LocalRepository extends JpaRepository<Local, Long> {

    /**
     * Encontra todos os locais e os ordena pela data de criação em ordem ascendente.
     *
     * @return uma lista de {@link Local} ordenada pela data de criação.
     */
    List<Local> findAllByOrderByDataCriacaoAsc();

    /**
     * Encontra todos os locais com o nome especificado.
     *
     * @param nome o nome do local a ser pesquisado.
     * @return uma lista de {@link Local} com o nome correspondente.
     */
    List<Local> findByNome(String nome);
}
