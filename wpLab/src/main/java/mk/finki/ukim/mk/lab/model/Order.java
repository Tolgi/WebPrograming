package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Orders")
public class Order {

    private String pizzaType;
    private String pizzaSize;
    private String clientName;
    private String clientAddress;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

}
