package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class TenmoService {

    private String baseUrl;
    String authToken;
    RestTemplate restTemplate = new RestTemplate();

    public TenmoService(String baseUrl, String authToken) {
        this.baseUrl = baseUrl;
        this.authToken = authToken;
    }

    public Account getBalance(){
        Account account = null;
       ResponseEntity<Account> response = restTemplate.exchange(baseUrl + "balance",
               HttpMethod.GET, makeAuthEntity(), Account.class);
       account = response.getBody();
       return account;

    }

    public User[] getUserIdAndName(){ // does this need to be same method name?
        User[] user = null;
        ResponseEntity<User[]> response = restTemplate.exchange(baseUrl + "users",
                HttpMethod.GET, makeAuthEntity(), User[].class);
        user = response.getBody();
        return user;
    }

  /*  public Review[] listReviews() {
        return restTemplate.getForObject(API_BASE_URL + "reviews", Review[].class);
    }

    public Hotel getHotelById(int id) {
        return restTemplate.getForObject(API_BASE_URL + "hotels/" + id, Hotel.class);
    }

    public Review[] getReviewsByHotelId(int hotelID) {
        return restTemplate.getForObject(API_BASE_URL + "hotels/" + hotelID + "/reviews", Review[].class);
*/
        //helper method
    public HttpEntity<Void> makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return  new HttpEntity<>(headers);
    }

}
