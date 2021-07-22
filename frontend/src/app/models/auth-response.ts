import {Client} from "./client";

export interface AuthResponse {
  user: Client;
  roleList: string[];
}
