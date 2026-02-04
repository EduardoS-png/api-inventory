package br.com.company.inventorymanagement.mapper;

import br.com.company.inventorymanagement.dto.product.ProductCreateDTO;
import br.com.company.inventorymanagement.dto.product.ProductDTO;
import br.com.company.inventorymanagement.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductCreateDTO dto);

    ProductDTO toDTO(Product product);

    List<ProductDTO> toDTOList(List<Product> products);
}
