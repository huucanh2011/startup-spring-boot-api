package vn.luvina.startup.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseModel{

    @ManyToOne
    @JoinColumn(name = "size_id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private MSize msizeProduct;

    @ManyToOne
    @JoinColumn(name = "brand_id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private MBrand mBrand;
 
    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Category category;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "gallery")
    private String gallery;

    @Column(name = "image")
    private String image;

    @Column(name = "feature")
    private String feature;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "stocks")
    private Integer stocks;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "brand_id")
    private UUID brandId;

    @Column(name = "size_id")
    private UUID sizeId;

    @Column(name = "category_id")
    private UUID categoryId;

}
