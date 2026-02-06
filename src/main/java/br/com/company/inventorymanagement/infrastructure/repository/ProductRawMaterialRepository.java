package br.com.company.inventorymanagement.infrastructure.repository;

import br.com.company.inventorymanagement.infrastructure.model.ProductRawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRawMaterialRepository extends JpaRepository<ProductRawMaterial, Long> {

    List<ProductRawMaterial> findByProductId(Long productId);

    boolean existsByProductIdAndRawMaterialId(Long productId, Long rawMaterialId);
}
