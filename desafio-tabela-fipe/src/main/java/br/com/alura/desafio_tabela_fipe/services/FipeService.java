package br.com.alura.desafio_tabela_fipe.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FipeService {

  private HttpClient client = HttpClient.newHttpClient();

  public String getInfo(String url) {
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      return response.body();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
