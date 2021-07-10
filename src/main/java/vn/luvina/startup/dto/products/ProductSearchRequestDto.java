package vn.luvina.startup.dto.products;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchRequestDto {
    private String searchValue;
    private Integer pageNumber;
    private UUID category;
    private Integer priceMin;
    private Integer priceMax;
    private Integer stocks;
    private String sortField;
    private String sortStatus;
}
