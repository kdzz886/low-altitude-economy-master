import axios from "./axios";
import { PublishDemandRequest, Demand, MatchedEnterprise } from "./types";

/** 获取需求列表 */
export function getDemandList() {
  return axios.get<Demand[]>("/api/demand/list");
}

/** 获取单个需求详情 */
export function getDemandDetail(id: number) {
  return axios.get<Demand>(`/api/demand/${id}`);
}

/** 发布需求 */
export function publishDemand(data: PublishDemandRequest) {
  return axios.post("/api/demand/publish", data);
}

/** 获取需求 + 匹配结果 */
export function getDemandWithMatches(id: number) {
  return axios.get<{
    success: boolean;
    demand: Demand;
    matchedEnterprises: MatchedEnterprise[];
  }>(`/api/demand/${id}/with-matches`);
}
