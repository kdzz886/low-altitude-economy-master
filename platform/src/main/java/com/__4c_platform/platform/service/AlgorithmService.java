// service/AlgorithmService.java
package com.__4c_platform.platform.service;

import com.__4c_platform.platform.entity.MatchResult;
import java.util.List;

public interface AlgorithmService {
    /**
     * 智能匹配算法接口
     * @param demandId 需求ID
     * @return 匹配的企业列表，按匹配度从高到低排序
     */
    List<MatchResult> matchEnterprises(Long demandId);
}