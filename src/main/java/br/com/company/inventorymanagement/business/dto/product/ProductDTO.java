package br.com.company.inventorymanagement.business.dto.product;

import java.math.BigDecimal;

public record ProductDTO(Long id,
                         String name,
                         BigDecimal price) {
}
