package com.miausoft.miaups.models;

import com.sun.istack.NotNull;
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
@Table(name = "Parcel")
public class Parcel implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private UUID id;

    @Column(name = "Type")
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "DimensionsId")
    private ParcelDimensions dimensions;

    @OneToOne
    @JoinColumn(name = "PaymentId")
    private Payment payment;

    @Column(name = "CurrentAddress")
    private String currentAddress;

    @OneToOne
    @JoinColumn(name = "CurrentParcelMachineLockerId")
    private ParcelMachineLocker currentParcelMachineLocker;

    @ManyToOne
    @JoinColumn(name = "CurrentWarehouseId")
    private Warehouse currentWarehouse;

    @Column(name = "StartAddress")
    private String startAddress;

    @Column(name = "DestinationAddress")
    private String destinationAddress;

    @ManyToOne
    @JoinColumn(name = "StartParcelMachineId")
    private ParcelMachine startParcelMachine;

    @ManyToOne
    @JoinColumn(name = "DestinationParcelMachineId")
    private ParcelMachine destinationParcelMachine;

    @OneToMany(mappedBy = "parcel")
    private Set<DeliveryTask> tasks;

}
