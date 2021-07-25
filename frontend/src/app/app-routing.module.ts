import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SignUpPageComponent} from "./components/sign-up-page/sign-up-page.component";
import {SignInPageComponent} from "./components/sign-in-page/sign-in-page.component";
import {UserPageComponent} from "./components/user-page/user-page.component";
import {PetCreateComponent} from "./components/pet-create/pet-create.component";
import {PetEditComponent} from "./components/pet-edit/pet-edit.component";

const routes: Routes = [
  {path: "sign_up", component: SignUpPageComponent},
  {path: "sign_in", component: SignInPageComponent},
  {path: "users/:id", component: UserPageComponent},
  {path: "users/:id/create_pet", component: PetCreateComponent},
  {path: "users/:id/pets/:pet_id", component: PetEditComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
