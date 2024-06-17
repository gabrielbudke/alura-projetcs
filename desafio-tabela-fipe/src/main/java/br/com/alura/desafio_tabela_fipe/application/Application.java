package br.com.alura.desafio_tabela_fipe.application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import br.com.alura.desafio_tabela_fipe.entity.DataDto;
import br.com.alura.desafio_tabela_fipe.entity.ModelDto;
import br.com.alura.desafio_tabela_fipe.entity.VehicleDto;
import br.com.alura.desafio_tabela_fipe.services.FipeService;
import br.com.alura.desafio_tabela_fipe.util.VehicleMapper;

public class Application {

  private static final String BASE_URL = "https://parallelum.com.br/fipe/api/v1";

  public static void run() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Qual a categoria do veículo? (carros, motos, ou caminhoes): ");
    String category = scanner.nextLine();

    FipeService fipeService = new FipeService();
    VehicleMapper vehicleMapper = new VehicleMapper();

    String url = BASE_URL + "/" + category + "/marcas";

    try {
      String brandsJSON = fipeService.getInfo(url);
      List<DataDto> brandsDto = vehicleMapper.toList(brandsJSON, DataDto.class);

      brandsDto.stream()
          .sorted(Comparator.comparing(DataDto::codigo))
          .forEach(brand -> System.out.println("Cód: " + brand.codigo() + " | Descrição: " + brand.nome()));

      System.out.print("Digite o código da marca escolhida: ");
      int brandCode = scanner.nextInt();
      System.out.println();

      url = url + "/" + brandCode + "/modelos";
      String modelsJSON = fipeService.getInfo(url);
      ModelDto modelDto = vehicleMapper.toDto(modelsJSON, ModelDto.class);
      modelDto.modelos().stream()
          .sorted(Comparator.comparing(DataDto::codigo))
          .forEach(model -> System.out.println("Cód " + model.codigo() + " | Descrição: " + model.nome()));

      scanner.nextLine();
      System.out.print("Digite um trecho do nome do veículo: ");
      String search = scanner.nextLine();
      modelDto.modelos().stream()
          .filter(model -> model.nome().toLowerCase().contains(search.toLowerCase()))
          .forEach(model -> System.out.println("Cód " + model.codigo() + " | Descrição: " + model.nome()));

      System.out.print("Digite o código do veículo: ");
      int vehicleCode = scanner.nextInt();

      url = url + "/" + vehicleCode + "/anos";
      String availableYearsJson = fipeService.getInfo(url);
      List<DataDto> availableYears = vehicleMapper.toList(availableYearsJson, DataDto.class);
      availableYears.forEach(System.out::println);

      List<VehicleDto> listVehicleDto = new ArrayList<>();
      for (DataDto dataDto : availableYears) {
        String vehicleInfo = fipeService.getInfo(url + "/" + dataDto.codigo());
        listVehicleDto.add(vehicleMapper.toDto(vehicleInfo, VehicleDto.class));
      }

      listVehicleDto.forEach(System.out::println);

      scanner.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
