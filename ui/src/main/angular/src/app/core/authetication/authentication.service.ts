import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { User } from '../../main/home/user.model';
import { MessagingService } from '../messaging/messaging.service';
import { LoadingService } from '../loading/loading.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpClient: HttpClient,
    private router: Router,
    private messagingService: MessagingService,
    private loadingService: LoadingService) { }

  login(email: string, password: string) {
    console.log(' logging: ' + email + ' p: ' + password);
    this.loadingService.block();
    this.httpClient.post('api/auth/login', { email: email, password: password })
      .subscribe((user: User) => {
        console.log('user logged as ' + user);
        localStorage.setItem('currentUser', JSON.stringify(user));
        this.router.navigate(['/home']);
        this.loadingService.unblock();
      },
        error => this.handError(error));
  };

  logout() {
    console.log('logout');
    this.loadingService.block();
    this.httpClient.get('api/auth/logout').subscribe(() => {
      localStorage.removeItem('currentUser');
      this.router.navigate(['/login']);
      this.loadingService.unblock();
      console.log('succesful logout');
    },
      error => this.handError(error)
    );
  }

  private handError(error: HttpErrorResponse) {
    console.error(error);
    this.loadingService.unblock();
    this.messagingService.error(error.message)
  }
}
