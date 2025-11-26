package com.__4c_platform.platform.service;

import com.__4c_platform.platform.entity.Demand;
import com.__4c_platform.platform.mapper.DemandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandService {

    @Autowired
    private DemandMapper demandMapper;

    public List<Demand> getAllDemands() {
        return demandMapper.selectAll();
    }

    public List<Demand> getDemandsByPublisher(Long publisherId) {
        return demandMapper.selectByPublisherId(publisherId);
    }

    public boolean publishDemand(Demand demand) {
        // 设置默认状态为 pending
        if (demand.getStatus() == null) {
            demand.setStatus("pending");
        }
        return demandMapper.insert(demand) > 0;
    }
}