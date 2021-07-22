import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Client} from "../models/client";
import {Observable} from "rxjs";
import {AuthResponse} from "../models/auth-response";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient) { }

  private storeAuthHeader(email: string, password: string): void {
    localStorage.setItem("NC_auth_header", "Basic " + btoa(email + ":" + password));
  }

  private encodePassword(password: string): string {
    return password;
  }

  public signUp(user: Client): Observable<AuthResponse> {
    return this.httpClient.post<AuthResponse>("http://localhost:8080/auth/sign_up", user)
      .pipe(map(authResponse => {
        this.storeAuthHeader(user.email, this.encodePassword(user.password));
        return authResponse;
      }));
  }
}
