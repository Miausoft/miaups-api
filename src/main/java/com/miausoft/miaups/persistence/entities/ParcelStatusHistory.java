package com.miausoft.miaups.persistence.entities;

import com.miausoft.miaups.converter.AddressConverter;
import com.miausoft.miaups.enums.DeliveryStatus;
import com.miausoft.miaups.enums.DeliveryStatusGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ParcelStatusHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatusGroup deliveryStatusGroup;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus deliveryStatus;

    @Convert(converter = AddressConverter.class)
    @Column(nullable = false)
    private Address address;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Parcel parcel;
}
