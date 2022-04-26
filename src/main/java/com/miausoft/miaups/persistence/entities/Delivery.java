package com.miausoft.miaups.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.miausoft.miaups.converter.AddressConverter;
import com.miausoft.miaups.enums.DeliveryMethod;
import com.miausoft.miaups.enums.DeliveryStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Delivery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "parcel_id")
    private Parcel parcel;

    @Column
    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    @Column
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Column
    @Convert(converter = AddressConverter.class)
    private Address startAddress;

    @Column
    @Convert(converter = AddressConverter.class)
    private Address destinationAddress;

    @JsonIgnoreProperties({"storedParcel","parcelMachine"})
    @OneToOne
    @JoinColumn(name = "start_parcel_machine_locker_id")
    private ParcelMachineLocker startParcelMachineLocker;

    @JsonIgnoreProperties({"storedParcel","parcelMachine"})
    @OneToOne
    @JoinColumn(name = "destination_parcel_machine_locker_id")
    private ParcelMachineLocker destinationParcelMachineLocker;

    @JsonIgnoreProperties({"storedParcels"})
    @OneToOne
    @JoinColumn(name = "start_warehouse_id")
    private Warehouse startWarehouse;

    @JsonIgnoreProperties({"storedParcels"})
    @OneToOne
    @JoinColumn(name = "destination_warehouse_id")
    private Warehouse destinationWarehouse;

    @JsonIgnoreProperties({"delivery"})
    @OneToMany(mappedBy = "delivery", orphanRemoval = true)
    private Set<DeliveryRecord> deliveryRecords;
}
