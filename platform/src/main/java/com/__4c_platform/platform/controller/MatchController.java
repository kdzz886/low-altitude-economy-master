// controller/MatchController.java
package com.__4c_platform.platform.controller;

import com.__4c_platform.platform.entity.MatchResult;
import com.__4c_platform.platform.service.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Autowired
    private AlgorithmService algorithmService;

    @GetMapping("/{demandId}")
    public List<MatchResult> getMatchResult(@PathVariable Long demandId) {
        // 这里直接调用算法服务
        return algorithmService.matchEnterprises(demandId);
    }
}