package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.*;
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

    public User[] getUserIdAndName(){
        User[] user = null;
        ResponseEntity<User[]> response = restTemplate.exchange(baseUrl + "users",
                HttpMethod.GET, makeAuthEntity(), User[].class);
        user = response.getBody();
        return user;
    }

    public Transfer[] viewTransferHistory(){
       Transfer[] transfer = null;
        ResponseEntity<Transfer[]> response = restTemplate.exchange(baseUrl + "transfer",
                HttpMethod.GET, makeAuthEntity(), Transfer[].class);
        transfer = response.getBody();
        return transfer;
    }

    public TransferDetail viewTransferDetail(int id){
        TransferDetail transferDetail = null;
        ResponseEntity<TransferDetail> response = restTemplate.exchange(baseUrl + "transfer/" + id,
                HttpMethod.GET, makeAuthEntity(), TransferDetail.class);
        transferDetail=response.getBody();
        return transferDetail;
    }


    public TransferDTO transferDTORequest(TransferDTO transferDTO){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);

        HttpEntity<TransferDTO> entity = new HttpEntity<>(transferDTO, headers);
        transferDTO= restTemplate.postForObject(baseUrl+ "transfer",
                entity,TransferDTO.class);
        return transferDTO;
    }

    public TransferDTO processDTORequest(TransferDTO transferDTO){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);

        HttpEntity<TransferDTO> entity = new HttpEntity<>(transferDTO, headers);
        restTemplate.put(baseUrl + "transfer", entity, TransferDTO.class);

        return transferDTO;
    }

    // helper method
    public HttpEntity<Void> makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }

}
