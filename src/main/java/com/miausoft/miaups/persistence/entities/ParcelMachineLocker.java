package com.miausoft.miaups.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Column
    private boolean reserved;

    @JsonIgnoreProperties({"lockers"})
    @ManyToOne
    @JoinColumn(nullable = false)
    private ParcelMachine parcelMachine;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Parcel storedParcel;

    public void removeParcel() {
        storedParcel = null;
        reserved = false;
        parcelMachine.increaseAvailableLockersCount();
    }
    public void insertParcel(Parcel parcel){
        if(!reserved){
            throw new RuntimeException("In order to insert Parcel - Locker must be reserved");
        }
        storedParcel = parcel;
    }
}

