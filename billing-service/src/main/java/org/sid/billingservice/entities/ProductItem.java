package org.sid.billingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.billingservice.models.Product;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class ProductItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double quantity;
    private double price;
    private long productID;
    @ManyToOne
    private Bill bill;
    @Transient
    private Product product;
}
