package com.__4c_platform.platform.dto;

import lombok.Data;

@Data
public class EnterpriseMatchResult {
    private Long enterpriseId;
    private String enterpriseName;
    private Double matchScore;  // 匹配度 0-1
    private String matchReason; // 匹配原因

    // 无参构造方法（必须）
    public EnterpriseMatchResult() {
    }

    // 有参构造方法
    public EnterpriseMatchResult(Long enterpriseId, String enterpriseName,
                                 Double matchScore, String matchReason) {
        this.enterpriseId = enterpriseId;
        this.enterpriseName = enterpriseName;
        this.matchScore = matchScore;
        this.matchReason = matchReason;
    }
}