package com.sistemas_distribuidos.pratica2.net_wheels_hub.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ReplyDataService<T> {

    private final String service1Url = "http://localhost:8081/api/carros";
    private final String service2Url = "http://localhost:8082/api/carros";
    private final String service3Url = "http://localhost:8083/api/carros";

    // TODO: Finalizar serviço de réplica crud
    public List<ResponseEntity<T>> replyCreateData(T entity) throws Exception {

        try {

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<T> requestEntity = new HttpEntity<>(entity, headers);

            ResponseEntity<T> response = restTemplate.postForEntity(this.service1Url, requestEntity, (Class<T>) entity.getClass());
            ResponseEntity<T> response2 = restTemplate.postForEntity(this.service2Url, requestEntity, (Class<T>) entity.getClass());
            ResponseEntity<T> response3 = restTemplate.postForEntity(this.service3Url, requestEntity, (Class<T>) entity.getClass());

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("REPLICANDO DADOS EM 3 BASES");
                System.out.println("RESPONSE CREATE CARRO BODY SERVICE 1: " + response.getBody());
                System.out.println("RESPONSE CREATE CARRO BODY SERVICE 2: " + response2.getBody());
                System.out.println("RESPONSE CREATE CARRO BODY SERVICE 3: " + response3.getBody());
                return Arrays.asList(response, response2, response3);

            } else {
                System.out.println(response);
            }
        }
        catch (Exception e) {
            throw new Exception(e);
        }

        return Arrays.asList();
    }

    public List<ResponseEntity<T>> replyReadData() throws Exception {
        return null;
    }

    public List<ResponseEntity<T>> replyUpdateData() throws Exception {
        return null;
    }

    public List<ResponseEntity<T>> replyDeleteData() throws Exception {
        return null;
    }

}