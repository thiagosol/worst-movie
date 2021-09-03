package com.goldenraspberryawards.worstmovie.controller;

import com.goldenraspberryawards.worstmovie.dto.ProducersAwardsRangeMaxMinDTO;
import com.goldenraspberryawards.worstmovie.service.ProducerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Produtores")
@RestController("ProducerController")
@RequestMapping("/producers")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @ApiOperation(value = "Retorna os produtores com maior e menor intervalo entre dois prêmios consecutivos",
            response = ProducersAwardsRangeMaxMinDTO.class)
    @GetMapping("/max-min-awards-range")
    public ResponseEntity<ProducersAwardsRangeMaxMinDTO> getProducersAwardsRange() {
        return ResponseEntity.ok(producerService.getProducersAwardsRange());
    }

    @ApiOperation(value = "Retorna os produtores com maior e menor intervalo entre dois prêmios consecutivos (Feito com Native Query)",
            response = ProducersAwardsRangeMaxMinDTO.class)
    @GetMapping("/native-query/max-min-awards-range")
    public ResponseEntity<ProducersAwardsRangeMaxMinDTO> getProducersAwardsRangeWithNativeQuery() {
        return ResponseEntity.ok(producerService.getProducersAwardsRangeWithNativeQuery());
    }

}
