import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Pet} from "../models/pet";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {AuthResponse} from "../models/auth-response";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  public retrieveUserInfo(id: string): Observable<AuthResponse> {
    return this.httpClient.get<AuthResponse>(`${environment.apiUrl}/auth/user_info`);
  }

  public retrieveUserPets(id: string): Observable<Pet[]> {
    return this.httpClient.get<Pet[]>(`${environment.apiUrl}/client/${id}/pet`);
  }

  public createPet(pet: Pet): Observable<Pet> {
    return this.httpClient.post<Pet>(`${environment.apiUrl}/client/${pet.ownerId}/pet`, pet);
  }
}
