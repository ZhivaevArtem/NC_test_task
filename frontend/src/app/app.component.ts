import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Pet} from "./models/pet";
import {AuthService} from "./services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent {

  constructor(public authService: AuthService,
              private router: Router) {
  }

  public signOut(): void {
    this.authService.signOut();
    this.router.navigate(["sign_in"]);
  }
}
