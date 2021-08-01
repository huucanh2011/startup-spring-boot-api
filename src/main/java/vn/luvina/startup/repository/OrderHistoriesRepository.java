package vn.luvina.startup.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.luvina.startup.model.OrderHistory;

@Repository
public interface OrderHistoriesRepository extends JpaRepository<OrderHistory, UUID> {
    Page<OrderHistory> findAllByUserId(UUID userId, Pageable pageable);
}
