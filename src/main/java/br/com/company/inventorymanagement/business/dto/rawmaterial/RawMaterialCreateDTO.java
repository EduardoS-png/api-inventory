package br.com.company.inventorymanagement.business.dto.rawmaterial;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record RawMaterialCreateDTO(@NotBlank String name,
                                   @NotNull @PositiveOrZero BigDecimal stockQuantity) {
}
