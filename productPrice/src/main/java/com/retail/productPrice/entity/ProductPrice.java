package com.retail.productPrice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Amit Raut on 3/30/2019.
 */

@Data
@Builder
@Entity
@Table(name = "productPrice")
@AllArgsConstructor
public class ProductPrice implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    ProductPrice(){}
}
