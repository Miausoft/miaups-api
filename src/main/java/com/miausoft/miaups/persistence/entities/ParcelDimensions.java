package com.miausoft.miaups.persistence.entities;

import com.miausoft.miaups.enums.ParcelSize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ParcelDimensions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    public long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParcelSize size;

    @Column(nullable = false)
    private double maxLength;

    @Column(nullable = false)
    private double maxWidth;

    @Column(nullable = false)
    private double maxHeight;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private double price;
}
