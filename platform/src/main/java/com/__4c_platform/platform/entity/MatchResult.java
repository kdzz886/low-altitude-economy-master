// entity/MatchResult.java
package com.__4c_platform.platform.entity;

import lombok.Data;

@Data
public class MatchResult {
    private Long enterpriseId;     // 匹配上的企业ID
    private String enterpriseName; // 企业名称（用于前端直接显示）
    private Double matchScore;     // 匹配度分数 (例如 0.85 代表85%)
    private String reason;         // 匹配原因 (例如："标签高度吻合：都涉及'整机'与'零部件'")

    // 全参构造函数，方便算法同学使用
    public MatchResult(Long enterpriseId, String enterpriseName, Double matchScore, String reason) {
        this.enterpriseId = enterpriseId;
        this.enterpriseName = enterpriseName;
        this.matchScore = matchScore;
        this.reason = reason;
    }
}