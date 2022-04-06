package com.miausoft.miaups.models;

import com.sun.istack.NotNull;
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
@Table(name="ParcelMachineLocker")
public class ParcelMachineLocker implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Integer id;

    @NotNull
    @Column(name = "PlaceNr")
    private Integer placeNr;

    @NotNull
    @Column(name="Empty")
    private boolean empty;

    @ManyToOne
    @JoinColumn(name = "ParcelMachineId")
    private ParcelMachine parcelMachine;

    @OneToOne(mappedBy = "currentParcelMachineLocker")
    private Parcel storedParcel;
}

