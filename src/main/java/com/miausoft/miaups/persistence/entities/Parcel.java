package com.miausoft.miaups.persistence.entities;

import com.miausoft.miaups.converter.AddressConverter;
import com.miausoft.miaups.enums.DeliveryMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Parcel implements Serializable {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryMethod deliveryMethod;

    @Convert(converter = AddressConverter.class)
    @Column(nullable = false)
    private Address startAddress;

    @Convert(converter = AddressConverter.class)
    @Column(nullable = false)
    private Address destinationAddress;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private ParcelDimensions dimensions;

    @OneToMany
    private Set<ParcelStatusHistory> statusHistory;

    @OneToOne
    @JoinColumn
    private ParcelMachineLocker currentParcelMachineLocker;

    @ManyToOne
    @JoinColumn
    private Warehouse currentWarehouse;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ParcelMachine startParcelMachine;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ParcelMachine destinationParcelMachine;
}
