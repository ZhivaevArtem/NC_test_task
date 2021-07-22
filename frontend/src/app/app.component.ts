import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Pet} from "./models/pet";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent {
  title = 'frontend';

  constructor(private httpClient: HttpClient) {
  }

  getPets() {
    this.httpClient.get<Pet[]>("http://localhost:8080/pet").subscribe(pets => {
      console.log(pets);
    });
  }

  clearLocalStorage() {
    localStorage.removeItem("NC_auth_header");
  }
}
