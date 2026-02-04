package br.com.company.inventorymanagement.repository;

import br.com.company.inventorymanagement.model.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
}
