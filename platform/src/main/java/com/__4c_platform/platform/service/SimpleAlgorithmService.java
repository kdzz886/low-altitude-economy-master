// service/SimpleAlgorithmService.java
package com.__4c_platform.platform.service;

import com.__4c_platform.platform.entity.MatchResult;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service // 这个注解很重要，让Spring管理这个类
public class SimpleAlgorithmService implements AlgorithmService {

    @Override
    public List<MatchResult> matchEnterprises(Long demandId) {
        // 这里是模拟数据！将来会被真正的Python算法替换
        // 模拟返回两个企业的匹配结果
        MatchResult result1 = new MatchResult(
                1L,
                "大疆创新",
                0.92,
                "标签高度吻合：需求涉及'整机'，企业擅长'整机设计、制造'"
        );

        MatchResult result2 = new MatchResult(
                2L,
                "宁德时代",
                0.76,
                "业务相关：需求提及'电池'，企业主营'电池系统'"
        );

        return Arrays.asList(result1, result2);
    }
}