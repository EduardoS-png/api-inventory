package br.com.company.inventorymanagement.infrastructure.repository;

import br.com.company.inventorymanagement.infrastructure.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
