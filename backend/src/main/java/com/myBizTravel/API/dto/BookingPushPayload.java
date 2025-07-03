package com.myBizTravel.API.dto;

import java.util.List;

import lombok.Data;

@Data
public class BookingPushPayload {
    private String customerId;
    private String serviceId;
    private String requisitionId;
    private String bookingId;
    private String bookerName;
    private String bookerEmail;
    private String approvalStatus;
    private String tripType;
    private List<BookingVoucherDetails> bookingVoucherDetails;
    private FlightBookingDetails flightBookingDetails;
}

@Data
 class BookingVoucherDetails {
    private String path;
    private long createdAt;
}


@Data
 class FlightBookingDetails {
    private String journeyType;
    private String fromCity;
    private String toCity;
    private String fromAirportCode;
    private String toAirportCode;
    private boolean isCompleteCancellation;
}


