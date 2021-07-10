package vn.luvina.startup.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.luvina.startup.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {
  Optional<Rating> findByScores(Integer score);

  Page<Rating> findAllByProductIdAndIsActiveOrderByEntryDateDesc(UUID productId, boolean isActive, Pageable pageable);
}
