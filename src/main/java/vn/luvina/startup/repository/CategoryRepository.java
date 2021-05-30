package vn.luvina.startup.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.luvina.startup.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

  Optional<Category> findByName(String name);

  Page<Category> findAllByNameIgnoreCaseContainingOrSlugIgnoreCaseContaining(String name, String email,
      Pageable pageable);
}
