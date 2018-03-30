import {Component, NgModule} from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule, MatCheckboxModule, MatMenuModule, MatIconModule, MatCardModule } from '@angular/material';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

@NgModule({
  imports: [BrowserAnimationsModule,
            MatToolbarModule,
            MatButtonModule,
            MatCheckboxModule,
            MatMenuModule,
            MatIconModule,
            MatCardModule,
            MatFormFieldModule]
})

export class AppComponent {
  title = 'Kwetter';

  constructor(private http : HttpClient) {

  }


}
