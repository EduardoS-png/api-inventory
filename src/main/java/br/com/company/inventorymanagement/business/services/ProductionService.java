package br.com.company.inventorymanagement.business.services;

import br.com.company.inventorymanagement.business.dto.productproduction.ProductProductionDTO;
import br.com.company.inventorymanagement.exceptions.BusinessException;
import br.com.company.inventorymanagement.infrastructure.model.Product;
import br.com.company.inventorymanagement.infrastructure.model.ProductRawMaterial;
import br.com.company.inventorymanagement.infrastructure.repository.ProductRawMaterialRepository;
import br.com.company.inventorymanagement.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductionService {

    private final ProductRepository productRepository;
    private final ProductRawMaterialRepository productRawMaterialRepository;

    @Transactional(readOnly = true)
    public List<ProductProductionDTO> getProducibleProducts() {

        return productRepository.findAll()
                .stream()
                .map(this::calculateProduction)
                .toList();
    }

    private ProductProductionDTO calculateProduction(Product product) {

        List<ProductRawMaterial> materials =
                productRawMaterialRepository.findByProductId(product.getId());

        if (materials.isEmpty()) {
            return new ProductProductionDTO(product.getId(), product.getName(), 0);
        }

        int possibleQuantity = materials.stream()
                .mapToInt(this::calculateMaterialContribution)
                .min()
                .orElse(0);

        return new ProductProductionDTO(product.getId(), product.getName(), possibleQuantity);
    }

    private int calculateMaterialContribution(ProductRawMaterial productRawMaterial) {

        Double required = productRawMaterial.getQuantityRequired();
        BigDecimal stock = productRawMaterial.getRawMaterial().getStockQuantity();

        if (required == null || required <= 0) {
            throw new BusinessException("Invalid required quantity.");
        }

        if (stock == null) {
            throw new BusinessException("Stock cannot be null.");
        }

        return stock
                .divide(BigDecimal.valueOf(required), RoundingMode.DOWN)
                .intValue();

    }
}
