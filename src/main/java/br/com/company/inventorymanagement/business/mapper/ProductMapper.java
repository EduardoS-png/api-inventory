package br.com.company.inventorymanagement.business.mapper;

import br.com.company.inventorymanagement.business.dto.product.ProductCreateDTO;
import br.com.company.inventorymanagement.business.dto.product.ProductDTO;
import br.com.company.inventorymanagement.infrastructure.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductCreateDTO dto);

    ProductDTO toDTO(Product product);

    List<ProductDTO> toDTOList(List<Product> products);
}
