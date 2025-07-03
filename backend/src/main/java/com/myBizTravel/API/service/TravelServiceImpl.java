package com.myBizTravel.API.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

import com.myBizTravel.API.dto.ApprovalRequestPayload;
import com.myBizTravel.API.dto.BookingPushPayload;
import com.myBizTravel.API.dto.ItineraryPushPayload;
import com.myBizTravel.API.dto.MyBizResponsePayload;
import com.myBizTravel.API.dto.TravelRequestPayload;
import com.myBizTravel.API.exception.all.MyBizApiException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TravelServiceImpl implements TravelService{
	
	@Qualifier("restClientWithHeaders")
	private final RestClient restClientWithHeaders;
	
	@Override
	public MyBizResponsePayload sendCreateRequestToMyBiz(TravelRequestPayload travelRequestPayload) {
	    log.info("Sending request to MyBiz...");

	    try {
	        return restClientWithHeaders
	            .post()
	            .uri("/corporate/v1/create/partner/travelCardDetails")
	            .body(travelRequestPayload)
	            .retrieve()
	            .onStatus(
	                HttpStatusCode::isError,
	                (request, response) -> {
	                    String errorBody;
	                    try (InputStream errStream = response.getBody()) {
	                        errorBody = new String(errStream.readAllBytes(), StandardCharsets.UTF_8);
	                    } catch (IOException ioException) {
	                        errorBody = "Unable to read error body: " + ioException.getMessage();
	                    }
	                    HttpStatusCode status = response.getStatusCode();
	                    log.error("Error from MyBiz API. Status: {}, Body: {}", status, errorBody);
	                    throw new MyBizApiException(
	                        String.format("MyBiz API returned error: status=%s, body=%s", status, errorBody)
	                    );
	                }
	            )
	            .body(MyBizResponsePayload.class);

	    } catch (RestClientResponseException e) {
	        // Handles HTTP errors like 4xx/5xx not caught above
	        log.error("HTTP error while calling MyBiz API. Status: {}, Body: {}",
	        		
	                e.getResponseBodyAsString(),
	                e);
	        throw new MyBizApiException("HTTP error while calling MyBiz API", e);
	    } catch (RestClientException e) {
	        // Handles connection errors, timeouts, etc.
	        log.error("Connection error while calling MyBiz API: {}", e.getMessage(), e);
	        throw new MyBizApiException("Failed to call MyBiz API", e);
	    }
	}

	@Override
	public void processreceiveItinerary(ItineraryPushPayload itineraryPushPayload) {
		// TODO Auto-generated method stub
		System.out.println("Itinerary pushed: " + itineraryPushPayload);
	}
	@Override
	public ResponseEntity<String> sendApprovalToMyBiz(ApprovalRequestPayload approvalRequestPayload) {
		// TODO Auto-generated method stub
		String response = restClientWithHeaders
				.put()
				.uri("/corporate/v1/update/partner/travelCardDetails")
				.body(approvalRequestPayload)
				.retrieve()
				.body(String.class);
		return ResponseEntity.ok(response);
	}
	@Override
	public void receiveBooking(BookingPushPayload bookingPushPayload) {
		// TODO Auto-generated method stub
		 System.out.println("Booking pushed: " + bookingPushPayload);
	}
}
