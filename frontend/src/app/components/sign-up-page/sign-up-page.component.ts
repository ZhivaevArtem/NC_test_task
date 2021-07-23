import { Component, OnInit } from '@angular/core';
import {Client} from "../../models/client";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up-page.component.html',
  styleUrls: ['./sign-up-page.component.less']
})
export class SignUpPageComponent implements OnInit {

  public user: Client = {
    id: "", name: "Artem", surname: "Zhivaev", patronymic: "Evgenievich",
    email: "zhivaev993@gmail.com", phone: "+79200237099", password: "123",
    birth: new Date(), address: "asdfasdf"
  };

  constructor(private authService: AuthService,
              private router: Router) { }

  ngOnInit(): void {
  }

  public signUp(): void {
    this.authService.signUp(this.user).subscribe(authResponse => {
      this.router.navigate(["/"]);
    });
  }
}
