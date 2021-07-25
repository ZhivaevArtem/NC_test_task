import {Sex} from "./sex";

export interface Pet {
  id: string;
  name: string;
  sex: Sex;
  birth: Date;
  breed: string;
  color: string;
  description: string;
  animalType: string;
  ownerId: string;
  // TODO: implement images
  picUrl: string;
}
