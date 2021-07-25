import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {Client} from "../../models/client";
import {ActivatedRoute} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {Pet} from "../../models/pet";

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.less']
})
export class UserPageComponent implements OnInit {

  public user: Client = {
    id: "", name: "", surname: "", patronymic: "",
    email: "", phone: "", password: "", address: "", birth: new Date()
  };
  public pets: Pet[] = [];

  constructor(private userService: UserService,
              private authService: AuthService,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.userService.retrieveUserInfo(params['id'])
        .subscribe(authResponse => {
          this.user = authResponse.user;
        });
      this.userService.retrieveUserPets(params['id'])
        .subscribe(pets => this.pets = pets);
    });
  }
}
