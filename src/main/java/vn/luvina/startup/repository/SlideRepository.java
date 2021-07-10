package vn.luvina.startup.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.luvina.startup.model.Slide;

@Repository
public interface SlideRepository extends JpaRepository<Slide, UUID>{
  List<Slide> findAllByIsActive(boolean isActive); 
}
