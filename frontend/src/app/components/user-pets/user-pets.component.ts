import {Component, Input, OnInit} from '@angular/core';
import {Pet} from "../../models/pet";
import {UserService} from "../../services/user.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-user-pets',
  templateUrl: './user-pets.component.html',
  styleUrls: ['./user-pets.component.less']
})
export class UserPetsComponent implements OnInit {

  public pets: Pet[] = [];

  constructor(private userService: UserService,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.userService.retrieveUserPets(params['id']).subscribe(pets => {
        this.pets = pets;
      });
    });
  }
}
