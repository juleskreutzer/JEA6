import { Injectable } from '@angular/core';
import {WebsocketService} from "./websocket.service";
import {Subject} from "rxjs/Subject";
import {Kwet} from "../domain";


const url = 'ws://localhost:8080/Kwetter-1.0/socket';

@Injectable()
export class NewKweetService {

  public messages: Subject<Kwet>;

  constructor(wsService: WebsocketService) {
    this.messages = <Subject<Kwet>>wsService
      .connect(url)
      .map((response: MessageEvent): Kwet => {
        let data = JSON.parse(response.data);
        return data
      });
  }

}
