# MyBiz Travel Request API Integration (Spring Boot)
[Note : This is the project in which the main focus is calling the external api through RestTemplate of Make my trip i.e, request goes to one server to another server ].

This project integrates with MakeMyTrip's **myBiz Travel Request API**, enabling seamless travel requisition, approval, and booking synchronization between a third-party platform and myBiz.
 
---

## 🚀 Features

* Create travel requests and redirect users to myBiz
* Receive itineraries pushed by myBiz
* Send approval/rejection of services
* Receive booking details & vouchers after successful booking

---

## 📁 Project Structure

```
com.example.travelintegration
└── config
    └── RestTemplateConfig.java
└── controller
    └── TravelController.java
└── service
    └── TravelService.java
     └── TravelServiceImpl.java
└── dto
    ├── TravelRequest.java
    ├── ApprovalRequest.java
    ├── BookingPushPayload.java
    └── ItineraryPushPayload.java
```

---

## ⚙️ Configuration

Update your `application.properties`:

```properties
mybiz.api.key=your-partner-api-key
mybiz.client.code=your-client-code
```

---

## 🔗 API Endpoints

### 1. Create Travel Request

```
POST /api/travel/create-itinerary
```

* Sends trip data to myBiz
* Receives requisitionId and redirect URL

### 2. Receive Itinerary Push (from myBiz)

```
POST /api/travel/receive-itinerary
```

* myBiz calls this with full itinerary JSON after request creation

### 3. Send Approval

```
PUT /api/travel/send-approval
```

* Sends manager-level approvals or rejections

### 4. Receive Booking Details

```
POST /api/travel/receive-booking
```

* Receives booking info & vouchers after myBiz confirms booking

---

## 🧰 Technologies Used

* Java 17+
* Spring Boot 3+
* Spring Web (RestTemplate)
* Lombok

---

## 📦 Sample Payloads

Sample DTOs for each API interaction are defined in the `dto` package. Refer to:

* `TravelRequest` for outbound travel initiation
* `ItineraryPushPayload` for receiving itinerary
* `ApprovalRequest` to send approvals
* `BookingPushPayload` for booking data

---

## ✅ Running the App

1. Clone the repo
2. Add your credentials to `application.properties`
3. Run the Spring Boot application
4. Use Postman or your frontend to test the APIs
---
## 🙋 Support

Raise an issue or reach out if you need help customizing the integration.

Contact => Email : harshitvarshneyv2@gmail.com
