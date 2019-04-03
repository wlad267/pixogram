import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { User } from '../user.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { MessagingService } from '../../../core/messaging/messaging.service';

@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.scss']
})
export class MyAccountComponent implements OnInit {

  user: User;
  formGroup: FormGroup

  constructor(private formBuilder: FormBuilder,
    private cd: ChangeDetectorRef,
    private userService: UserService,
    private messagingService: MessagingService) { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    this.formGroup = this.formBuilder.group({
      id: [this.user.id, Validators.required],
      file: [null, Validators.required],
      firstName: [this.user.firstName, Validators.required],
      lastName: [this.user.lastName, Validators.required],
      fileName: ['', Validators.required]
  });
  }


  onFileChange(event) { ;
    console.log('file change' + event);
 
  if(event.target.files && event.target.files.length) {
    const file = event.target.files[0];
    const fileName =  event.target.files[0].name;

    // Upload form might become valid only with supported media types
    this.formGroup.patchValue({
        file: file,
        fileName: fileName,
      });

    // Need to run CD since file load runs outside of zone
    this.cd.markForCheck();      
    };
   
  }

 onSubmit(){
  console.log("submitting:" + JSON.stringify(this.formGroup.value));
  
  this.userService.update(this.formGroup.value).subscribe(
    response => {
      console.log('saved' + response);
      localStorage.setItem('currentUser', JSON.stringify(response));
      this.userService.userChangeSubject.next();
    },
    error => {
      this.messagingService.error(error.message);
    }
  );
 }

}
