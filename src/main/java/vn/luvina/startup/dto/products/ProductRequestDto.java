package vn.luvina.startup.dto.products;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    private String code;

    private String name;

    private String slug;

    private String gallery;

    private String image;

    private String feature;

    private String description;

    private Integer price;

    private Integer stocks;

    private Boolean isActive;

    private UUID brandId;

    private UUID sizeId;

    private UUID categoryId;

    // private Date entryDate;

    // private Date updateDate;
    
}
