import { Component, OnInit } from '@angular/core';
import {Client} from "../../models/client";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.less']
})
export class SignUpComponent implements OnInit {

  public user: Client = {
    id: "", name: "", surname: "", patronymic: "",
    email: "", phone: "", password: "",
    birth: new Date(), address: ""
  };

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  public signUp(): void {
    this.authService.signUp(this.user).subscribe(authResponse => {
      console.log("Success " + localStorage.getItem("NC_auth_header"));
    });
  }
}
