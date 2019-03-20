import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { MessagingService } from './messaging.service';

@Component({
  selector: 'app-messaging',
  templateUrl: './messaging.component.html',
  styleUrls: ['./messaging.component.scss']
})
export class MessagingComponent implements OnInit, OnDestroy {
 private subscription: Subscription;  

  messages: Array<{severity: string, summary: string, detail: string}> = new Array();;

  constructor(private messagingService: MessagingService) { }

  ngOnInit() {
    this.subscription = this.messagingService
      .getMessage()
      .subscribe(message => {
        this.messages.push(message);  
      });
  }

  ngOnDestroy() {
        this.subscription.unsubscribe();
    }

}
