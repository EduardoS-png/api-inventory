package br.com.company.inventorymanagement.business.services;

import br.com.company.inventorymanagement.business.dto.productrawmaterial.ProductRawMaterialCreateDTO;
import br.com.company.inventorymanagement.business.dto.productrawmaterial.ProductRawMaterialDTO;
import br.com.company.inventorymanagement.business.mapper.ProductRawMaterialMapper;
import br.com.company.inventorymanagement.exceptions.BusinessException;
import br.com.company.inventorymanagement.exceptions.NotFoundException;
import br.com.company.inventorymanagement.infrastructure.model.Product;
import br.com.company.inventorymanagement.infrastructure.model.ProductRawMaterial;
import br.com.company.inventorymanagement.infrastructure.model.RawMaterial;
import br.com.company.inventorymanagement.infrastructure.repository.ProductRawMaterialRepository;
import br.com.company.inventorymanagement.infrastructure.repository.ProductRepository;
import br.com.company.inventorymanagement.infrastructure.repository.RawMaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductRawMaterialService {

    private final ProductRawMaterialRepository repository;
    private final ProductRepository productRepository;
    private final RawMaterialRepository rawMaterialRepository;
    private final ProductRawMaterialMapper mapper;

    @Transactional(readOnly = true)
    public List<ProductRawMaterialDTO> findByProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException("Product not found with ID: " + productId)
        );

        return repository.findByProductId(productId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Transactional
    public ProductRawMaterialDTO createAssociation(Long productId, ProductRawMaterialCreateDTO dto) {

        if (dto.quantityRequired() == null || dto.quantityRequired() <= 0) {
            throw new BusinessException("Quantity required must be greater than 0");
        }

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException("Product not found: " + productId)
        );

        RawMaterial rawMaterial = rawMaterialRepository.findById(dto.rawMaterialId()).orElseThrow(
                () -> new NotFoundException("Raw Material not found: " + dto.rawMaterialId())
        );

        if (repository.existsByProductIdAndRawMaterialId(productId, dto.rawMaterialId())) {
            throw new BusinessException("This material is already linked to this product");
        }

        ProductRawMaterial productRawMaterial = mapper.toEntity(dto);
        productRawMaterial.setProduct(product);
        productRawMaterial.setRawMaterial(rawMaterial);

        return mapper.toDTO(repository.save(productRawMaterial));
    }

    @Transactional
    public void deleteProductRawMaterial(Long id) {
        ProductRawMaterial productRawMaterial = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Relation not found: " + id)
        );

        repository.delete(productRawMaterial);
    }

    @Transactional
    public ProductRawMaterialDTO updateAssociation(Long id, ProductRawMaterialCreateDTO dto) {

        if (dto.quantityRequired() == null || dto.quantityRequired() <= 0) {
            throw new BusinessException("Quantity required must be greater than 0");
        }

        ProductRawMaterial productRawMaterial = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Relation not found: " + id)
        );

        productRawMaterial.setQuantityRequired(dto.quantityRequired());

        return mapper.toDTO(repository.save(productRawMaterial));
    }
}
