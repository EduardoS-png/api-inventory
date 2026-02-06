package br.com.company.inventorymanagement.business.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductCreateDTO(@NotBlank String name,
                               @NotNull @Positive BigDecimal price) {
}
