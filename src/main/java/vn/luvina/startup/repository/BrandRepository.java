package vn.luvina.startup.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.luvina.startup.model.MBrand;

@Repository
public interface BrandRepository extends JpaRepository<MBrand, UUID> {
  MBrand getByName(String name);
}
