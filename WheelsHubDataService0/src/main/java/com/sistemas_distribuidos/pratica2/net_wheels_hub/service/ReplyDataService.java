package com.sistemas_distribuidos.pratica2.net_wheels_hub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.beans.ConstructorProperties;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class ReplyDataService<T> {

    private String entityPath = "";

    private String service1Url = "http://localhost:8081/api/";
    private String service2Url = "http://localhost:8082/api/";
    private String service3Url = "http://localhost:8083/api/";


    public ReplyDataService(String entityPath) {
        this.entityPath = entityPath;
        this.service1Url = "http://localhost:8081/api" + entityPath;
        this.service2Url = "http://localhost:8082/api" + entityPath;
        this.service3Url = "http://localhost:8083/api" + entityPath;
    }


    // DONE: Finalizar serviço de réplica crud
    public List<ResponseEntity<T>> replyCreateData(T entity) throws Exception {

        int timeout = 4_000; // 4 segundos

        // Configurar o Factory de Requisições HTTP com o tempo limite
        SimpleClientHttpRequestFactory clientHttpRequestFactory  = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        clientHttpRequestFactory.setReadTimeout(timeout);

        // Criar o RestTemplate com o Factory de Requisições HTTP configurad
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<T> requestEntity = new HttpEntity<>(entity, headers);

        System.out.println("NEW CARRO: " + entity);
        try {

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
            if (response.getStatusCode().is2xxSuccessful() && response2.getStatusCode().is2xxSuccessful() && response3.getStatusCode().is2xxSuccessful()) {

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

        } catch (Exception e) {
            System.out.println("para");
            // Dobra o tempo do timeout para esperar o serviço voltar
            timeout = timeout * 4;
            clientHttpRequestFactory.setConnectTimeout(timeout);
            clientHttpRequestFactory.setReadTimeout(timeout);

            restTemplate.setRequestFactory(clientHttpRequestFactory);

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
            if (response.getStatusCode().is2xxSuccessful() && response2.getStatusCode().is2xxSuccessful() && response3.getStatusCode().is2xxSuccessful()) {

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
            System.out.println("para");
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

    // TODO: Finalizar reply dessa operação
    public List<ResponseEntity<String>> replyBuyCarData(UUID id, T entity) throws Exception {
        System.out.println("para");

        try {

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<T> requestEntity = new HttpEntity<>(entity, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    this.service1Url + "/compraCarro/" + id,
                    HttpMethod.POST,
                    requestEntity,
                    (Class<String>) null
            );

            ResponseEntity<String> response2 = restTemplate.exchange(
                    this.service2Url + "/compraCarro/" + id,
                    HttpMethod.POST,
                    requestEntity,
                    (Class<String>) null
            );

            ResponseEntity<String> response3 = restTemplate.exchange(
                    this.service3Url + "/compraCarro/" + id,
                    HttpMethod.POST,
                    requestEntity,
                    (Class<String>) null
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