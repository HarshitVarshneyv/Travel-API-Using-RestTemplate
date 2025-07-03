package com.myBizTravel.API.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myBizTravel.API.dto.ApprovalRequestPayload;
import com.myBizTravel.API.dto.BookingPushPayload;
import com.myBizTravel.API.dto.ItineraryPushPayload;
import com.myBizTravel.API.dto.MyBizResponsePayload;
import com.myBizTravel.API.dto.TravelRequestPayload;
import com.myBizTravel.API.service.TravelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/travel")
@CrossOrigin
@RequiredArgsConstructor
public class TravelController {

	private final TravelService travelService;
	
	@PostMapping("/create-itinerary")
	public ResponseEntity<MyBizResponsePayload> createItineary(@RequestBody TravelRequestPayload travelRequestPayload){
		// MyBizResponsePayload payload = travelService.sendCreateRequestToMyBiz(travelRequestPayload);
		System.out.println(travelRequestPayload);
		return ResponseEntity.ok(travelService.sendCreateRequestToMyBiz(travelRequestPayload));
	}
	
	@PostMapping("/approval-request") //or => receive-itinerary
	public ResponseEntity<String> receiveItinerary(@RequestBody ItineraryPushPayload itineraryPushPayload){
		travelService.processreceiveItinerary(itineraryPushPayload);
		return ResponseEntity.ok("Itinerary received");
	}
	
	@PutMapping("/send-approval")
	public ResponseEntity<String> sendApproal(@RequestBody ApprovalRequestPayload approvalRequestPayload){
		return travelService.sendApprovalToMyBiz(approvalRequestPayload);
	}
	
	@PostMapping("/booking-confirmation")
	public ResponseEntity<String> receiveBooking(@RequestBody BookingPushPayload bookingPushPayload){
		travelService.receiveBooking(bookingPushPayload);
		return ResponseEntity.ok("Booking Received");
	}
}





