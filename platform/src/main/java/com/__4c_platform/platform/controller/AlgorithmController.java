// controller/AlgorithmController.java
package com.__4c_platform.platform.controller;

import com.__4c_platform.platform.entity.MatchResult;
import com.__4c_platform.platform.service.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/algorithm")
public class AlgorithmController {

    @Autowired
    private AlgorithmService algorithmService;

    /**
     * 获取需求匹配的企业列表
     */
    @GetMapping("/demand/{demandId}/matches")
    public Map<String, Object> getDemandMatches(@PathVariable Long demandId) {
        Map<String, Object> result = new HashMap<>();

        try {
            List<MatchResult> matches = algorithmService.matchEnterprises(demandId);

            result.put("success", true);
            result.put("data", matches);

        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "算法匹配失败: " + e.getMessage());
        }

        return result;
    }

    /**
     * 测试算法匹配的接口
     */
    @PostMapping("/test-match")
    public Map<String, Object> testAlgorithmMatch(@RequestBody Map<String, String> params) {
        String testText = params.get("text");

        Map<String, Object> result = new HashMap<>();

        // 使用固定的需求ID进行测试
        List<MatchResult> matches = algorithmService.matchEnterprises(1L);

        result.put("success", true);
        result.put("testText", testText);
        result.put("matches", matches);

        return result;
    }
}