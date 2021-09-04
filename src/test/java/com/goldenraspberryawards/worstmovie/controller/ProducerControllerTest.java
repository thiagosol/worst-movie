package com.goldenraspberryawards.worstmovie.controller;

import com.goldenraspberryawards.worstmovie.dto.ProducersAwardsRangeMaxMinDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProducerControllerTest {

    private static String LOCAL_HOST = "localhost";

    @LocalServerPort
    private int port;

    TestRestTemplate testRestTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    void getProducersAwardsRange() {
        HttpEntity entity = new HttpEntity<>(null, headers);
        ResponseEntity<ProducersAwardsRangeMaxMinDTO> responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/producers/max-min-awards-range"), HttpMethod.GET, entity, ProducersAwardsRangeMaxMinDTO.class);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertNotNull(responseEntity.getBody().getMax());
        assertNotNull(responseEntity.getBody().getMin());
    }

    @Test
    void getProducersAwardsRangeWithNativeQuery() {
        HttpEntity entity = new HttpEntity<>(null, headers);
        ResponseEntity<ProducersAwardsRangeMaxMinDTO> responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/producers/native-query/max-min-awards-range"), HttpMethod.GET, entity, ProducersAwardsRangeMaxMinDTO.class);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertNotNull(responseEntity.getBody().getMax());
        assertNotNull(responseEntity.getBody().getMin());
    }

    private String createURLWithPort(String uri)
    {
        return String.format("http://%s:%s%s", LOCAL_HOST, port, uri);
    }
}