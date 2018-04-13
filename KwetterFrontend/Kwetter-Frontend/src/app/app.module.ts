import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { MatCardModule, MatIconModule, MatMenuModule, MatToolbarModule, MatGridListModule, MatButtonModule, MatFormFieldModule, MatInputModule } from "@angular/material";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AuthGuardService } from "./services/auth-guard.service";
import { AuthService } from "./services/auth.service";
import { HttpService } from './services/http.service'
import { CookieService } from 'ngx-cookie-service';
import { RouterModule, Routes } from "@angular/router";
import { KwetterComponent } from './kwetter/kwetter.component';
import { HttpClientModule } from '@angular/common/http';

import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { ProfileComponent } from './profile/profile.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';

const routes : Routes = [
  { path: 'login', component: LoginComponent, data: {title: 'Kwetter - Login'} },
  { path: 'register', component: RegisterComponent, data: {title: 'Kwetter - Register'} },
  { path: '', component: KwetterComponent, data: {title: 'Kwetter'}, canActivate: [AuthGuardService] },
  { path: 'profile/:id', component: ProfileComponent, data: { title: 'Kwetter Profile' }, canActivate: [AuthGuardService] },
  { path: 'me', component: EditProfileComponent, data: { title: 'Edit Kwetter Profile' }, canActivate: [AuthGuardService] },
  { path: '**', component: KwetterComponent, data: {title: 'Kwetter' }, canActivate: [AuthGuardService] }
];


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    KwetterComponent,
    ProfileComponent,
    EditProfileComponent
  ],
  imports: [
    RouterModule.forRoot(
      routes,
      { enableTracing: false }
    ),
    BrowserModule, BrowserAnimationsModule, MatToolbarModule,
    MatMenuModule, MatIconModule, MatFormFieldModule,
    FormsModule, ReactiveFormsModule, MatCardModule,
    MatGridListModule, MatButtonModule, MatInputModule,
    HttpClientModule
  ],
  providers: [
    AuthGuardService,
    AuthService,
    HttpService,
    CookieService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
