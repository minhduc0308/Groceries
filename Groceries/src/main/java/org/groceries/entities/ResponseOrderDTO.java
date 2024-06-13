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
public class ResponseOrderDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Date orderDate;
    private String status;
}
