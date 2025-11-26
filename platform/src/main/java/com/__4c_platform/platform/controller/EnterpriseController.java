package com.__4c_platform.platform.controller;

import com.__4c_platform.platform.entity.Enterprise;
import com.__4c_platform.platform.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping("/list")
    public List<Enterprise> getAllEnterprises() {
        return enterpriseService.getAllEnterprises();
    }

    @PostMapping("/add")
    public Map<String, Object> addEnterprise(@RequestBody Enterprise enterprise) {
        Map<String, Object> result = new HashMap<>();
        boolean success = enterpriseService.addEnterprise(enterprise);
        result.put("success", success);
        result.put("message", success ? "企业添加成功" : "企业添加失败");
        return result;
    }

    @GetMapping("/{id}")
    public Enterprise getEnterpriseById(@PathVariable Long id) {
        return enterpriseService.getEnterpriseById(id);
    }

    @PutMapping("/update")
    public Map<String, Object> updateEnterprise(@RequestBody Enterprise enterprise) {
        Map<String, Object> result = new HashMap<>();
        boolean success = enterpriseService.updateEnterprise(enterprise);
        result.put("success", success);
        result.put("message", success ? "企业更新成功" : "企业更新失败");
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> deleteEnterprise(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        boolean success = enterpriseService.deleteEnterprise(id);
        result.put("success", success);
        result.put("message", success ? "企业删除成功" : "企业删除失败");
        return result;
    }
}