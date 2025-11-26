import axios from "./axios";
import { LoginRequest, RegisterRequest, LoginResponse } from "./types";

export function login(data: LoginRequest) {
  return axios.post<LoginResponse>("/api/user/login", data);
}

export function register(data: RegisterRequest) {
  return axios.post("/api/user/register", data);
}
