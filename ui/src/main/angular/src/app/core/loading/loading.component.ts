import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription, Subject } from 'rxjs';
import { LoadingService } from './loading.service';

@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.scss']
})
export class LoadingComponent implements OnInit, OnDestroy {
 
  blocked = false;

  private subscription: Subscription;
  constructor(private loadingService: LoadingService) { }

  ngOnInit() {
    this.subscription = this.loadingService.getLoader().subscribe((block: boolean) => this.blocked = block);
  }
  
   ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
