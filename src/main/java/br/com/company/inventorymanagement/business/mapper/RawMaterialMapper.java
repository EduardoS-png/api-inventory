package br.com.company.inventorymanagement.business.mapper;

import br.com.company.inventorymanagement.business.dto.rawmaterial.RawMaterialCreateDTO;
import br.com.company.inventorymanagement.business.dto.rawmaterial.RawMaterialDTO;
import br.com.company.inventorymanagement.infrastructure.model.RawMaterial;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RawMaterialMapper {

    RawMaterial toEntity(RawMaterialCreateDTO dto);

    RawMaterialDTO toDTO(RawMaterial rawMaterial);

    List<RawMaterialDTO> toDTOList(List<RawMaterial> rawMaterials);
}
