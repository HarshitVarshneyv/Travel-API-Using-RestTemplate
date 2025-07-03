package com.myBizTravel.API.service;

import org.springframework.http.ResponseEntity;

import com.myBizTravel.API.dto.ApprovalRequestPayload;
import com.myBizTravel.API.dto.BookingPushPayload;
import com.myBizTravel.API.dto.ItineraryPushPayload;
import com.myBizTravel.API.dto.MyBizResponsePayload;
import com.myBizTravel.API.dto.TravelRequestPayload;

public interface TravelService {

	MyBizResponsePayload sendCreateRequestToMyBiz(TravelRequestPayload travelRequestPayload);

	void processreceiveItinerary(ItineraryPushPayload itineraryPushPayload);

	ResponseEntity<String> sendApprovalToMyBiz(ApprovalRequestPayload approvalRequestPayload);

	void receiveBooking(BookingPushPayload bookingPushPayload);
	
}

























/* 

We are using .block() in your code, which turns the reactive flow into a synchronous call and returns a String. 
That’s why your method returns ResponseEntity<String> instead of Mono<ResponseEntity<String>>.

🔁 Why Others Use Mono Directly?
In fully reactive applications (Spring WebFlux end-to-end):

You avoid blocking completely.

You return a Mono<ResponseEntity<String>> to let the WebFlux framework handle it reactively.

✅ Your Current Approach:

public ResponseEntity<String> sendCreateRequestToMyBiz(...) {
    String response = webClient.post()...block();
    return ResponseEntity.ok(response);
}
This is suitable for Spring MVC or if you don’t need reactive behavior at the controller level.

💡 Reactive Alternative:
If you want to return Mono<ResponseEntity<String>>, change it like this:

public Mono<ResponseEntity<String>> sendCreateRequestToMyBiz(...) {
    return webClient.post()
        .uri("/...")
        .bodyValue(request)
        .retrieve()
        .bodyToMono(String.class)
        .map(ResponseEntity::ok);
}
And in controller:

@PostMapping("/create-itinerary")
public Mono<ResponseEntity<String>> createItinerary(...) { ... }

🔍 What is the significance of non-blocking in WebClient?
🧠 1. Definition:
Non-blocking means that the thread is not held waiting for the HTTP response. Instead, it continues execution and gets notified when the response arrives.

With WebClient:

java
Copy
Edit
// Non-blocking
Mono<String> response = webClient.get()
    .uri("/something")
    .retrieve()
    .bodyToMono(String.class);
With blocking:

java
Copy
Edit
// Blocking (your current code)
String response = webClient.get()
    .uri("/something")
    .retrieve()
    .bodyToMono(String.class)
    .block();  // This blocks the thread!
⚡ Benefits of Non-Blocking:
Feature	Non-blocking (Mono)	Blocking (.block())
Thread Usage	Efficient (frees thread)	One thread per request
Concurrency	Scales well with many requests	Threads get saturated easily
Performance	High throughput	Lower throughput
Best Use Case	High-load systems, microservices	Simple, low-traffic systems
Return Type	Mono<T>, Flux<T> (reactive types)	Plain T (e.g., String)

🔁 Example in Your Context
Your service:

public ResponseEntity<String> sendCreateRequestToMyBiz(...) {
    return ResponseEntity.ok(webClient.post()...block());
}
Non-blocking version:

public Mono<ResponseEntity<String>> sendCreateRequestToMyBiz(...) {
    return webClient.post()
        .uri("/...")
        .bodyValue(request)
        .retrieve()
        .bodyToMono(String.class)
        .map(ResponseEntity::ok);
}
And update your controller to:

@PostMapping("/create-itinerary")
public Mono<ResponseEntity<String>> createItinerary(...) { ... }
🧩 When to Use Non-Blocking
✅ Use non-blocking (Mono, Flux) when:

You're building a fully reactive application using Spring WebFlux

You want high performance and scalability

You deal with many concurrent HTTP calls (APIs, microservices)

❌ Avoid it if:

You're using Spring MVC (traditional blocking model)

Simplicity and readability are more important than scalability
*/