package org.groceries.entities;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
@Builder
public class ProductRequestDTO {
    private int productId;
    private String productName;
    private String category;
    private int price;
    private int stock;
    private BigDecimal cost;
}
