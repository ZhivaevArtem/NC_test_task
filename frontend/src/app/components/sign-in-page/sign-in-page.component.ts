import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Route, Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {Credentials} from "../../models/credentials";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in-page.component.html',
  styleUrls: ['./sign-in-page.component.less']
})
export class  SignInPageComponent implements OnInit {

  public buttonDisabledState = false;

  public credentials: Credentials = {
    email: "", password: ""
  };
  public signInFormGroup = new FormGroup({
    'email': new FormControl('', [
      Validators.required,
      Validators.email
    ]),
    'password': new FormControl('', [
      Validators.required,
      Validators.minLength(3)
    ])
  });

  constructor(private authService: AuthService,
              private router: Router) { }

  ngOnInit(): void {
  }

  public signIn(): void {
    if (this.signInFormGroup.valid) {
      this.buttonDisabledState = true;
      this.authService.signIn(this.credentials)
        .subscribe(authResponse => {
          this.authService.redirectAfterAuth(authResponse.user.id);
        }, error => {
          this.buttonDisabledState = false;
          this.credentials.password = "";
          this.credentials.email = "";
        });
    }
  }
}
