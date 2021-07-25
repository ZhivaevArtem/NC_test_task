import {Component, Input, OnInit} from '@angular/core';
import {Pet} from "../../models/pet";
import {Sex} from "../../models/sex";

@Component({
  selector: 'app-pet-card',
  templateUrl: './pet-card.component.html',
  styleUrls: ['./pet-card.component.less']
})
export class PetCardComponent implements OnInit {

  @Input()
  public pet: Pet = {
    name: "", animalType: "", birth: new Date(),
    breed: "", color: "", description: "", id: "",
    ownerId: "", sex: Sex.UNKNOWN
  };

  constructor() { }

  ngOnInit(): void {
  }

}
