package group.intelliboys.cimsfrontend.models.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Credit {
    private int id;
    private int customerId;
    private String productId;
    private String productName;
    private float price;
    private int quantity;
    private String paid;
    private LocalDate dueDate;
    private LocalDate createdAt;
}
