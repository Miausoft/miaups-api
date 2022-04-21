package com.miausoft.miaups.persistence.entities;

import com.miausoft.miaups.converter.AddressConverter;
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
public class ParcelMachine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    public long id;

    @Convert(converter = AddressConverter.class)
    @Column(nullable = false)
    private Address address;

    @OneToMany(mappedBy = "parcelMachine")
    private Set<ParcelMachineLocker> lockers;
}
