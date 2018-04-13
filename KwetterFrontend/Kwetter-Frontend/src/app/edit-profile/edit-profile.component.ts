import { Component, OnInit } from '@angular/core';
import {HttpService} from "../services/http.service";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  private currentUser: Account;

  constructor(private http: HttpService, private auth: AuthService) {
    this.currentUser = this.auth.getAccount();
  }

  ngOnInit() {
  }

  update() {

  }

}
