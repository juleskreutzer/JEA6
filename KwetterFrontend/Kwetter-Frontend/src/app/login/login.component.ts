import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http'
import { Router, ActivatedRoute } from '@angular/router'
import {AuthService} from "../services/auth.service";
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  title = "Inloggen";
  returnUrl: String

  email = new FormControl('', [Validators.required, Validators.email]);
  txtEmail : string
  txtPassword : string


  getErrorMessage() {
    return this.email.hasError('required') ? 'You must enter a value' :
      this.email.hasError('email') ? 'Not a valid email' :
        '';
  }

  login() : void {
    if(this.txtEmail && this.txtPassword) {
      this.auth.login(this.txtEmail, this.txtPassword)
        .subscribe(
          data => {
            this.router.navigate([this.returnUrl]);
          },
          error => {
            if(error.status === 401) {
              alert("Email/password combination is incorrect. Please try again");
            } else {
              alert("Something went wrong. Please try again later.");
            }
          });
    } else {
      alert("Please fill in email and/or password");
    }

  }

  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute, private auth: AuthService) { }

  ngOnInit() {
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

}
