package com.miausoft.miaups.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Column
    private Address startAddress;

    @Convert(converter = AddressConverter.class)
    @Column
    private Address destinationAddress;

    @JsonIgnoreProperties({"lockers"})
    @ManyToOne
    @JoinColumn
    private ParcelMachine startParcelMachine;

    @JsonIgnoreProperties({"lockers"})
    @ManyToOne
    @JoinColumn
    private ParcelMachine destinationParcelMachine;

    @JsonIgnoreProperties({"storedParcel","parcelMachine"})
    @OneToOne(mappedBy = "storedParcel")
    @JoinColumn
    private ParcelMachineLocker currentParcelMachineLocker;

    @JsonIgnoreProperties({"storedParcels"})
    @ManyToOne
    @JoinColumn
    private Warehouse currentWarehouse;

    @JsonIgnoreProperties({"parcel","id","paymentId"})
    @OneToOne(mappedBy = "parcel")
    private Payment payment;

    @JsonIgnoreProperties({"parcel"})
    @OneToMany(mappedBy = "parcel", orphanRemoval = true)
    private Set<DeliveryTask> deliveryPlan;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private ParcelDimensions dimensions;

    @JsonIgnore
    @OneToMany(mappedBy = "parcel")
    private Set<DeliveryTaskRecord> deliveryTaskRecords;
}
