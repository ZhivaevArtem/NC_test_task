import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Pet} from "../models/pet";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

}
