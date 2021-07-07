package vn.luvina.startup.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.luvina.startup.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  Optional<User> findByEmail(String email);

  Boolean existsByEmail(String email);

  Page<User> findAllByNameIgnoreCaseContainingOrEmailIgnoreCaseContaining(String name, String email, Pageable pageable);

}
