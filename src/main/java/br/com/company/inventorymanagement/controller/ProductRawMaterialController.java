package br.com.company.inventorymanagement.controller;

import br.com.company.inventorymanagement.business.dto.productrawmaterial.ProductRawMaterialCreateDTO;
import br.com.company.inventorymanagement.business.dto.productrawmaterial.ProductRawMaterialDTO;
import br.com.company.inventorymanagement.business.services.ProductRawMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/{productId}/materials")
@RequiredArgsConstructor
public class ProductRawMaterialController {

    private final ProductRawMaterialService service;

    @GetMapping
    public ResponseEntity<List<ProductRawMaterialDTO>> findByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(service.findByProduct(productId));
    }

    @PostMapping
    public ResponseEntity<ProductRawMaterialDTO> create(@PathVariable Long productId,
                                                        @RequestBody ProductRawMaterialCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAssociation(productId, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteProductRawMaterial(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductRawMaterialDTO> update(@PathVariable Long id,
                                                        @RequestBody ProductRawMaterialCreateDTO dto) {
        return ResponseEntity.ok(service.updateAssociation(id, dto));
    }
}
