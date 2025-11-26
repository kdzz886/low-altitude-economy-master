package com.__4c_platform.platform.service;

import com.__4c_platform.platform.entity.Enterprise;
import com.__4c_platform.platform.mapper.EnterpriseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterpriseService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    public List<Enterprise> getAllEnterprises() {
        return enterpriseMapper.selectAll();
    }

    public Enterprise getEnterpriseById(Long id) {
        return enterpriseMapper.selectById(id);
    }

    public boolean addEnterprise(Enterprise enterprise) {
        return enterpriseMapper.insert(enterprise) > 0;
    }

    public boolean updateEnterprise(Enterprise enterprise) {
        return enterpriseMapper.update(enterprise) > 0;
    }

    public boolean deleteEnterprise(Long id) {
        return enterpriseMapper.deleteById(id) > 0;
    }
}