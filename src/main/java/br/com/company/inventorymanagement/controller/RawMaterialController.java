package br.com.company.inventorymanagement.controller;

import br.com.company.inventorymanagement.business.dto.rawmaterial.RawMaterialCreateDTO;
import br.com.company.inventorymanagement.business.dto.rawmaterial.RawMaterialDTO;
import br.com.company.inventorymanagement.business.mapper.RawMaterialMapper;
import br.com.company.inventorymanagement.business.services.RawMaterialService;
import br.com.company.inventorymanagement.infrastructure.model.RawMaterial;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/materials")
public class RawMaterialController {

    private final RawMaterialService rawMaterialService;
    private final RawMaterialMapper mapper;

    @PostMapping
    public ResponseEntity<RawMaterialDTO> createRawMaterial(@RequestBody RawMaterialCreateDTO dto) {
        RawMaterial rawMaterial = mapper.toEntity(dto);
        RawMaterial saved = rawMaterialService.saveRawMaterial(rawMaterial);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<RawMaterialDTO>> findAll() {
        List<RawMaterialDTO> list = rawMaterialService.findAllRawMaterials()
                .stream()
                .map(mapper::toDTO)
                .toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RawMaterialDTO> findById(@PathVariable Long id) {
        RawMaterial rawMaterial = rawMaterialService.findByIdRawMaterial(id);
        return ResponseEntity.ok(mapper.toDTO(rawMaterial));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteRawMaterial(@PathVariable Long id) {
        rawMaterialService.deleteByIdRawMaterial(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RawMaterialDTO> updateProduct(@PathVariable Long id,
                                                        @RequestBody RawMaterialCreateDTO dto) {
        RawMaterial updatedRawMaterial = rawMaterialService.updateRawMaterial(id, dto);
        return ResponseEntity.ok(mapper.toDTO(updatedRawMaterial));
    }
}
