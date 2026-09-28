package br.com.fatec.ninjas.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "cla")
@Valid
public class Cla {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cla")
    private Long id;

    @Column(name = "nome_cla", nullable = false)
    @NotBlank(message = "O nome do clã é obrigatório.")
    @Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres.")
    private String nome;

    @Column(name = "descricao_cla", nullable = false)
    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    @Column(name = "habilidade_cla", nullable = false)
    @NotBlank(message = "A habilidade é obrigatória.")
    private String habilidade;
}
