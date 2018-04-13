import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {Kwet} from "../domain";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {HttpService} from "../services/http.service";

@Component({
  selector: 'app-kwetter',
  templateUrl: './kwetter.component.html',
  styleUrls: ['./kwetter.component.css']
})
export class KwetterComponent implements OnInit {

  currentUser: Account;
  kwets: Kwet[] = [];
  txtText: string;

  constructor(private authService: AuthService, private router: Router, private http: HttpService) {
    this.currentUser = JSON.parse(localStorage.getItem("currentUser")).account;
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  createKweet() {
    if(this.txtText !== undefined) {
      this.http.sendPostRequest('/api/kwets/create', {
        text: this.txtText,
        owner: this.currentUser,
        creationDate: new Date()
      })
      .subscribe(
        res => {
            window.location.reload();
        }, err => {
          if(err.status === 401) {
            this.router.navigate(['/login']);
          }
        }
      );
    } else {
      alert("Fill in some text");
    }
  }

  private loadAllKwets() {
    this.http.sendGetRequest('/api/kwets/all').subscribe(
      res => {
        var temp: Kwet[];
        temp = res;

        this.kwets = temp.sort(function(a, b) {
          if(b === undefined) { return 0; } else {
            return +new Date(b.creationDate.replace('[UTC]', '')) - +new Date(a.creationDate.replace('[UTC]', ''))
          }
        });
      },
      err => {
        if(err.status === 401) {
          console.log(err);
          this.router.navigate(['/login'])
        }
      }
    );
  }

  likeKweet(id: number) {
    console.log("Liking kweet with id " + id);
  }

  ngOnInit() {
    this.loadAllKwets();
  }

}
