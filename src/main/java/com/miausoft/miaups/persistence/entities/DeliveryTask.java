package com.miausoft.miaups.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.miausoft.miaups.converter.AddressConverter;
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
public class DeliveryTask implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "order_in_plan")
    private Integer order;

    @ManyToOne
    @JoinColumn(name = "parcel_id")
    private Parcel parcel;

    @Column
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Column
    @Convert(converter = AddressConverter.class)
    private Address startAddress;

    @Column
    @Convert(converter = AddressConverter.class)
    private Address destinationAddress;

    @JsonIgnoreProperties({"storedParcel"})
    @OneToOne
    @JoinColumn(name = "start_parcel_machine_locker_id")
    private ParcelMachineLocker startParcelMachineLocker;

    @JsonIgnoreProperties({"storedParcel"})
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

    @JsonIgnoreProperties({"delivery","parcel"})
    @OneToMany(mappedBy = "delivery", orphanRemoval = true)
    private Set<DeliveryTaskRecord> deliveryTaskRecords;

    public DeliveryTask(Integer order, Parcel parcel,
                        Address startAddress, Address destinationAddress,
                        ParcelMachineLocker startParcelMachineLocker, ParcelMachineLocker destinationParcelMachineLocker,
                        Warehouse startWarehouse, Warehouse destinationWarehouse) {
        this.status = DeliveryStatus.AWAITING;
        this.order = order;
        this.parcel = parcel;
        this.startAddress = startAddress;
        this.destinationAddress = destinationAddress;
        this.startParcelMachineLocker = startParcelMachineLocker;
        this.destinationParcelMachineLocker = destinationParcelMachineLocker;
        this.startWarehouse = startWarehouse;
        this.destinationWarehouse = destinationWarehouse;
    }

    
}
