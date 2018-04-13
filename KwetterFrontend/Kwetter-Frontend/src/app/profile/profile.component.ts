import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {HttpService} from "../services/http.service";
import {Kwet} from "../domain";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  private profile: Account;
  private kwets: Kwet[];

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private http: HttpService, private auth: AuthService) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe((params: Params) => {
      console.log(this.auth.getAccount().id);
      console.log('ID from localstorage: ' + this.auth.getAccount().id);
      console.log('ID from URL param: ' + params['id']);
      if(params['id'] === undefined) {
        this.router.navigate(['']);
      } else if(params['id'] == this.auth.getAccount().id) {
        this.router.navigate(['me']);
      }

      let userId = params['id'];
      this.http.sendGetRequest('/api/accounts/id/' + userId)
        .subscribe(
          res => {
            this.profile = res;
          }, err => {
            alert("Something went wrong. Please try again");
            console.log(err);
          }
        );

      this.http.sendGetRequest('/api/kwets/owner/' + userId + '/all')
        .subscribe(
          res => {
            this.kwets = res
          }, err =>
          {
            alert("Couldn't load Kweets. Please try again");
            console.log(err);
          }
        )
    });
  }

  followUser() {
    let currentUser = this.auth.getAccount();
    if(currentUser.id === this.profile.id) {
      alert("You can't follow yourself!");
    } else {
      this.http.sendPostRequest('/api/accounts/follow/' + this.profile.id, { })
        .subscribe(
          res => {
            console.log(res);
          }, err => {
            console.log(err);
          }
        )
    }
  }

}
