package com.miausoft.miaups.persistence.entities;

import com.miausoft.miaups.converter.AddressConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Convert(converter = AddressConverter.class)
    @Column(nullable = false)
    private Address address;

    @OneToMany(mappedBy = "currentWarehouse")
    private Set<Parcel> storedParcels;
}
