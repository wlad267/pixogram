import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './authetication/login/login.component';
import { RegisterComponent } from './authetication/register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MessagingComponent } from './messaging/messaging.component';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';
import { BlockUIModule } from 'primeng/blockui';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { LoadingComponent } from './loading/loading.component';

@NgModule({
  declarations: [LoginComponent, RegisterComponent, MessagingComponent, LoadingComponent],
  imports: [
    CommonModule,
    FormsModule, 
    ReactiveFormsModule,
    RouterModule,
    MessageModule,
    MessagesModule,
    BrowserAnimationsModule,
    BlockUIModule,
    ProgressSpinnerModule
  ],
  exports: [LoginComponent, RegisterComponent, MessagingComponent, LoadingComponent]
})
export class CoreModule { }
