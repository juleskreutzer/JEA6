import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  title = "Inloggen";

  email = new FormControl('', [Validators.required, Validators.email]);
  txtEmail : string
  txtPassword : string


  getErrorMessage() {
    return this.email.hasError('required') ? 'You must enter a value' :
      this.email.hasError('email') ? 'Not a valid email' :
        '';
  }

  login() : void {
    if(this.txtEmail === undefined || this.txtPassword === undefined ) {
      console.log("email or password not defined!");
    } else {
      console.log("Email is: " + this.txtEmail + "\nPassword is: " + this.txtPassword);
    }

  }

  constructor() { }

  ngOnInit() {
  }

}
