package vn.luvina.startup.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import vn.luvina.startup.dto.products.ProductRequestDto;
import vn.luvina.startup.dto.products.ProductSearchRequestDto;
import vn.luvina.startup.dto.products.ProductsSearchAndFilterResponseDto;
import vn.luvina.startup.exception.ServiceRuntimeException;
import vn.luvina.startup.mapper.MetaMapper;
import vn.luvina.startup.model.Product;
import vn.luvina.startup.repository.ProductRepository;
import vn.luvina.startup.service.ProductService;
import vn.luvina.startup.util.Constants;
import vn.luvina.startup.util.StartupMessages;
import vn.luvina.startup.util.StringUtils;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    private final MetaMapper metaMapper;

    @Override
    public ProductsSearchAndFilterResponseDto getAllProduct(ProductSearchRequestDto searchDto) {
        String searchValue = searchDto.getSearchValue();
        Integer pageNumber = searchDto.getPageNumber();
        UUID category = searchDto.getCategory();
        Integer stocks = searchDto.getStocks();
        Integer priceMin = searchDto.getPriceMin();
        Integer priceMax = searchDto.getPriceMax();
        String sortField = searchDto.getSortField();
        String sortStatus = searchDto.getSortStatus();
        Sort sort = Sort.by(Sort.Direction.ASC, "name");

        int limit = Constants.LIMIT_DEFAULT;
        Page<Product> searchResult;
        ProductsSearchAndFilterResponseDto responseResult = new ProductsSearchAndFilterResponseDto();

        // Xử lý sort
        if (sortField != null) {
            if ("ASC".equals(sortStatus)) {
                sort = Sort.by(Sort.Direction.ASC, sortField);
            } else {
                sort = Sort.by(Sort.Direction.DESC, sortField);
            }
        }

        // Gán các giá trị mặc định và giá trị cho phân trang.
        if (pageNumber == null) {
            pageNumber = Constants.PAGE_DEFAULT;
        }
        Pageable pageable = PageRequest.of(pageNumber - 1, limit, sort);
        // Kiểm tra trường hợp search và lọc
        if (searchValue == null && category == null && stocks == null && priceMin == null) {
            searchResult = productRepository.findAll(pageable);
        } else {
            if (searchValue != null) {
                searchValue = searchValue.toUpperCase();
            }
            searchResult = productRepository.searchAndFilter(searchValue, searchValue, searchValue, priceMin, priceMax,
                    category, stocks, pageable);
        }
        responseResult.setData(searchResult.getContent());
        responseResult.setMeta(metaMapper.convertToMetaResponse(pageNumber, limit, searchResult));
        return responseResult;
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new ServiceRuntimeException(HttpStatus.NOT_FOUND, StartupMessages.ERR_CATEGORY_002);
    }

    @Override
    public List<Product> getProductBySlug(String slug, Integer pageNumber) {
        int limit = Constants.LIMIT_DEFAULT;
        Page<Product> searchResult;
        // Gán các giá trị mặc định và giá trị cho phân trang.
        if (pageNumber == null) {
            pageNumber = Constants.PAGE_DEFAULT;
        }
        Pageable pageable = PageRequest.of(pageNumber - 1, limit);
        searchResult = productRepository.findBySlugIgnoreCaseContaining(slug, pageable);
        return searchResult.getContent();
    }

    @Override
    @Transactional
    public Product insertProduct(ProductRequestDto productRequestDto) {
        Product product = modelMapper.map(productRequestDto, Product.class);
        product.setSlug(StringUtils.toSlug(product.getName()));
        return productRepository.saveAndFlush(product);
    }

    @Override
    @Transactional
    public Product updateProduct(UUID id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id).get();
        product.setCode(productRequestDto.getCode());
        product.setName(productRequestDto.getName());
        product.setSlug(productRequestDto.getSlug());
        product.setGallery(productRequestDto.getGallery());
        product.setImage(productRequestDto.getImage());
        product.setFeature(productRequestDto.getFeature());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setStocks(productRequestDto.getStocks());
        product.setIsActive(productRequestDto.getIsActive());
        product.setBrandId(productRequestDto.getBrandId());
        product.setSizeId(productRequestDto.getSizeId());
        product.setCategoryId(productRequestDto.getCategoryId());
        return productRepository.saveAndFlush(product);
    }

    @Override
    @Transactional
    public Boolean deleteProduct(UUID id) {
        if (id != null) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
