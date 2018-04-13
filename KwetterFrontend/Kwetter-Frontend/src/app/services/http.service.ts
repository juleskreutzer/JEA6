import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {environment} from "../../environments/environment";

@Injectable()
export class HttpService {

  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient, private auth: AuthService) {

  }

  sendGetRequest(url: string) {
    return this.http.get<any>(this.baseUrl + url, {headers: { 'Authorization': this.auth.getToken()}})
      .map(data => { return data; });
  }

  sendPostRequest(url: string, body: object) {
    return this.http.post<any>(this.baseUrl + url, body, {headers: { 'Authorization': this.auth.getToken()}})
      .map(data => { return data; });

  }
}
