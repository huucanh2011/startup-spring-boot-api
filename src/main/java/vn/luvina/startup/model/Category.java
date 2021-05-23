package vn.luvina.startup.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "categories")
public class Category {

  @Id
  @GeneratedValue
  @Column(name = "id", unique = true)
  private UUID id;

  @Column(name = "name", length = 40)
  private String name;

  @Column(name = "slug", length = 40)
  private String slug;

  @Column(name = "entry_date", updatable = false, nullable = false)
  @CreationTimestamp
  private LocalDateTime entryDate;

  @Column(name = "update_date", nullable = false)
  @UpdateTimestamp
  private LocalDateTime updateDate;
  
}
