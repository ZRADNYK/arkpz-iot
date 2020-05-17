package ua.nure.akrpz.iot.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long buildingId;
    private String name;
    private Double area;
    private String address;
    private Short entersAmount;
    private Short workersAmount;
    private Short maxCustomersAllowed;

}
