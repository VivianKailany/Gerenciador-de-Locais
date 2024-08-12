package br.com.viviankailany.locais.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Entidade que representa um local. Contém informacoes sobre o nome,
 * bairro, cidade, estado e as datas de criacao e atualizacao do registro.
 */

@Entity
@Data
public class Local {
    /**
     * Identificador único do local. Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do local.
     * <p>
     * O nome é obrigatório e deve ter no máximo 100 caracteres.
     */
    @NotBlank(message = "O nome do local é obrigatório.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    private String nome;

    /**
     * Nome do bairro onde o local está situado.
     * <p>
     * O bairro é obrigatório e deve ter no máximo 255 caracteres.
     */
    @NotBlank(message = "O nome do bairro é obrigatório.")
    @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres.")
    private String bairro;

    /**
     * Nome da cidade onde o local está situado.
     * <p>
     * A cidade é obrigatória.
     */
    @NotBlank(message = "O nome da cidade é obrigatório.")
    @Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres.")
    private String cidade;

    /**
     * Nome do estado onde o local está situado.
     * <p>
     * O estado é obrigatório.
     */
    @NotBlank(message = "O nome do estado é obrigatório.")
    @Size(max = 100, message = "O nome do estado deve ter no máximo 100 caracteres.")
    private String estado;

    /**
     * Data e hora da criação do registro.
     * <p>
     * Este campo é preenchido automaticamente quando o registro é criado.
     */
    @CreationTimestamp
    private LocalDateTime dataCriacao;

    /**
     * Data e hora da última atualização do registro.
     * <p>
     * Este campo deve ser atualizado manualmente quando o registro for modificado.
     */
    private LocalDateTime dataAtualizacao;


}
