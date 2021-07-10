package vn.luvina.startup.controller.v1;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.products.ProductRequestDto;
import vn.luvina.startup.dto.products.ProductSearchRequestDto;
import vn.luvina.startup.dto.products.ProductsSearchAndFilterResponseDto;
import vn.luvina.startup.model.Product;
import vn.luvina.startup.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ProductsSearchAndFilterResponseDto> getAllProduct(
            @RequestParam(name = "q", required = false) String searchValue,
            @RequestParam(name = "p", required = false) Integer pageNumber,
            @RequestParam(name = "category", required = false) UUID category,
            @RequestParam(name = "priceMin", required = false) Integer priceMin,
            @RequestParam(name = "priceMax", required = false) Integer priceMax,
            @RequestParam(name = "stocks", required = false) Integer stocks,
            @RequestParam(name = "sortField", required = false) String sortField,
            @RequestParam(name = "sortStatus", required = false) String sortStatus) {       
        ProductSearchRequestDto searchDto = new ProductSearchRequestDto(searchValue, pageNumber, category, priceMin, priceMax, stocks, sortField, sortStatus);
        return new ResponseEntity<>(productService.getAllProduct(searchDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") UUID id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<List<Product>> getProductBySlug(@PathVariable(value = "slug") String slug,
                                                         @RequestParam(name = "p", required = false) Integer pageNumber) {
        return new ResponseEntity<>(productService.getProductBySlug(slug, pageNumber), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> insertProduct(@RequestBody(required = false) ProductRequestDto product) {
        return new ResponseEntity<>(productService.insertProduct(product), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") UUID id,
            @RequestBody(required = false) ProductRequestDto product) {
        return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable(value = "id") UUID id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }

}
