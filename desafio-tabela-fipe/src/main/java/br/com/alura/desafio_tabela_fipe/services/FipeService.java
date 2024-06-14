package br.com.alura.desafio_tabela_fipe.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import io.micrometer.core.ipc.http.HttpSender.Response;

public class FipeService {

  private final String BASE_URL = "https://parallelum.com.br/fipe/api/v1";

  private HttpClient client = HttpClient.newHttpClient();

  public String getBrandsByCategory(String category) {
    String url = BASE_URL + "/" + category + "/marcas";
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      return response.body();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public String getModelByBrandCode(String category, int brandCode) {
    String url = BASE_URL + "/" + category + "/marcas/" + brandCode + "/modelos";
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      return response.body();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
