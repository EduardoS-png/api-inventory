package br.com.company.inventorymanagement.mapper;

import br.com.company.inventorymanagement.dto.rawmaterial.RawMaterialCreateDTO;
import br.com.company.inventorymanagement.dto.rawmaterial.RawMaterialDTO;
import br.com.company.inventorymanagement.model.RawMaterial;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RawMaterialMapper {

    RawMaterial toEntity(RawMaterialCreateDTO dto);

    RawMaterialDTO toDTO(RawMaterial rawMaterial);

    List<RawMaterialDTO> toDTOList(List<RawMaterial> rawMaterials);
}
