import {Component, Input, OnInit} from '@angular/core';
import {Pet} from "../models/pet";

@Component({
  selector: 'app-pet-item',
  templateUrl: './pet-item.component.html',
  styleUrls: ['./pet-item.component.less']
})
export class PetItemComponent implements OnInit {

  @Input()
  public pet: Pet = {id: "", name: "", type: ""};

  constructor() {}

  ngOnInit(): void {
  }

}
