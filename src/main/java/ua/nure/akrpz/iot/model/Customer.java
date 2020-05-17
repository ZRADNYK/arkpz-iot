package ua.nure.akrpz.iot.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Customer {
    private Double temperature;
    private Boolean isSicking;
    private Long buildingId;
}
