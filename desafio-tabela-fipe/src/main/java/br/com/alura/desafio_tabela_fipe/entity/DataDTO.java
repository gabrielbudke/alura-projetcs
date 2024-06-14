package br.com.alura.desafio_tabela_fipe.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataDTO(List<DataDto> modelos) {
}
