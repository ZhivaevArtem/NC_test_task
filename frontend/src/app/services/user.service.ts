import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Pet} from "../models/pet";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PetService {

  constructor(private httpClient: HttpClient) { }

  public getAllPets(): Observable<Pet[]> {
    return this.httpClient.get<Pet[]>('http://localhost:8080/pet');
  }
}
