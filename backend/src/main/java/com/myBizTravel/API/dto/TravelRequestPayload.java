package com.myBizTravel.API.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelRequestPayload {

    private DeviceDetails deviceDetails;
    private TravellerDetails travellerDetails;
    private RequestorInfo requestorInfo;

    @Builder.Default
    private String tripId = UUID.randomUUID().toString(); // Auto-generated tripId

    // Nested Static Classes (so they can be used without requiring outer instance)

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeviceDetails {
        private String osType;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TravellerDetails {
        private List<PaxDetail> paxDetails;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaxDetail {
        private String name;
        private String email;
        
        @JsonProperty("isPrimaryPax")
        private boolean isPrimaryPax;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestorInfo {
        private String email;
    }
}
