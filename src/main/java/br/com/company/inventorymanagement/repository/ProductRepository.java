package br.com.company.inventorymanagement.repository;

import br.com.company.inventorymanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
