package br.com.company.inventorymanagement.business.dto.productproduction;

public record ProductProductionDTO(Long productId,
                                   String productName,
                                   Integer possibleQuantity) {
}
