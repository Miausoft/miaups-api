package com.miausoft.miaups.persistence.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ParcelMachineLocker implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    public long id;

    @Column(nullable = false)
    private Integer lockerId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ParcelMachine parcelMachine;

    @OneToOne
    @JoinColumn(unique = true)
    private Parcel storedParcel;

}

