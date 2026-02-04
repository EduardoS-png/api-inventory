package br.com.company.inventorymanagement.dto.rawmaterial;

import java.math.BigDecimal;

public record RawMaterialDTO(Long id,
                             String name,
                             BigDecimal stockQuantity) {
}
