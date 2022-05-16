package com.miausoft.miaups.persistence.entities;

import com.miausoft.miaups.enums.DeliveryStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DeliveryTaskRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus deliveryStatus;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private DeliveryTask delivery;

    @ManyToOne
    @JoinColumn(name = "parcel_id")
    private Parcel parcel;

    @Column
    private LocalDateTime createdAt;

    public DeliveryTaskRecord(DeliveryStatus deliveryStatus, DeliveryTask delivery, Parcel parcel) {
        this.deliveryStatus = deliveryStatus;
        this.delivery = delivery;
        this.parcel = parcel;
        createdAt = LocalDateTime.now();
    }
}
