import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { UserService } from 'src/app/main/services/user.service';
import { MessagingService } from '../../messaging/messaging.service';
import { LoadingService} from '../../loading/loading.service';

@Component({templateUrl: 'register.component.html'})
export class RegisterComponent implements OnInit {
    registerForm: FormGroup;
    submitted = false;

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private userService: UserService,
        private messagingService: MessagingService,
        private loadingService: LoadingService) { }

    ngOnInit() {
        this.registerForm = this.formBuilder.group({
            firstName: ['', Validators.required],
            lastName: ['', Validators.required],
            email: ['wlad267@gmail.com', [Validators.required, Validators.email]],
            password: ['', [Validators.required, Validators.minLength(6)]]
        });
    }

    // convenience getter for easy access to form fields
    get f() { return this.registerForm.controls; }

    onSubmit() {
      console.log(this.registerForm);
        this.submitted = true;

        // stop here if form is invalid
        if (this.registerForm.invalid) {
            return;
        }

        this.loadingService.block();  
        this.userService.register(this.registerForm.value)
             .pipe(first())
             .subscribe(
                 data => {
                     this.messagingService.success('Registration successful. Please check your email for confirmation.');
                     this.loadingService.unblock();
                     this.router.navigate(['/login']);
                 },
                 error => {
                     console.error(error);
                     this.loadingService.unblock();
                     this.messagingService.error(error.message);
                 });
     }
}