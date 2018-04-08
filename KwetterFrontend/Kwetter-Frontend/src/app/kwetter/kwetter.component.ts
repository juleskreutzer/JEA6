import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {Kwet} from "../domain";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-kwetter',
  templateUrl: './kwetter.component.html',
  styleUrls: ['./kwetter.component.css']
})
export class KwetterComponent implements OnInit {

  currentUser: Account;
  txtText: string;

  constructor(private authService: AuthService, private router: Router, private http: HttpClient) {
    this.currentUser = JSON.parse(localStorage.getItem("currentUser")).account;
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  createKweet() {
    if(this.txtText !== undefined) {
      this.http.post(environment.apiUrl + '/api/kwets/create', {
        text: this.txtText,
        owner: this.currentUser,
        creationDate: new Date()
      })
        .subscribe(res => {
          console.log(res);
        }, err => {
          console.log(err);
        });
    } else {
      alert("Fill in some text");
    }


  }

  ngOnInit() {
  }

}
