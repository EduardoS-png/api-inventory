package br.com.company.inventorymanagement.services;

import br.com.company.inventorymanagement.dto.product.ProductCreateDTO;
import br.com.company.inventorymanagement.exceptions.BusinessException;
import br.com.company.inventorymanagement.exceptions.NotFoundException;
import br.com.company.inventorymanagement.model.Product;
import br.com.company.inventorymanagement.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findByIdProduct(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Product not found: " + id)
        );
    }

    @Transactional
    public Product saveProduct(Product product) {
        validateProduct(product);
        return repository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, ProductCreateDTO dto) {
        Product productExisting = findByIdProduct(id);

        productExisting.setName(dto.name());
        productExisting.setPrice(dto.price());

        validateProduct(productExisting);

        return repository.save(productExisting);
    }

    @Transactional
    public void deleteByIdProduct(Long id) {
        Product product = findByIdProduct(id);
        repository.delete(product);
    }

    private void validateProduct(Product product) {
        if (product.getPrice().doubleValue() <= 0) {
            throw new BusinessException("Price must be greater than zero");
        }

        if (product.getName() == null || product.getName().isBlank()) {
            throw new BusinessException("Product name cannot be empty");
        }
    }
}
