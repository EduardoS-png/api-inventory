package br.com.company.inventorymanagement.business.dto.productrawmaterial;

public record ProductRawMaterialDTO(Long id,
                                    Long productId,
                                    Long rawMaterialId,
                                    String rawMaterialName,
                                    Double quantityRequired) {
}
