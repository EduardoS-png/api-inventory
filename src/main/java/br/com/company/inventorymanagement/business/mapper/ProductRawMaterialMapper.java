package br.com.company.inventorymanagement.business.mapper;

import br.com.company.inventorymanagement.business.dto.productrawmaterial.ProductRawMaterialCreateDTO;
import br.com.company.inventorymanagement.business.dto.productrawmaterial.ProductRawMaterialDTO;
import br.com.company.inventorymanagement.infrastructure.model.ProductRawMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductRawMaterialMapper {

    @Mapping(source = "rawMaterial.id", target = "rawMaterialId")
    @Mapping(source = "rawMaterial.name", target = "rawMaterialName")
    ProductRawMaterialDTO toDTO(ProductRawMaterial entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "rawMaterial", ignore = true)
    ProductRawMaterial toEntity(ProductRawMaterialCreateDTO dto);
}
