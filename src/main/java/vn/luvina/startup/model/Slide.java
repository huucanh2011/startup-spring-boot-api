package vn.luvina.startup.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Table(name = "slides")
public class Slide {
  @Id
  @GeneratedValue
  @Column(name = "id", unique = true)
  private UUID id;

  @Column(name = "title", length = 40)
  private String title;

  @Column(name = "link", length = 255)
  private String link;

  @Column(name = "image", length = 255)
  private String image;

  @Column(name = "is_active")
  private boolean isActive;

  @Column(name = "entry_date", updatable = false, nullable = false)
  @CreationTimestamp
  private LocalDateTime entryDate;
  

  @Column(name = "update_date", nullable = false)
  @UpdateTimestamp
  private LocalDateTime updateDate;

}
