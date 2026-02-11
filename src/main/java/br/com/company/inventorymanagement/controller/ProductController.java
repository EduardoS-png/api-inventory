package br.com.company.inventorymanagement.controller;

import br.com.company.inventorymanagement.business.dto.product.ProductCreateDTO;
import br.com.company.inventorymanagement.business.dto.product.ProductDTO;
import br.com.company.inventorymanagement.business.mapper.ProductMapper;
import br.com.company.inventorymanagement.business.services.ProductService;
import br.com.company.inventorymanagement.infrastructure.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper mapper;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductCreateDTO dto) {
        Product product = mapper.toEntity(dto);
        Product saved = productService.saveProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> list = productService.findAllProducts()
                .stream()
                .map(mapper::toDTO)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        Product product = productService.findByIdProduct(id);
        return ResponseEntity.ok(mapper.toDTO(product));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteByIdProduct(id);
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Cannot delete product because it is associated with raw materials.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting product.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id,
                                                    @RequestBody ProductCreateDTO dto) {
        Product updatedProduct = productService.updateProduct(id, dto);
        return ResponseEntity.ok(mapper.toDTO(updatedProduct));
    }
}
