import axios from "./axios";
import { Enterprise } from "./types";

export function getEnterpriseList() {
  return axios.get<Enterprise[]>("/api/enterprise/list");
}

export function getEnterpriseDetail(id: number) {
  return axios.get<Enterprise>(`/api/enterprise/${id}`);
}
