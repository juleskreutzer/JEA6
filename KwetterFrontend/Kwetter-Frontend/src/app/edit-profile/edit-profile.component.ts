import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Account} from "../domain";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  private currentUser: Account;

  constructor(private http: HttpClient, private auth: AuthService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser')).account;
  }

  ngOnInit() {
  }

  update(email: string, location: string, fullname: string, profileimage: string, web: string, bio: string) {
    let id = this.auth.getAccount().id;
    // var user = {
    //   email: email,
    //   location: location,
    //   profileImage: profileimage,
    //   web: web,
    //   bio: bio
    // };
    // console.log(user);
    // this.http.sendPostRequest('/api/accounts/update/' + id, user)
    //   .subscribe(res => {
    //     alert('Profile updated!');
    //   }, err => {
    //     console.log(err);
    //     alert('Something went wrong');
    //   })

    this.http.post<any>(environment.apiUrl + '/api/accounts/update/' + id, {
      txtemail: email,
      txtlocation: location,
      txtfullname: fullname,
      txtprofileimage: profileimage,
      txtweb: web,
      txtbio: bio
    }, {headers: { 'Authorization': this.auth.getToken()}})
      .map(user => {
        return user;
      }).subscribe(res => {
        alert('Update ok!');
    }, err => {
        console.log(err);
        alert('something went wrong!');
    });

  }

}
