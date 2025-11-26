package com.__4c_platform.platform.controller;

import com.__4c_platform.platform.entity.Demand;
import com.__4c_platform.platform.entity.MatchResult;
import com.__4c_platform.platform.service.DemandService;
import com.__4c_platform.platform.service.AlgorithmService;  // 新增导入
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/demand")
public class DemandController {

    @Autowired
    private DemandService demandService;

    @Autowired  // 新增：注入算法服务
    private AlgorithmService algorithmService;

    @GetMapping("/list")
    public List<Demand> getAllDemands() {
        return demandService.getAllDemands();
    }

    @GetMapping("/publisher/{publisherId}")
    public List<Demand> getDemandsByPublisher(@PathVariable Long publisherId) {
        return demandService.getDemandsByPublisher(publisherId);
    }

    @PostMapping("/publish")
    public Map<String, Object> publishDemand(@RequestBody Demand demand) {
        Map<String, Object> result = new HashMap<>();
        boolean success = demandService.publishDemand(demand);
        result.put("success", success);
        result.put("message", success ? "需求发布成功" : "需求发布失败");
        return result;
    }

    // ============ 新增的算法相关接口 ============

    /**
     * 获取需求详情及匹配的企业列表
     */
    @GetMapping("/{demandId}/with-matches")
    public Map<String, Object> getDemandWithMatches(@PathVariable Long demandId) {
        Map<String, Object> result = new HashMap<>();

        Demand demand = demandService.getDemandById(demandId);  // 需要确保有这个方法
        if (demand == null) {
            result.put("success", false);
            result.put("message", "需求不存在");
            return result;
        }

        // 调用算法服务获取匹配的企业
        List<MatchResult> matches = algorithmService.matchEnterprises(demandId);

        result.put("success", true);
        result.put("demand", demand);
        result.put("matchedEnterprises", matches);

        return result;
    }

    /**
     * 获取单个需求详情（原有的，如果没有的话可以加上）
     */
    @GetMapping("/{demandId}")
    public Map<String, Object> getDemandById(@PathVariable Long demandId) {
        Map<String, Object> result = new HashMap<>();
        Demand demand = demandService.getDemandById(demandId);
        if (demand != null) {
            result.put("success", true);
            result.put("data", demand);
        } else {
            result.put("success", false);
            result.put("message", "需求不存在");
        }
        return result;
    }
}