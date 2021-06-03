package vn.luvina.startup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.luvina.startup.model.PasswordReset;

public interface PasswordResetRepository extends JpaRepository<PasswordReset, String> {

}
