package com.examly.apigateway;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApigatewayApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    public String customerToken;
    private String financialConsultantToken;
    private String regionalManagerToken;
    private int customerUserId;

    private ObjectMapper objectMapper = new ObjectMapper(); 

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Test
    @Order(1)
    void backend_testRegisterCustomer() throws Exception {
        int userId = 1; // Example userId for test
        
        String requestBody = "{"
            + "\"userId\": " + userId + ","
            + "\"email\": \"customer@gmail.com\","
            + "\"password\": \"customer@1234\","
            + "\"username\": \"customer\","
            + "\"userRole\": \"Customer\","
            + "\"mobileNumber\": \"1234567890\""
            + "}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/register",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        // Assert status is Created
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Parse the response body
        JsonNode responseBody = objectMapper.readTree(response.getBody());
        int registeredUserId = responseBody.get("userId").asInt();
        Assertions.assertEquals(userId, registeredUserId, "UserId should match the provided one");

        // Store userId for further use if needed
        customerUserId = registeredUserId;
        System.out.println("Registered Customer UserId: " + customerUserId);
    }

    @Test
    @Order(2)
    void backend_testRegisterFinancialConsultant() throws Exception {
        int userId = 2; // Example userId for test
        
        String requestBody = "{"
            + "\"userId\": " + userId + ","
            + "\"email\": \"consultant@gmail.com\","
            + "\"password\": \"consultant@1234\","
            + "\"username\": \"consultant\","
            + "\"userRole\": \"FinancialConsultant\","
            + "\"mobileNumber\": \"1234567890\""
            + "}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/register",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        // Assert status is Created
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Parse the response body
        JsonNode responseBody = objectMapper.readTree(response.getBody());
        int registeredUserId = responseBody.get("userId").asInt();
        Assertions.assertEquals(userId, registeredUserId, "UserId should match the provided one");

        // Store token for further use if needed
        int financialConsultantUserId = registeredUserId;
        System.out.println("Registered Financial Consultant UserId: " + registeredUserId);
    }

    @Test
    @Order(3)
    void backend_testRegisterRegionalManager() throws Exception {
        int userId = 3; // Example userId for test
        
        String requestBody = "{"
            + "\"userId\": " + userId + ","
            + "\"email\": \"regionalmanager@gmail.com\","
            + "\"password\": \"regionalmanager@1234\","
            + "\"username\": \"regionalmanager\","
            + "\"userRole\": \"RegionalManager\","
            + "\"mobileNumber\": \"1234567890\""
            + "}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/register",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        // Assert status is Created
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Parse the response body
        JsonNode responseBody = objectMapper.readTree(response.getBody());
        int registeredUserId = responseBody.get("userId").asInt();
        Assertions.assertEquals(userId, registeredUserId, "UserId should match the provided one");

        // Store token for further use if needed
        int regionalManagerUserId = registeredUserId;
        System.out.println("Registered Regional Manager UserId: " + registeredUserId);
    }

    @Test
    @Order(4)
    void backend_testLoginCustomer() throws Exception, JsonProcessingException {
        String requestBody = "{\"email\": \"customer@gmail.com\", \"password\": \"customer@1234\"}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        // Parse the response body
        JsonNode responseBody = objectMapper.readTree(response.getBody());
        String token = responseBody.get("token").asText();
        customerToken = token;
        customerUserId = responseBody.get("userId").asInt();

        // Assert status is OK
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(token);
        Assertions.assertNotNull(customerUserId, "UserId should not be null");
    }

    @Test
    @Order(5)
    void backend_testLoginFinancialConsultant() throws Exception, JsonProcessingException {
        String requestBody = "{\"email\": \"consultant@gmail.com\", \"password\": \"consultant@1234\"}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        JsonNode responseBody = objectMapper.readTree(response.getBody());
        String token = responseBody.get("token").asText();
        financialConsultantToken = token;

        // Assert status is OK
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(token);
    }

    @Test
    @Order(6)
    void backend_testLoginRegionalManager() throws Exception, JsonProcessingException {
        String requestBody = "{\"email\": \"regionalmanager@gmail.com\", \"password\": \"regionalmanager@1234\"}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        JsonNode responseBody = objectMapper.readTree(response.getBody());
        String token = responseBody.get("token").asText();
        regionalManagerToken = token;

        // Assert status is OK
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(token);
    }




	@Test
	@Order(7)
	void backend_testAddSavingsPlanAsFinancialConsultant() throws Exception {
		Assertions.assertNotNull(financialConsultantToken, "financial token should not be null");
       
        int savingsPlanId = 1;
       
        String requestBody = "{"
        + "\"savingsPlanId\": " + savingsPlanId + ","  // Replace with actual savings plan ID if needed, or remove if auto-generated
        + "\"name\": \"Emergency Fund\","
        + "\"goalAmount\": 10000.0,"                   // Replace with actual goal amount
        + "\"timeFrame\": 2,"                          // Replace with actual time frame
        + "\"riskLevel\": \"High\","                   // Replace with actual risk level ("High", "Medium", "Low")
        + "\"description\": \"A fund for emergencies.\"," // Replace with actual description
        + "\"status\": \"Active\""                     // Replace with actual status ("Active", "Inactive", etc.)
        + "}";

	
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + financialConsultantToken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
	
		ResponseEntity<String> response = restTemplate.exchange("/api/savingsplans", HttpMethod.POST, requestEntity, String.class);
	
		// Assert status is CREATED (201)
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		// Optionally log or assert the response body if needed
		System.out.println("Response status: " + response.getStatusCode());
		System.out.println("Response body: " + response.getBody());
	}
	


    @Test
    @Order(8)
    void backend_testAddSavingsPlanAsCustomer() throws Exception {
        Assertions.assertNotNull(customerToken, "customerToken should not be null");

        String requestBody = "{"
                + "\"name\": \"Emergency Fund\","
                + "\"goalAmount\": 10000.0,"
                + "\"timeFrame\": 2,"
                + "\"riskLevel\": \"High\","
                + "\"description\": \"A fund for emergencies.\","
                + "\"status\": \"Active\""
                + "}";

        System.out.println("Request Body for Adding Savings Plan: " + requestBody);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer invalidtoken");
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/savingsplans", HttpMethod.POST, requestEntity,
                String.class);

        // Assert status is UNAUTHORIZED
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        // Optionally log or assert the response body if needed
        System.out.println("Response status: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody());
    }

    @Test
    @Order(9)
    void backend_testGetSavingsPlanByIdAsFinancialConsultant() throws Exception {
        // Arrange: Set up the token and the savings plan ID you want to retrieve
        Assertions.assertNotNull(financialConsultantToken, "FinancialConsultant token should not be null");
        long testSavingsPlanId = 1L; // Replace with an actual ID or dynamically retrieve it
    
        HttpHeaders headers = createHeaders();
        headers.set("Authorization", "Bearer " + financialConsultantToken);
    
        // Act: Perform the GET request to retrieve the savings plan by ID
        ResponseEntity<String> response = restTemplate.exchange(
                "/api/savingsplans/{id}", 
                HttpMethod.GET, 
                new HttpEntity<>(headers), 
                String.class, 
                testSavingsPlanId
        );
    
        // Assert: Check the status and content
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
        
        String responseBody = response.getBody();
        Assertions.assertNotNull(responseBody, "Response body should not be null");
      //  Assertions.assertTrue(responseBody.contains("\"id\":" + testSavingsPlanId), "Response should contain the savings plan ID");
        
        System.out.println("Response status: " + response.getStatusCode());
        System.out.println("Response body: " + responseBody);
    }



	@Test
    @Order(10)
    void backend_testAccessSavingsPlansAsFinancialConsultant() throws Exception {
        Assertions.assertNotNull(financialConsultantToken, "financial consultant token should not be null");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + financialConsultantToken);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/savingsplans", HttpMethod.GET, requestEntity, String.class);

        // Assert status is OK (200)
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        // Optionally log or assert the response body if needed
        System.out.println("Response status (Financial Consultant): " + response.getStatusCode());
        System.out.println("Response body (Financial Consultant): " + response.getBody());
    }

    @Test
    @Order(11)
    void backend_testAccessSavingsPlansAsRegionalManager() throws Exception {
        Assertions.assertNotNull(regionalManagerToken, "regional manager token should not be null");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + regionalManagerToken);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/savingsplans", HttpMethod.GET, requestEntity, String.class);

        // Assert status is OK (200)
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        // Optionally log or assert the response body if needed
        System.out.println("Response status (Regional Manager): " + response.getStatusCode());
        System.out.println("Response body (Regional Manager): " + response.getBody());
    }

    @Test
    @Order(12)
    void backend_testAccessSavingsPlansAsCustomer() throws Exception {
        Assertions.assertNotNull(customerToken, "customer token should not be null");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + customerToken);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/savingsplans", HttpMethod.GET, requestEntity, String.class);

        // Assert status is OK (200)
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        // Optionally log or assert the response body if needed
        System.out.println("Response status (Customer): " + response.getStatusCode());
        System.out.println("Response body (Customer): " + response.getBody());
    }

@Test
@Order(13)
void backend_testUpdateSavingsPlanAsRegionalManager() throws Exception {
    Assertions.assertNotNull(regionalManagerToken, "regional manager token should not be null");

    // Assuming you have a savings plan with ID 1 that you want to update
  
    Long savingsPlanId = 1L;  
        
    // Update request body
    String requestBody = "{"
            + "\"name\": \"Updated Emergency Fund\"," 
            + "\"goalAmount\": 15000.0," 
            + "\"timeFrame\": 3," 
            + "\"riskLevel\": \"Medium\"," 
            + "\"description\": \"Updated fund for emergencies.\"," 
            + "\"status\": \"Active\"" 
            + "}";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + regionalManagerToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/savingsplans/" + savingsPlanId, HttpMethod.PUT, requestEntity, String.class);

    // Assert status is OK (200) or No Content (204) based on your API implementation
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()); // or HttpStatus.NO_CONTENT

    // Optionally, log or assert the response body if needed
    System.out.println("Response status (Regional Manager Update): " + response.getStatusCode());
    System.out.println("Response body (Regional Manager Update): " + response.getBody());
}




@Test
@Order(14)
void backend_testPostFeedbackByCustomer() throws Exception {
    Assertions.assertNotNull(customerToken, "Customer token should not be null");

    // Feedback request body
    String requestBody = "{"
        + "\"feedbackId\": 1,"
        + "\"feedbackText\": \"Great application, really user-friendly!\","
        + "\"date\": \"2024-09-15\","
        + "\"user\": {"
        + "\"userId\": " + 1
        + "}"
        + "}";
 

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + customerToken);
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    // Execute POST request
    ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.POST, requestEntity, String.class);

    // Assert that the POST operation was successful (status is CREATED)
    Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    System.out.println("Feedback posted successfully by Customer.");
}

@Test
@Order(15)
void backend_testPostFeedbackAsFinancialConsultant() throws Exception {
    Assertions.assertNotNull(financialConsultantToken, "Financial Consultant token should not be null");

    // Feedback request body
    String requestBody = "{"
            + "\"feedbackId\": 1,"
            + "\"userId\": " + customerUserId + ","
            + "\"comments\": \"Great service!\","
            + "\"rating\": 5"
            + "}";

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + financialConsultantToken);
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    // Execute POST request
    ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.POST, requestEntity, String.class);

    // Assert that the POST operation is forbidden (status is FORBIDDEN)
    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    System.out.println("Unauthorized user (Financial Consultant) attempted to post feedback.");
}




 @Test
    @Order(16)
    void backend_testAddPlanApplicationAsCustomer() throws Exception {
        Assertions.assertNotNull(customerToken, "customerToken should not be null");

        String requestBody = "{"
                + "\"appliedAmount\": 5000.0,"
                + "\"status\": \"Pending\","
                + "\"applicationDate\": \"" + LocalDate.now() + "\","
                + "\"remarks\": \"Applying for the savings plan.\","
                + "\"proofDocument\": \"Base64EncodedDocument\","
                + "\"user\": {\"userId\": 1},"
                + "\"savingsPlan\": {\"savingsPlanId\": 1}"
                + "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + customerToken);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/planapplications", HttpMethod.POST, requestEntity, String.class);

        // Assert status is CREATED (201) as Customer has permission
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        System.out.println("Response status (Customer adding plan application): " + response.getStatusCode());
        System.out.println("Response body (Customer adding plan application): " + response.getBody());
    }

    @Test
    @Order(17)
    void backend_testAddPlanApplicationAsFinancialConsultant() throws Exception {
        Assertions.assertNotNull(financialConsultantToken, "financialConsultantToken should not be null");

        int planApplicationId = 1;
        String requestBody = "{"
        + "\"planApplicationId\": " + planApplicationId + ","
        + "\"appliedAmount\": 5000.0,"
        + "\"status\": \"Pending\","
        + "\"applicationDate\": \"" + LocalDate.now() + "\","
        + "\"remarks\": \"Applying for the savings plan.\","
        + "\"proofDocument\": \"Base64EncodedDocument\","
        + "\"user\": {\"userId\": 1},"
        + "\"savingsPlan\": {\"savingsPlanId\": 1}"
        + "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + financialConsultantToken);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/planapplications", HttpMethod.POST, requestEntity, String.class);

        // Assert status is FORBIDDEN (403) since FinancialConsultant does not have permission to add plan applications
        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        System.out.println("Response status (Financial Consultant adding plan application): " + response.getStatusCode());
        System.out.println("Response body (Financial Consultant adding plan application): " + response.getBody());
    }

    @Test
    @Order(18)
    void backend_testAddPlanApplicationAsInvalidToken() throws Exception {
        String requestBody = "{"
                + "\"appliedAmount\": 5000.0,"
                + "\"status\": \"Pending\","
                + "\"applicationDate\": \"" + LocalDate.now() + "\","
                + "\"remarks\": \"Applying for the savings plan.\","
                + "\"proofDocument\": \"Base64EncodedDocument\","
                + "\"user\": {\"id\": 1},"
                + "\"savingsPlan\": {\"id\": 1}"
                + "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer invalidtoken");
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/planapplications", HttpMethod.POST, requestEntity, String.class);

        // Assert status is UNAUTHORIZED
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        System.out.println("Response status (Invalid token): " + response.getStatusCode());
        System.out.println("Response body (Invalid token): " + response.getBody());
    }

    @Test
    @Order(19)
    void backend_testGetPlanApplicationsAsFinancialConsultant() throws Exception {
        Assertions.assertNotNull(financialConsultantToken, "financialConsultantToken should not be null");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + financialConsultantToken);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/planapplications", HttpMethod.GET, requestEntity, String.class);

        // Assert status is OK (200) as FinancialConsultant has permission
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println("Response status (Financial Consultant retrieving plan applications): " + response.getStatusCode());
        System.out.println("Response body (Financial Consultant retrieving plan applications): " + response.getBody());
    }

    @Test
    @Order(20)
    void backend_testGetPlanApplicationsAsRegionalManager() throws Exception {
        Assertions.assertNotNull(regionalManagerToken, "regionalManagerToken should not be null");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + regionalManagerToken);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/planapplications", HttpMethod.GET, requestEntity, String.class);

        // Assert status is OK (200) as RegionalManager has permission
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println("Response status (Regional Manager retrieving plan applications): " + response.getStatusCode());
        System.out.println("Response body (Regional Manager retrieving plan applications): " + response.getBody());
    }

    @Test
    @Order(21)
    void backend_testUpdatePlanApplicationAsCustomer() throws Exception {
        Assertions.assertNotNull(customerToken, "customerToken should not be null");

        Long testPlanApplicationId = 1L; // Replace with an actual ID or dynamically retrieve it
        String requestBody = "{"
                + "\"appliedAmount\": 6000.0,"
                + "\"status\": \"Approved\","
                + "\"applicationDate\": \"" + LocalDate.now() + "\","
                + "\"remarks\": \"Updated remarks for the application.\","
                + "\"proofDocument\": \"UpdatedBase64EncodedDocument\","
                + "\"user\": {\"id\": 1},"
                + "\"savingsPlan\": {\"id\": 1}"
                + "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + customerToken);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/planapplications/{id}", HttpMethod.PUT, requestEntity, String.class, testPlanApplicationId);

        // Assert status is OK (200) as Customer has permission to update their own plan applications
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println("Response status (Customer updating plan application): " + response.getStatusCode());
        System.out.println("Response body (Customer updating plan application): " + response.getBody());
    }

    @Test
    @Order(22)
    void backend_testUpdatePlanApplicationAsFinancialConsultant() throws Exception {
        Assertions.assertNotNull(financialConsultantToken, "financialConsultantToken should not be null");

        Long testPlanApplicationId = 1L; // Replace with an actual ID or dynamically retrieve it
        String requestBody = "{"
                + "\"appliedAmount\": 6000.0,"
                + "\"status\": \"Approved\","
                + "\"applicationDate\": \"" + LocalDate.now() + "\","
                + "\"remarks\": \"Updated remarks for the application.\","
                + "\"proofDocument\": \"UpdatedBase64EncodedDocument\","
                + "\"user\": {\"id\": 1},"
                + "\"savingsPlan\": {\"id\": 1}"
                + "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + financialConsultantToken);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/planapplications/{id}", HttpMethod.PUT, requestEntity, String.class, testPlanApplicationId);

        // Assert status is FORBIDDEN (403) since FinancialConsultant does not have permission to update plan applications
        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        System.out.println("Response status (Financial Consultant updating plan application): " + response.getStatusCode());
        System.out.println("Response body (Financial Consultant updating plan application): " + response.getBody());
    }






@Test
@Order(23)
void backend_testGetOwnFeedbackAsCustomer() throws Exception {
    Assertions.assertNotNull(customerToken, "Customer token should not be null");

    // Prepare request headers
    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + customerToken);

    // Ensure the URL matches your API endpoint
    String url = "/api/feedback/user/1";

    ResponseEntity<String> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            new HttpEntity<>(headers),
            String.class);

    // Assert status is OK (200)
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "Response status should be 200 OK");

    JsonNode responseBody = objectMapper.readTree(response.getBody());
    Assertions.assertTrue(responseBody.isArray(), "Response should be an array");
    
    // Optionally, check specific feedback details
}


@Test
@Order(24)
void backend_testGetAllFeedbackAsCustomer() throws Exception {
    Assertions.assertNotNull(customerToken, "Customer token should not be null");

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + customerToken);

    ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.GET,
            new HttpEntity<>(headers),
            String.class);

    // Assert status is OK (200)
    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
    System.out.println("Response status (Regional Manager): " + response.getStatusCode());
    System.out.println("Response body (Regional Manager): " + response.getBody());
}

@Test
@Order(25)
void backend_testGetAllFeedbackAsFinancialConsultant() throws Exception {
    Assertions.assertNotNull(financialConsultantToken, "Financial Consultant token should not be null");

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + financialConsultantToken);

    ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.GET,
            new HttpEntity<>(headers),
            String.class);

    // Assert status is OK (200)
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
    System.out.println("Response status (Financial Consultant): " + response.getStatusCode());
    System.out.println("Response body (Financial Consultant): " + response.getBody());
}


@Test
@Order(26)
void backend_testGetAllFeedbackAsRegionalManager() throws Exception {
    Assertions.assertNotNull(regionalManagerToken, "regionalManagerToken should not be null");

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + regionalManagerToken);
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

   ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.GET, requestEntity, String.class);
    Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    System.out.println("Unauthorized user (Financial Consultant) attempted to post feedback.");
}

@Test
@Order(27)
void backend_testGetFeedbackByUserIdAsCustomer() throws Exception {
    Assertions.assertNotNull(customerToken, "customerToken should not be null");

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + customerToken);
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    // Execute get request
    ResponseEntity<String> response = restTemplate.exchange("/api/feedback/user/1", HttpMethod.GET, requestEntity, String.class);

    Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    System.out.println("Unauthorized user (Financial Consultant) attempted to post feedback.");
}


@Test
@Order(28)
void backend_testGetFeedbackByFeedbackIdAsCustomer() throws Exception {
    Assertions.assertNotNull(customerToken, "customerToken should not be null");

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + customerToken);
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    // Execute get request
    ResponseEntity<String> response = restTemplate.exchange("/api/feedback/1", HttpMethod.GET, requestEntity, String.class);

    Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
}
@Test
@Order(29)
void backend_testAddInquiresAsCustomer() throws Exception {
    Assertions.assertNotNull(customerToken, "customerToken should not be null");

    Long testPlanApplicationId = 1L; // Replace with an actual ID or dynamically retrieve it

    String requestBody = "{"
    + "\"inquiryId\": " + 1 + ","
    + "\"message\": \"string\","
    + "\"user\": {"
    + "\"userId\": " + 1
    + "},"
    + "\"replied\": \"string\""
    + "}";


    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + customerToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/inquiries", HttpMethod.POST, requestEntity, String.class, testPlanApplicationId);

    Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
}

@Test
@Order(30)
void backend_testAddInquiresAsFinancialConsultant() throws Exception {
    Assertions.assertNotNull(financialConsultantToken, "financialConsultantToken should not be null");

    Long testPlanApplicationId = 1L; // Replace with an actual ID or dynamically retrieve it

    String requestBody = "{"
    + "\"inquiryId\": " + 1 + ","
    + "\"message\": \"string\","
    + "\"user\": {"
    + "\"userId\": " + 1
    + "},"
    + "\"replied\": \"string\""
    + "}";


    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + financialConsultantToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/inquiries", HttpMethod.POST, requestEntity, String.class, testPlanApplicationId);

    Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
}
@Test
@Order(31)
void backend_testAddInquiresAsRegionalManager() throws Exception {
    Assertions.assertNotNull(regionalManagerToken, "regionalManagerToken should not be null");

    Long testPlanApplicationId = 1L; // Replace with an actual ID or dynamically retrieve it

    String requestBody = "{"
    + "\"inquiryId\": " + 1 + ","
    + "\"message\": \"string\","
    + "\"user\": {"
    + "\"userId\": " + 1
    + "},"
    + "\"Replied\": \"string\""
    + "}";


    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + regionalManagerToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/inquiries", HttpMethod.POST, requestEntity, String.class, testPlanApplicationId);

    Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
}
@Test
@Order(32)
void backend_testGetAllInquiresAsCustomer() throws Exception {
    Assertions.assertNotNull(customerToken, "customerToken should not be null");

    Long testPlanApplicationId = 1L; // Replace with an actual ID or dynamically retrieve it

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + customerToken);
    HttpEntity<String> requestEntity = new HttpEntity<>( headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/inquiries", HttpMethod.GET, requestEntity, String.class, testPlanApplicationId);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
}

@Test
@Order(33)
void backend_testGetInquiresbyIdAsCustomer() throws Exception {
    Assertions.assertNotNull(customerToken, "customerToken should not be null");

    Long testPlanApplicationId = 1L; // Replace with an actual ID or dynamically retrieve it

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + customerToken);
    HttpEntity<String> requestEntity = new HttpEntity<>( headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/inquiries/1", HttpMethod.GET, requestEntity, String.class, testPlanApplicationId);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
}
@Test
@Order(34)
void backend_testGetInquiresbyIdAsFinancialConsultant() throws Exception {
    Assertions.assertNotNull(financialConsultantToken, "financialConsultantToken should not be null");

    Long testPlanApplicationId = 1L; // Replace with an actual ID or dynamically retrieve it

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + financialConsultantToken);
    HttpEntity<String> requestEntity = new HttpEntity<>( headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/inquiries/1", HttpMethod.GET, requestEntity, String.class, testPlanApplicationId);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
  
}
@Test
@Order(35)
void backend_testGetInquiresbyUserIdAsCustomer() throws Exception {
    Assertions.assertNotNull(customerToken, "customerToken should not be null");

    Long testPlanApplicationId = 1L; // Replace with an actual ID or dynamically retrieve it

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + customerToken);
    HttpEntity<String> requestEntity = new HttpEntity<>( headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/inquiries/user/1", HttpMethod.GET, requestEntity, String.class, testPlanApplicationId);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

}

@Test
@Order(36)
void backend_testUpdateInquiresbyIdAsFinancialConsultant() throws Exception {
    Assertions.assertNotNull(financialConsultantToken, "financialConsultantToken should not be null");

    Long testPlanApplicationId = 1L; // Replace with an actual ID or dynamically retrieve it

    String requestBody = "{"
    + "\"inquiryId\": " + 1 + ","
    + "\"message\": \"updated\","
    + "\"user\": {"
    + "\"userId\": " + 1
    + "},"
    + "\"Replied\": \"updated\""
    + "}";


    HttpHeaders headers = new HttpHeaders();

    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + financialConsultantToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/inquiries/1", HttpMethod.PUT, requestEntity, String.class, testPlanApplicationId);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
}
@Test
@Order(37)
void backend_testUpdateInquiresbyIdAsCustomer() throws Exception {
    Assertions.assertNotNull(customerToken, "customerToken should not be null");

    Long testPlanApplicationId = 1L; // Replace with an actual ID or dynamically retrieve it

    String requestBody = "{"
    + "\"inquiryId\": " + 1 + ","
    + "\"message\": \"updated\","
    + "\"user\": {"
    + "\"userId\": " + 1
    + "},"
    + "\"Replied\": \"updated\""
    + "}";


    HttpHeaders headers = new HttpHeaders();

    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + customerToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/inquiries/1", HttpMethod.PUT, requestEntity, String.class, testPlanApplicationId);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
}

@Test
@Order(38)
void backend_testInvalidCredentialsLogincustomer() throws Exception {
    // Use incorrect credentials
    String requestBody = "{\"email\": \"customer@gmail.com\", \"password\": \"wrongpassword\"}";
 
    ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
            new HttpEntity<>(requestBody, createHeaders()), String.class);
 
    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
 
   
}
 
 
 
 
@Test
@Order(39)
void backend_testInvalidCredentialsLoginfinancialConsultant() throws Exception {
    // Use incorrect credentials
    String requestBody = "{\"email\": \"financialConsultant@gmail.com\", \"password\": \"wrongpassword\"}";
 
    ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
            new HttpEntity<>(requestBody, createHeaders()), String.class);
 
    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
     
}
   
@Test
@Order(40)
void backend_testInvalidCredentialsLoginregionalManager() throws Exception {
    // Use incorrect credentials
    String requestBody = "{\"email\": \"regionalManager@gmail.com\", \"password\": \"wrongpassword\"}";
 
    ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
            new HttpEntity<>(requestBody, createHeaders()), String.class);
 
    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
 
   
}
}