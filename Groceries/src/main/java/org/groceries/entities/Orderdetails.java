package org.groceries.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
@Builder
public class Orderdetails {
    private int id;
    private int orderId;
    private int productId;
    private int quantity;
    private int price;

}
