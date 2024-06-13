package org.groceries.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
@Builder
public class ResponseOrderDetailsDTO {
    private int id;
    private int orderId;
    private String productName;
    private String category;
    private String price;
    private int quantity;
}
