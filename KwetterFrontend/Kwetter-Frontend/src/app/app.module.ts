import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { MatCardModule, MatIconModule, MatMenuModule, MatToolbarModule, MatGridListModule, MatButtonModule, MatFormFieldModule, MatInputModule } from "@angular/material";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { RouterModule, Routes } from "@angular/router";
import { KwetterComponent } from './kwetter/kwetter.component';
import { HttpClientModule } from '@angular/common/http';

import { FormsModule, ReactiveFormsModule} from '@angular/forms';

const routes : Routes = [
  { path: 'login', component: LoginComponent, data: {title: 'Kwetter - Login'} },
  { path: 'register', component: RegisterComponent, data: {title: 'Kwetter - Register'} },
  { path: '', component: KwetterComponent, data: {title: 'Kwetter'} }
];


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    KwetterComponent
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
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
