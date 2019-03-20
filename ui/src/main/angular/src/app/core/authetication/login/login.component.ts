import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '../authentication.service';
import { MessagingService } from '../../messaging/messaging.service';
import { LoadingService} from '../../loading/loading.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private messagingService: MessagingService,
    private loadingService: LoadingService) {
    }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
        email: ['admin@bluementors.com', Validators.email],
        password: ['', [Validators.required, Validators.minLength(6)]]
    });

    // reset login status
    //this.authenticationService.logout();

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
}

  onSubmit() {
     console.log(this.loginForm);
     console.log(this.loginForm.controls.email.errors);
     console.log(this.loginForm.controls.email.errors && this.loginForm.controls.email.errors.email);
     this.authenticationService.login(this.loginForm.controls.email.value, this.loginForm.controls.password.value);

  }


}
