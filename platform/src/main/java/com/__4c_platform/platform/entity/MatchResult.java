// entity/MatchResult.java
package com.__4c_platform.platform.entity;

import lombok.Data;

@Data
public class MatchResult {
    private Long enterpriseId;
    private String enterpriseName;
    private Double matchScore;  // 匹配度 0-1
    private String matchReason; // 匹配原因

    // 如果需要，可以添加构造方法
    public MatchResult() {}

    public MatchResult(Long enterpriseId, String enterpriseName, Double matchScore, String matchReason) {
        this.enterpriseId = enterpriseId;
        this.enterpriseName = enterpriseName;
        this.matchScore = matchScore;
        this.matchReason = matchReason;
    }
}