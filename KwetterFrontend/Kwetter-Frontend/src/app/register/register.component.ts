import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router'
import { HttpClient } from "@angular/common/http";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  title = "Register";

  txtEmail : string;
  txtPassword : string;
  txtPasswordConfirm : string

  register() : void {
    if(this.txtEmail === undefined || this.txtPassword === undefined || this.txtPasswordConfirm === undefined) {
      alert("Please fill in all fields.");
    } else if(this.txtPasswordConfirm !== this.txtPassword) {
      alert("Both passwords do not match!");
    } else {
      this.http.post(environment.apiUrl + '/api/accounts/create', {
        email: this.txtEmail,
        password: this.txtPassword
      })
        .subscribe(res => {
          this.router.navigate(['/login']);
          console.log(res);
        }, err => {
          if(err['status'] === 406) {
            alert("This email address is allready registered. Please login.");
          } else {
            alert("Something went wrong! Please try again.");
            console.log(err);
          }
        });
    }
  }

  constructor(private http: HttpClient, private router: Router) {
  }

  ngOnInit() {
  }

}
