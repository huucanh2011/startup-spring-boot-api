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
@Table(name = "ratings")
public class Rating {
  @Id
  @GeneratedValue
  @Column(name = "id", unique = true)
  private UUID id;
  
  @Column(name = "scores")
  private Integer scores;

  @Column(name = "content", length = 255)
  private String content;

  @Column(name = "is_active")
  private boolean isActive;

  @Column(name = "product_id")
  private UUID productId;

  @Column(name = "user_id")
  private UUID userId;

  @Column(name = "entry_date", updatable = false, nullable = false)
  @CreationTimestamp
  private LocalDateTime entryDate;

  @Column(name = "update_date", nullable = false)
  @UpdateTimestamp
  private LocalDateTime updateDate;

}
