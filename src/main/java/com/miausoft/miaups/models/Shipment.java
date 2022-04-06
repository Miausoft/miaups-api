package com.miausoft.miaups.models;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="Shipment")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @NotNull
    @Column(name="Status")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Column(name = "CourierId")
    private String courierId;

    @OneToMany(mappedBy = "shipment")
    private Set<DeliveryTask> tasks;
}
