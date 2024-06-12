package org.groceries.entities;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
@Builder
public class Order {
    private int id;
    private int customerId;
    private Date orderDate;
    private String status;
}
