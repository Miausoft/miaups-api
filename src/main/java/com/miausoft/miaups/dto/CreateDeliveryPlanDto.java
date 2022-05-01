package com.miausoft.miaups.dto;

import java.util.List;
import java.util.UUID;

public class CreateDeliveryPlanDto {
    public UUID parcelId;
    public List<Stop> stops;

    public static class Stop{
        public Long parcelMachineId;
        public Long warehouseId;
    }
}
