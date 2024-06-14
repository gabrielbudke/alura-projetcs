package br.com.alura.desafio_tabela_fipe.util;

import java.util.List;

public interface IVehicleMapper {
  <T> List<T> toList(String json, Class<T> clazz);

  <T> T toDto(String json, Class<T> clazz);

}
