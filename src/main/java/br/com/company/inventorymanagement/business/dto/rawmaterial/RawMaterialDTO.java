package br.com.company.inventorymanagement.business.dto.rawmaterial;

import java.math.BigDecimal;

public record RawMaterialDTO(Long id,
                             String name,
                             BigDecimal stockQuantity) {
}
