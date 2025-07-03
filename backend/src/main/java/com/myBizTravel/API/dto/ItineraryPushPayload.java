package com.myBizTravel.API.dto;

import java.util.List;
import java.util.Map;

import com.myBizTravel.API.dto.TravelRequestPayload.DeviceDetails;
import com.myBizTravel.API.dto.TravelRequestPayload.RequestorInfo;
import com.myBizTravel.API.dto.TravelRequestPayload.TravellerDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryPushPayload {

	private DeviceDetails deviceDetails;
	private TravellerDetails travellerDetails;
	private RequestorInfo requestorInfo;
	private String myBizRequisitionId;
	private String tripId;
	private String customerId;
	private Map<String, List<ServiceDetails>> services;
    private String reasonForTravel;
    private String reasonComment;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
 class ServiceDetails {
    private boolean autobook;
    private boolean inPolicy;
    private double price;
    private String currency;
    private String serviceId;
    private String travelClass;
    private List<JourneyDetails> journeyDetails;
    private PaxCount paxDetails;
    private String tripType;
    private List<PolicyViolation> policyViolations;
}
 
@Data
@AllArgsConstructor
@NoArgsConstructor
 class JourneyDetails {
	    private AirportDetails from;
	    private AirportDetails to;
	    private long arrivalDate;
	    private long departureDate;
	    private int noOfStops;
	    private List<FlightInfo> flightInfoList;
	}

@Data
@AllArgsConstructor
@NoArgsConstructor
 class AirportDetails {
	    private String airportCode;
	    private String airportName;
	    private String cityName;
	}

@Data 
@AllArgsConstructor
@NoArgsConstructor
 class FlightInfo {
	    private String fltNo;
	    private String fltName;
	    private String fltCode;
	    private long arrivalDate;
	    private long departureDate;
	    private AirportDetails from;
	    private AirportDetails to;
	}

@Data
@AllArgsConstructor
@NoArgsConstructor
 class PaxCount {
	    private int adult;
	    private int child;
	    private int infant;
	}

@Data
@AllArgsConstructor
@NoArgsConstructor
 class PolicyViolation {
	    private String message;
	}