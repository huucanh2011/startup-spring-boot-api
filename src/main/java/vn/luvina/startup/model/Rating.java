package vn.luvina.startup.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.catalina.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "ratings")
public class Rating extends BaseModel {
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

  @ManyToOne
  @JoinColumn(name = "product_id", insertable = false, updatable = false)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Product product;

  @Column(name = "user_id")
  private UUID userId;

  @ManyToOne
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private User user;

}
