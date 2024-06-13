package org.groceries.entities;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
@Builder
public class OrderProduct {
    private int customerId;
    private String orderDate;
    private String status;
    private List<Products> listProduct;
    private int quantity;
}
