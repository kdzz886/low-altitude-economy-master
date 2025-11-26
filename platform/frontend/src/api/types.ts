/** 用户登录 */
export interface LoginRequest {
  username: string;
  password: string;
}
export interface LoginResponse {
  token: string;
  userId: number;
  username: string;
}

/** 用户注册 */
export interface RegisterRequest {
  username: string;
  password: string;
}

/** 发布需求 */
export interface PublishDemandRequest {
  title: string;
  description: string;
  publisherId: number;
  status: string;
}

/** 企业（简略版） */
export interface Enterprise {
  id: number;
  name: string;
  tags: string[];
  description?: string;
}

/** 需求 */
export interface Demand {
  id: number;
  title: string;
  description: string;
  publisherId: number;
  status: string;
}

/** 匹配结果 */
export interface MatchedEnterprise {
  enterpriseId: number;
  enterpriseName: string;
  matchScore: number;
  matchReason: string;
}
