package vn.luvina.startup.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.luvina.startup.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    
    Optional<Product> findById(UUID id);

    Page<Product> findBySlugIgnoreCaseContaining(String slug, Pageable pageable);

    Page<Product> findByCodeIgnoreCaseContainingOrNameIgnoreCaseContainingOrSlugIgnoreCaseContaining(String code, String name,String slug, Pageable pageable);
    
    Page<Product> findByNameContaining(String name, Pageable pageable);

    @Query("SELECT p "  +
    "FROM Product AS p "+
    "WHERE ((:code is null or upper(p.code) LIKE %:code%) OR (:name is null or upper(p.name) LIKE %:name%) OR (:slug is null or upper(p.slug) LIKE %:slug%)) " +
    "AND ((:priceMin is null or (p.price >= :priceMin AND p.price <= :priceMax)) AND (COALESCE(:categoryId) is null or p.categoryId = :categoryId) AND (COALESCE(:stocks) is null or p.stocks = :stocks))")
    Page<Product> searchAndFilter(@Param("code") String code, @Param("name")String name,@Param("slug") String slug, 
                                    @Param("priceMin") Integer priceMin,@Param("priceMax") Integer priceMax, @Param("categoryId") UUID categoryId,
                                    @Param("stocks") Integer stocks, Pageable pageable);
}