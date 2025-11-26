package com.__4c_platform.platform.controller;

import com.__4c_platform.platform.entity.Demand;
import com.__4c_platform.platform.service.DemandService;
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

    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> result = new HashMap<>();
        result.put("status", "ok");
        result.put("timestamp", new java.util.Date().toString());
        return result;
    }

    @GetMapping("/list")
    public Map<String, Object> getAllDemands() {
        Map<String, Object> result = new HashMap<>();
        System.out.println("=== getAllDemands 被调用 ===");
        try {
            System.out.println("开始查询需求列表");
            List<Demand> demands = demandService.getAllDemands();
            System.out.println("查询成功，需求数：" + (demands != null ? demands.size() : 0));
            
            // 简化返回的数据，避免Date序列化问题
            java.util.List<Map<String, Object>> simplifiedDemands = new java.util.ArrayList<>();
            if (demands != null) {
                for (Demand demand : demands) {
                    Map<String, Object> demandMap = new HashMap<>();
                    demandMap.put("id", demand.getId());
                    demandMap.put("title", demand.getTitle());
                    demandMap.put("description", demand.getDescription());
                    demandMap.put("publisherId", demand.getPublisherId());
                    demandMap.put("status", demand.getStatus());
                    simplifiedDemands.add(demandMap);
                }
            }
            result.put("success", true);
            result.put("data", simplifiedDemands);
            result.put("message", "获取需求列表成功");
            System.out.println("=== getAllDemands 返回成功 ===");
        } catch (Exception e) {
            System.out.println("=== getAllDemands 出错：" + e.getMessage());
            e.printStackTrace();
            result.put("success", false);
            result.put("data", null);
            result.put("message", "获取需求列表失败: " + e.getMessage());
        }
        return result;
    }

    @GetMapping("/publisher/{publisherId}")
    public Map<String, Object> getDemandsByPublisher(@PathVariable Long publisherId) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Demand> demands = demandService.getDemandsByPublisher(publisherId);
            result.put("success", true);
            result.put("data", demands);
            result.put("message", "获取发布者的需求列表成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("data", null);
            result.put("message", "获取需求列表失败: " + e.getMessage());
        }
        return result;
    }

    @PostMapping("/publish")
    public Map<String, Object> publishDemand(@RequestBody Demand demand) {
        Map<String, Object> result = new HashMap<>();
        boolean success = demandService.publishDemand(demand);
        result.put("success", success);
        result.put("message", success ? "需求发布成功" : "需求发布失败");
        return result;
    }
}