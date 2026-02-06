package br.com.company.inventorymanagement.infrastructure.repository;

import br.com.company.inventorymanagement.infrastructure.model.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
}
