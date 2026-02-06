package br.com.company.inventorymanagement.business.services;

import br.com.company.inventorymanagement.business.dto.rawmaterial.RawMaterialCreateDTO;
import br.com.company.inventorymanagement.exceptions.BusinessException;
import br.com.company.inventorymanagement.exceptions.NotFoundException;
import br.com.company.inventorymanagement.infrastructure.model.RawMaterial;
import br.com.company.inventorymanagement.infrastructure.repository.RawMaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RawMaterialService {

    private final RawMaterialRepository repository;

    @Transactional(readOnly = true)
    public List<RawMaterial> findAllRawMaterials() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public RawMaterial findByIdRawMaterial(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Raw material not found: " + id)
        );
    }

    @Transactional
    public RawMaterial saveRawMaterial(RawMaterial material) {
        validateMaterial(material);
        return repository.save(material);
    }

    @Transactional
    public RawMaterial updateRawMaterial(Long id, RawMaterialCreateDTO dto) {
        RawMaterial rawMaterialExisting = findByIdRawMaterial(id);

        rawMaterialExisting.setName(dto.name());
        rawMaterialExisting.setStockQuantity(dto.stockQuantity());

        validateMaterial(rawMaterialExisting);

        return repository.save(rawMaterialExisting);
    }

    @Transactional
    public void deleteByIdRawMaterial(Long id) {
        RawMaterial material = findByIdRawMaterial(id);
        repository.delete(material);
    }

    private void validateMaterial(RawMaterial material) {
        if (material.getStockQuantity().doubleValue() < 0) {
            throw new BusinessException("Stock quantity cannot be negative");
        }

        if (material.getName() == null || material.getName().isBlank()) {
            throw new BusinessException("Material name cannot be empty");
        }
    }
}
