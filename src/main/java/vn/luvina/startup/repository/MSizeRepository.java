package vn.luvina.startup.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.luvina.startup.model.MSize;

@Repository
public interface MSizeRepository extends  JpaRepository<MSize, UUID> {
    
}
