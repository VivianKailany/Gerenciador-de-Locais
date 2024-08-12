package br.com.viviankailany.locais.exception;

/**
 * Exceção lançada quando um local não é encontrado.
 * Esta exceção é usada para indicar que um local com um determinado ID não foi encontrado no sistema.
 */
public class LocalNotFoundException extends RuntimeException {

    /**
     * Constrói uma nova instância de {@code LocalNotFoundException} com uma mensagem detalhada.
     *
     * @param id o ID do local que não foi encontrado
     */
    public LocalNotFoundException(Long id) {
        super("Local não encontrado com o ID " + id);
    }
}
