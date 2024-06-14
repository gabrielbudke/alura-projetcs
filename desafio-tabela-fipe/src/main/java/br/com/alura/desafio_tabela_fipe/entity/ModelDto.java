package br.com.alura.desafio_tabela_fipe.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ModelDto(List<DataDto> modelos) {

}
