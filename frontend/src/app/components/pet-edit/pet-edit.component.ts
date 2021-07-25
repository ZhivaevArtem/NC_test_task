import { Component, OnInit } from '@angular/core';
import {Pet} from "../../models/pet";
import {Sex} from "../../models/sex";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {ActivatedRoute} from "@angular/router";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-pet-edit',
  templateUrl: './pet-edit.component.html',
  styleUrls: ['./pet-edit.component.less']
})
export class PetEditComponent implements OnInit {

  public pet: Pet = {
    breed: "", animalType: "", name: "", sex: Sex.UNKNOWN, picUrl: "",
    ownerId: "", id: "", description: "", color: "", birth: new Date()
  };
  public petCreateFormGroup = new FormGroup({
    'breed': new FormControl('', [
    ]),
    'animalType': new FormControl('', [
      Validators.required
    ]),
    'name': new FormControl('', [
      Validators.required
    ]),
    'sex': new FormControl('', [
      e => {
        return (e.value == Sex.OTHER ||
          e.value == Sex.FEMALE ||
          e.value == Sex.MALE) ? null : {sexValidate: {valid: false}}
      }
    ]),
    'description': new FormControl('', [
    ]),
    'color': new FormControl('', [
    ]),
    'birth': new FormControl('', [
    ])
  });

  constructor(private userService: UserService,
              private activatedRoute: ActivatedRoute,
              private authService: AuthService) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.pet.ownerId = params['id'];
      this.userService.retrieveUserSinglePet(params['id'], params['pet_id'])
        .subscribe(pet => {
          this.pet = pet;
        });
    });
  }

  public updatePet(): void {
    if (this.petCreateFormGroup.valid) {
      this.userService.updatePet(this.pet)
        .subscribe(pet => {
          this.authService.redirectToUserPage(pet.ownerId);
        }, error => {
          this.pet = {
            breed: "", animalType: "", name: "", sex: Sex.UNKNOWN, picUrl: "",
            ownerId: "", id: "", description: "", color: "", birth: new Date()
          };
        });
    }
  }

  public deletePet(): void {
    this.userService.deletePet(this.pet)
      .subscribe(pet => {
        this.authService.redirectToUserPage(this.pet.ownerId);
      }, error => {
      });
  }
}
