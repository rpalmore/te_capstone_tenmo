package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.Account;
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

    public Account getBalance(int id){
        Account account = null;
       ResponseEntity<Account> response = restTemplate.exchange(baseUrl + "account/" + id,
               HttpMethod.GET , makeAuthEntity(), Account.class);
       account = response.getBody();
       return account;

    }

//helper method
    public HttpEntity<Void> makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return  new HttpEntity<>(headers);

    }

}
