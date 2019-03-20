import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class MessagingService {

    private subject = new Subject<{severity: string, summary: string, detail: string}>();

    constructor() { }

    success(message: string, details ='', keepAfterNavigationChange = false) {
        this.subject.next({ severity: 'success', summary: message, detail: details });
    }

    error(message: string,  details ='', keepAfterNavigationChange = false) {
        this.subject.next({ severity: 'error', summary: message, detail: details});
    }

    getMessage(): Observable<any> {
        return this.subject.asObservable();
    }
}
