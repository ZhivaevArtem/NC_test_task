import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Client} from "../models/client";
import {Observable} from "rxjs";
import {AuthResponse} from "../models/auth-response";
import {map} from "rxjs/operators";
import {environment} from "../../environments/environment";
import {Credentials} from "../models/credentials";

@Injectable({
  providedIn: 'root'
})
export class AuthService implements OnInit {

  public isAuth: boolean = false;

  constructor(private httpClient: HttpClient) { }

  ngOnInit(): void {
    this.isAuth = !!localStorage.getItem("NC_auth_header");
  }

  // region private methods
  private storeAuthHeader(email: string, password: string): void {
    localStorage.setItem("NC_auth_header", "Basic " + btoa(email + ":" + password));
    this.isAuth = true;
  }

  private encodePassword(password: string): string {
    return password;
  }
  // endregion private methods

  public signUp(user: Client): Observable<AuthResponse> {
    return this.httpClient.post<AuthResponse>(`${environment.apiUrl}/auth/sign_up`, user)
      .pipe(map(authResponse => {
        this.storeAuthHeader(user.email, this.encodePassword(user.password));
        return authResponse;
      }));
  }

  public signIn(credentials: Credentials): Observable<AuthResponse> {
    return this.httpClient.post<AuthResponse>(`${environment.apiUrl}/auth/sign_in`, credentials)
      .pipe(map(authResponse => {
      this.storeAuthHeader(credentials.email, this.encodePassword(credentials.password));
      return authResponse;
    }));
  }

  public signOut(): void {
    localStorage.removeItem("NC_auth_header");
    this.isAuth = false;
  }

  public isEmailTaken(email: string): Observable<boolean> {
    return this.httpClient.get<boolean>(`${environment.apiUrl}/auth/is_email_taken?email=${email}`);
  }
}
