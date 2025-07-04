package com.myBizTravel.API.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

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
	
	@Value("${mybiz.api.key}")
	private String apiKey;
	
	@Value("${mybiz.client.code}")
	private String clientCode;
	
	private final RestTemplate restTemplate;
	
	private static final String BASE_URL = "https://corpcb.makemytrip.com";

	
	@Override
	public MyBizResponsePayload sendCreateRequestToMyBiz(TravelRequestPayload travelRequestPayload) {
	    log.info("Sending request to MyBiz...");

	    String url = BASE_URL + "/corporate/v1/create/partner/travelCardDetails";
	    
	    HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("partner-apikey", apiKey);
        headers.set("client-code", clientCode);
	    
        HttpEntity<TravelRequestPayload> requestEntity = new HttpEntity<>(travelRequestPayload,headers);
        
        try {
        	ResponseEntity<MyBizResponsePayload> response = restTemplate.exchange(
        			url,
        			HttpMethod.POST,
        			requestEntity,
        			MyBizResponsePayload.class
        	);
        	return response.getBody();
        } catch(HttpStatusCodeException e) {
        	String responseBody = e.getResponseBodyAsString();
        	log.error("HTTP error while calling MyBiz API. Status:{} , Body:{}",e.getStatusCode(),responseBody);
        	throw new MyBizApiException(
        			String.format("MyBiz API error: Status=%s,body=%s",e.getStatusCode() , responseBody)
        	,e);
        }  catch (RestClientException e) {
            // Handles timeouts, connection failures
            log.error("Connection error while calling MyBiz API: {}", e.getMessage(), e);
            throw new MyBizApiException("Failed to call MyBiz API", e);
        }
	  
	}
	@Override
	public void processreceiveItinerary(ItineraryPushPayload itineraryPushPayload) {
		// TODO Auto-generated method stub
//		System.out.println("Itinerary pushed: " + itineraryPushPayload);
		 log.info("Itinerary pushed: {}", itineraryPushPayload);
	}
	@Override
	public ResponseEntity<String> sendApprovalToMyBiz(ApprovalRequestPayload approvalRequestPayload) {
		// TODO Auto-generated method stub
//		String response = restClientWithHeaders
//				.put()
//				.uri("/corporate/v1/update/partner/travelCardDetails")
//				.body(approvalRequestPayload)
//				.retrieve()
//				.body(String.class);
//		return ResponseEntity.ok(response);
		
		String url = BASE_URL + "/corporate/v1/update/partner/travelCardDetails";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("partner-apikey", apiKey);
		headers.set("client-code", clientCode);
		
		HttpEntity<ApprovalRequestPayload> requestEntity = new HttpEntity<>(approvalRequestPayload,headers);
		
		try {
			ResponseEntity<String> response = restTemplate.exchange(
					url,
					HttpMethod.PUT,
					requestEntity,
					String.class
			);
			return response;
		} catch(HttpStatusCodeException e) {
			String responseBody = e.getResponseBodyAsString();
			log.error("HTTP error while sending approval to MyBiz.Status:{} , Body:{}" , e.getStatusCode(),responseBody);
			 throw new MyBizApiException(
	                    String.format("MyBiz API error: status=%s, body=%s", e.getStatusCode(), responseBody),
	                    e
	            );
		} catch (RestClientException e) {
            log.error("Connection error while sending approval to MyBiz: {}", e.getMessage(), e);
            throw new MyBizApiException("Failed to send approval to MyBiz", e);
        }
	}
	@Override
	public void receiveBooking(BookingPushPayload bookingPushPayload) {
		// TODO Auto-generated method stub
		 //System.out.println("Booking pushed: " + bookingPushPayload);
		 log.info("Booking pushed: {}", bookingPushPayload);
	}
}
