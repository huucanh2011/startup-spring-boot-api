package vn.luvina.startup.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "password_resets")
public class PasswordReset {

  @Id
  @Column(name = "email", length = 60)
  private String email;

  @Column(name = "token", length = 60)
  private String token;

  @Column(name = "entry_date", updatable = false, nullable = false)
  @CreationTimestamp
  private LocalDateTime entryDate;

}
