package br.com.alura.desafio_tabela_fipe.util;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class VehicleMapper implements IVehicleMapper {
  private ObjectMapper mapper = new ObjectMapper();

  @Override
  public <T> T toDto(String json, Class<T> clazz) {
    try {
      return mapper.readValue(json, clazz);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public <T> List<T> toList(String json, Class<T> clazz) {
    try {
      return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
