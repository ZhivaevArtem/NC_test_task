import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SignUpPageComponent} from "./components/sign-up-page/sign-up-page.component";
import {SignInPageComponent} from "./components/sign-in-page/sign-in-page.component";

const routes: Routes = [
  {path: "sign_up", component: SignUpPageComponent},
  {path: "sign_in", component: SignInPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
