package br.com.alura.desafio_tabela_fipe.application;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import br.com.alura.desafio_tabela_fipe.entity.BrandDTO;
import br.com.alura.desafio_tabela_fipe.entity.DataDTO;
import br.com.alura.desafio_tabela_fipe.entity.DataDto;
import br.com.alura.desafio_tabela_fipe.entity.ModelDto;
import br.com.alura.desafio_tabela_fipe.services.FipeService;
import br.com.alura.desafio_tabela_fipe.util.VehicleMapper;

public class Application {

  public static void run() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Qual a categoria do veículo? (carros, motos, ou caminhoes): ");
    String category = scanner.nextLine();

    FipeService fipeService = new FipeService();
    VehicleMapper vehicleMapper = new VehicleMapper();
    try {
      String brandsJSON = fipeService.getBrandsByCategory(category);
      List<DataDto> brandsDto = vehicleMapper.toList(brandsJSON, DataDto.class);

      brandsDto.stream()
          .sorted(Comparator.comparing(DataDto::codigo))
          .forEach(brand -> System.out.println("Cód: " + brand.codigo() + " | Descrição: " + brand.nome()));

      System.out.print("Digite o código do veículo escolhido: ");
      int brandCode = scanner.nextInt();

      String modelsJSON = fipeService.getModelByBrandCode(category, brandCode);
      List<ModelDto> modelsDto = vehicleMapper.toList(modelsJSON, ModelDto.class);
      System.out.println(modelsDto);
      // DataDTO dataDto = vehicleMapper.toDto(dataJSON, DataDTO.class);
      // dataDto.models().forEach(model -> System.out.println("Cód. " + model.codigo()
      // + " Descrição: " + model.nome()));
      // scanner.nextLine();
      // System.out.print("Digite um trecho do nome do veículo: ");
      // String search = scanner.nextLine();

      // System.out.println(search);
      // dataDto.models().stream()
      // .filter(model -> model.nome().toLowerCase().contains(search.toLowerCase()))
      // .forEach(model -> System.out.println("Cód. " + model.codigo() + " Descrição:
      // " + model.nome()));
      // System.out.print("Digite o código do veículo para consultar valores: ");

      scanner.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
