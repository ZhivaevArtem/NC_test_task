import { Component, OnInit } from '@angular/core';
import {Pet} from "../models/pet";
import {PetService} from "../services/pet.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.less']
})
export class HomeComponent implements OnInit {

  public pets: Pet[] = [];

  constructor(private petService: PetService) { }

  ngOnInit(): void {
    this.petService.getAllPets().subscribe(pets => {
      this.pets = pets;
    });
  }

}
