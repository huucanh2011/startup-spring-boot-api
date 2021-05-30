package vn.luvina.startup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category extends BaseModel {

  @Column(name = "name", length = 40)
  private String name;

  @Column(name = "slug", length = 40)
  private String slug;

}
