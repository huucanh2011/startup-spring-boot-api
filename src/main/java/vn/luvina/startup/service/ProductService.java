package vn.luvina.startup.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import vn.luvina.startup.dto.products.ProductRequestDto;
import vn.luvina.startup.dto.products.ProductSearchRequestDto;
import vn.luvina.startup.dto.products.ProductsSearchAndFilterResponseDto;
import vn.luvina.startup.model.Product;

public interface ProductService {

    @Transactional
    public ProductsSearchAndFilterResponseDto getAllProduct(ProductSearchRequestDto searchDto);

    @Transactional
    public Product getProductById(UUID id);

    @Transactional
    public List<Product> getProductBySlug(String slug, Integer pageNumber);

    @Transactional
    public Product insertProduct(ProductRequestDto productRequestDto);

    @Transactional
    public Product updateProduct(UUID id,ProductRequestDto productRequestDto);

    @Transactional
    public Boolean deleteProduct(UUID id);
    
}
