package ua.nure.akrpz.iot.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class Detector {
    private Long detectorId;
    private String model;
    private String detectorPosition;
    private String description;
    private String type;

    private Double temperature;

    private Long buildingId;
}


