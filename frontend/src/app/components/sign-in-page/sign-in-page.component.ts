import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Route, Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {Credentials} from "../../models/credentials";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in-page.component.html',
  styleUrls: ['./sign-in-page.component.less']
})
export class SignInPageComponent implements OnInit {

  public credentials: Credentials = {
    email: "", password: ""
  };

  constructor(private authService: AuthService,
              private router: Router) { }

  ngOnInit(): void {
  }

  public signIn(): void {
    this.authService.signIn(this.credentials)
      .subscribe(authResponse => {
        this.router.navigate([""]);
      });
  }
}
