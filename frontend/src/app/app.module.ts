import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { SignUpPageComponent } from './components/sign-up-page/sign-up-page.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AuthHeaderInterceptor} from "./interceptors/auth-header.interceptor";
import { SignInPageComponent } from './components/sign-in-page/sign-in-page.component';
import { PetCardComponent } from './components/pet-card/pet-card.component';
import { UserPageComponent } from './components/user-page/user-page.component';
import { UserPetsComponent } from './components/user-pets/user-pets.component';
import { PetCardAddComponent } from './components/pet-card-add/pet-card-add.component';
import { PetCreateComponent } from './components/pet-create/pet-create.component';
import { PetEditComponent } from './components/pet-edit/pet-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    SignUpPageComponent,
    SignInPageComponent,
    PetCardComponent,
    UserPageComponent,
    UserPetsComponent,
    PetCardAddComponent,
    PetCreateComponent,
    PetEditComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthHeaderInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
