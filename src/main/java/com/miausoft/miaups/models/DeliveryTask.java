package com.miausoft.miaups.models;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="DeliveryTask")
public class DeliveryTask implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Integer id;

    @NotNull
    @Column(name="Type")
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    @NotNull
    @Column(name="Status")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ParcelId")
    private Parcel parcel;

    @NotNull
    @Column(name = "CreatedBy")
    private String createdBy;

    @NotNull
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedAt")
    private Date createdAt;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CompletedAt")
    private Date completedAt;

    @Column(name="StartAddress")
    private String startAddress;

    @Column(name="DestinationAddress")
    private String destinationAddress;

    @ManyToOne
    @JoinColumn(name="StartParcelMachineId")
    private ParcelMachine startParcelMachine;

    @ManyToOne
    @JoinColumn(name="DestinationParcelMachineId")
    private ParcelMachine destinationParcelMachine;

    @ManyToOne
    @JoinColumn(name="StartWarehouseId")
    private Warehouse startWarehouse;

    @ManyToOne
    @JoinColumn(name="DestinationWarehouseId")
    private Warehouse destinationWarehouse;

    @ManyToOne
    @JoinColumn(name="shipmentId")
    private Shipment shipment;
}
