import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { CardModule } from 'primeng/card';
import { CoreModule } from './core/core.module';
import { MainModule } from './main/main.module';
import { AuthenticationService } from './core/authetication/authentication.service';
import { AuthGuard } from './core/authetication/auth.guard';
import { HttpClientModule } from '@angular/common/http';
import { MessagingService } from './core/messaging/messaging.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CardModule,
    CoreModule, 
    MainModule
  ],
  providers: [AuthenticationService, AuthGuard, MessagingService],
  bootstrap: [AppComponent]
})
export class AppModule { }
