package com.store.BillService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Table(name = "bill")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(
            //mappedBy = "bill",
            cascade=CascadeType.ALL
    )
    @JoinColumn(name = "bill_id")
    private Set<CartItem> cartItems;

    @Column(name = "totalSalesTax", nullable = false)
    private BigDecimal totalSalesTax;

    @Column(name = "totalCost", nullable = false)
    private BigDecimal totalCost;

}
