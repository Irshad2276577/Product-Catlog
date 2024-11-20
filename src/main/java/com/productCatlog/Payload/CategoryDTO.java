package com.productCatlog.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.dialect.function.PostgreSQLTruncRoundFunction;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private long categoryId;
    private String name;
    private List<ProductDTO> products;

}
