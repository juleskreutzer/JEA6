import {Component, NgModule} from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule, MatCheckboxModule, MatMenuModule, MatIconModule } from '@angular/material';
import { MatToolbarModule } from '@angular/material/toolbar';


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
            MatIconModule]
})

export class AppComponent {
  title = 'Kwetter';
}
