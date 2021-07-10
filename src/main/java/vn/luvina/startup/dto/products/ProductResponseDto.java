package vn.luvina.startup.dto.products;

import java.sql.Date;
import java.util.UUID;
import java.util.Locale.Category;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.luvina.startup.model.MBrand;
import vn.luvina.startup.model.MSize;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private MSize msizeProduct;

    private MBrand mBrand;
 
    private Category category;
    
    @ApiModelProperty(value = "id", example = "23a06b67-bdba-47ef-aa76-06d5ce63bbd1")
    private UUID id;

    @ApiModelProperty(value = "code", example = "AOSM0001")
    private String code;

    @ApiModelProperty(value = "name ", example = "Áo sơ mi đũi trơn")
    private String name;

    @ApiModelProperty(value = "slug", example = "ao-so-mi-dui-tron")
    private String slug;

    @ApiModelProperty(value = "gallery", example = "url 1, url 2")
    private String gallery;

    @ApiModelProperty(value = "image", example = "url image")
    private String image;

    @ApiModelProperty(value = "feature", example = "Áo chất liệu đũi")
    private String feature;

    @ApiModelProperty(value = "feature", example = "Áo chất liệu đũi")
    private String description;

    @ApiModelProperty(value = "price", example = "100000")
    private Integer price;

    @ApiModelProperty(value = "stocks", example = "1")
    private Integer stocks;

    @ApiModelProperty(value = "isActive", example = "true")
    private Boolean isActive;

    @ApiModelProperty(value = "brandId", example = "228b0f2e-70c8-461f-9f86-ff102009234f")
    private UUID brandId;

    @ApiModelProperty(value = "sizeId", example = "5233bfbe-10e0-4df5-bb56-9231b8806840")
    private UUID sizeId;

    @ApiModelProperty(value = "categoryId", example = "ea1abfd4-21c7-4a43-ab32-80d30ec4525a")
    private UUID categoryId;

    @ApiModelProperty(value = "entryDate", example = "2021-06-13 15:13:27.075046+07")
    private Date entryDate;

    @ApiModelProperty(value = "updateDate", example = "2021-06-13 15:13:27.075046+07")
    private Date updateDate;

}
