import { Component, OnInit } from '@angular/core';
import {Client} from "../../models/client";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up-page.component.html',
  styleUrls: ['./sign-up-page.component.less']
})
export class SignUpPageComponent implements OnInit {

  public isEmailTaken = false;
  public buttonDisableState = false;
  public user: Client = {
    id: "", name: "", surname: "", patronymic: "",
    email: "", phone: "", password: "",
    birth: new Date(), address: ""
  };
  public signUpFormGroup = new FormGroup({
    'email': new FormControl('', [
      Validators.required,
      Validators.email,
      e => this.isEmailTaken ? {'emailValidator': {'valid': false}} : null
    ]),
    'password': new FormControl('', [
      Validators.required,
      Validators.minLength(3)
    ]),
    'name': new FormControl('', [
      Validators.required,
      Validators.pattern("[a-zA-Zа-яА-Я]+$")
    ]),
    'surname': new FormControl('', [
      Validators.required,
    ]),
    'phone': new FormControl('', [
      Validators.required,
      Validators.minLength(12),
      Validators.pattern("^\\+[0-9]+$")
    ])
  });

  constructor(private authService: AuthService,
              private router: Router) { }

  ngOnInit(): void {
    this.signUpFormGroup.markAsUntouched();
  }

  public signUp(): void {
    if (this.signUpFormGroup.valid) {
      this.buttonDisableState = true;
      this.authService.signUp(this.user).subscribe(authResponse => {
        this.router.navigate(["/"]);
      }, error => {
        this.buttonDisableState = false;
      });
    }
  }

  public updateIsEmailTaken(): void {
    console.log('update');
    this.authService.isEmailTaken(this.user.email)
      .subscribe(response => {
        this.isEmailTaken = response;
        console.log(this.isEmailTaken);
        this.signUpFormGroup.controls['email'].updateValueAndValidity();
      });
  }
}
