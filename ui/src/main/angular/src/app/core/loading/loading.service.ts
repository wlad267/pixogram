import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoadingService {

  private subject = new Subject<boolean>();

  constructor() { }

  block(){
    this.subject.next(true);
  }

  unblock(){
    this.subject.next(false);
  }

  getLoader(): Observable<any> {
        return this.subject.asObservable();
    }
}
