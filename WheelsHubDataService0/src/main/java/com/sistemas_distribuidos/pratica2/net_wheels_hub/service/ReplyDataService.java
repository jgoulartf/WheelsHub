package com.sistemas_distribuidos.pratica2.net_wheels_hub.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ReplyDataService<T> {

    private final String service1Url = "http://localhost:8081/api/carros";
    private final String service2Url = "http://localhost:8082/api/carros";
    private final String service3Url = "http://localhost:8083/api/carros";

    // TODO: Finalizar serviço de réplica crud
    public List<ResponseEntity<T>> replyCreateData(T entity) throws Exception {
        System.out.println("NEW CARRO: " + entity);
        try {

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<T> requestEntity = new HttpEntity<>(entity, headers);

            ResponseEntity<T> response = restTemplate.exchange(
                    this.service1Url,
                    HttpMethod.POST,
                    requestEntity,
                    (Class<T>) entity.getClass()
            );

            ResponseEntity<T> response2 = restTemplate.exchange(
                    this.service2Url,
                    HttpMethod.POST,
                    requestEntity,
                    (Class<T>) entity.getClass()
            );

            ResponseEntity<T> response3 = restTemplate.exchange(
                    this.service3Url,
                    HttpMethod.POST,
                    requestEntity,
                    (Class<T>) entity.getClass()
            );
            if (response.getStatusCode().is2xxSuccessful()) {

                System.out.println("_ _ _ _ _ LOG DE REPLICAÇÃO _ _ _ _ _");

                System.out.println("REPLICANDO DADOS EM 3 BASES");
                System.out.println("RESPONSE CREATE CARRO BODY SERVICE 1: " + response.getBody());
                System.out.println("RESPONSE CREATE CARRO BODY SERVICE 2: " + response2.getBody());
                System.out.println("RESPONSE CREATE CARRO BODY SERVICE 3: " + response3.getBody());

                System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
                return Arrays.asList(response, response2, response3);

            } else {
                System.out.println(response);
            }
        }
        catch (Exception e) {
            throw new Exception(e);
        }

        return List.of();
    }

    public List<ResponseEntity<T>> replyReadData() throws Exception {
        return null;
    }

    public List<ResponseEntity<T>> replyUpdateData(UUID id, T entity) throws Exception {

        try {

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<T> requestEntity = new HttpEntity<>(entity, headers);

            ResponseEntity<T> response = restTemplate.exchange(
                    this.service1Url + "/" + id,
                    HttpMethod.PUT,
                    requestEntity,
                    (Class<T>) entity.getClass()
            );

            ResponseEntity<T> response2 = restTemplate.exchange(
                    this.service2Url + "/" + id,
                    HttpMethod.PUT,
                    requestEntity,
                    (Class<T>) entity.getClass()
            );

            ResponseEntity<T> response3 = restTemplate.exchange(
                    this.service3Url + "/" + id,
                    HttpMethod.PUT,
                    requestEntity,
                    (Class<T>) entity.getClass()
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("_ _ _ _ _ LOG DE REPLICAÇÃO _ _ _ _ _");

                System.out.println("REPLICANDO DADOS EM 3 BASES");
                System.out.println("RESPONSE UPDATE CARRO BODY SERVICE 1: " + response.getBody());
                System.out.println("RESPONSE UPDATE CARRO BODY SERVICE 2: " + response2.getBody());
                System.out.println("RESPONSE UPDATE CARRO BODY SERVICE 3: " + response3.getBody());

                System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
                return Arrays.asList(response, response2, response3);

            } else {
                System.out.println(response);
            }
        }
        catch (Exception e) {
            throw new Exception(e);
        }

        return List.of();
    }

    public List<ResponseEntity<T>> replyDeleteData(UUID id) throws Exception {
        try {

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<T> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<T> response = restTemplate.exchange(
                    this.service1Url + "/" + id,
                    HttpMethod.DELETE,
                    requestEntity,
                    (Class<T>) null
            );

            ResponseEntity<T> response2 = restTemplate.exchange(
                    this.service2Url + "/" + id,
                    HttpMethod.DELETE,
                    requestEntity,
                    (Class<T>) null
            );

            ResponseEntity<T> response3 = restTemplate.exchange(
                    this.service3Url + "/" + id,
                    HttpMethod.DELETE,
                    requestEntity,
                    (Class<T>) null
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("_ _ _ _ _ LOG DE REPLICAÇÃO _ _ _ _ _");

                System.out.println("REPLICANDO DADOS EM 3 BASES");
                System.out.println("RESPONSE DELETE CARRO BODY SERVICE 1: " + response.getBody());
                System.out.println("RESPONSE DELETE CARRO BODY SERVICE 2: " + response2.getBody());
                System.out.println("RESPONSE DELETE CARRO BODY SERVICE 3: " + response3.getBody());

                System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
                return Arrays.asList(response, response2, response3);

            } else {
                System.out.println(response);
            }
        }
        catch (Exception e) {
            throw new Exception(e);
        }

        return List.of();
    }

    public List<ResponseEntity<T>> replyBuyCarData(UUID id, T entity) throws Exception {

        try {

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<T> requestEntity = new HttpEntity<>(entity, headers);

            ResponseEntity<T> response = restTemplate.exchange(
                    this.service1Url + "/" + id,
                    HttpMethod.PUT,
                    requestEntity,
                    (Class<T>) entity.getClass()
            );

            ResponseEntity<T> response2 = restTemplate.exchange(
                    this.service2Url + "/" + id,
                    HttpMethod.PUT,
                    requestEntity,
                    (Class<T>) entity.getClass()
            );

            ResponseEntity<T> response3 = restTemplate.exchange(
                    this.service3Url + "/" + id,
                    HttpMethod.PUT,
                    requestEntity,
                    (Class<T>) entity.getClass()
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("_ _ _ _ _ LOG DE REPLICAÇÃO _ _ _ _ _");

                System.out.println("REPLICANDO DADOS EM 3 BASES");
                System.out.println("RESPONSE | COMPRA DE CARRO | SERVICE 1: " + response.getBody());
                System.out.println("RESPONSE | COMPRA DE CARRO | SERVICE 2: " + response2.getBody());
                System.out.println("RESPONSE | COMPRA DE CARRO | SERVICE 3: " + response3.getBody());

                System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
                return Arrays.asList(response, response2, response3);

            } else {
                System.out.println(response);
            }
        }
        catch (Exception e) {
            throw new Exception(e);
        }

        return List.of();
    }

}