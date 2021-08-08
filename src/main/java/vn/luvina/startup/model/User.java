package vn.luvina.startup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseModel {

  @Column(name = "name", length = 40)
  private String name;

  @Column(name = "email", length = 60)
  private String email;

  @Column(name = "password", length = 60)
  @JsonIgnore
  private String password;

  @Column(name = "avatar_path", length = 255)
  private String avatarPath;

  @Column(name = "address", length = 100)
  private String address;

  @Column(name = "phone_number", length = 11)
  private String phoneNumber;

  @Column(name = "delivery_address", length = 100)
  private String deliveryAddress;

  @Column(name = "role", length = 5)
  private String role;

  @Column(name = "status", length = 10)
  private String status;

}
