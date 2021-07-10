package vn.luvina.startup.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "m_brands")
public class MBrand {
  @Id
  @GeneratedValue
  @Column(name = "id", unique = true)
  private UUID id;

  @Column(name = "name", length = 40)
  private String name;

  @Column(name = "slug", length = 40)
  private String slug;
}
