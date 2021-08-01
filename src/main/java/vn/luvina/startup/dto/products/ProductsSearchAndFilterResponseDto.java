package vn.luvina.startup.dto.products;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.luvina.startup.dto.base.MetaResponse;
import vn.luvina.startup.model.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsSearchAndFilterResponseDto {

    private List<Product> data;

    private MetaResponse meta;

}
