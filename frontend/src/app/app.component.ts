import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Pet} from "./models/pet";
import {AuthService} from "./services/auth.service";
import {Router} from "@angular/router";
import {AuthResponse} from "./models/auth-response";
import {environment} from "../environments/environment";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent implements OnInit {

  public userId: string = "";

  constructor(public authService: AuthService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.authService.ngOnInit();
    this.userId = this.authService.getUserId();
  }

  public signOut(): void {
    this.authService.signOut();
    this.router.navigate(["sign_in"]);
  }
}
