package org.groceries.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
@Builder
public class Invoice {
    private int invoiceID;
    private int orderID;
    private java.sql.Date invoiceDate;
    private double totalAmount;
}
