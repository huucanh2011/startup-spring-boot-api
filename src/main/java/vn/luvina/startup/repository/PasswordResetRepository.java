package vn.luvina.startup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.luvina.startup.model.PasswordReset;

public interface PasswordResetRepository extends JpaRepository<PasswordReset, String> {

  List<PasswordReset> findAllByEmailAndToken(String email, String token);

}
