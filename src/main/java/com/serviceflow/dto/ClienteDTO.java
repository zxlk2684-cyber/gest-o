package com.serviceflow.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String telefonSecundario;
    private String endereco;
    private String numeroEndereco;
    private String complementoEndereco;
    private String cidade;
    private String estado;
    private String cep;
    private String observacoes;

}
