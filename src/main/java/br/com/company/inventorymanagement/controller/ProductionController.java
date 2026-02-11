package br.com.company.inventorymanagement.controller;

import br.com.company.inventorymanagement.business.dto.productproduction.ProductProductionDTO;
import br.com.company.inventorymanagement.business.services.ProductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/production")
@RequiredArgsConstructor
public class ProductionController {

    private final ProductionService productionService;

    @GetMapping
    public ResponseEntity<List<ProductProductionDTO>> getProducibleProducts() {
        return ResponseEntity.ok(productionService.getProducibleProducts());
    }
}
