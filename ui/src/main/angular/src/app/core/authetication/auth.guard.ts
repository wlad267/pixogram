import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const user = localStorage.getItem('currentUser');
        if (user) {
           console.log('found logged user:' + user);
            return true;
        }

        // not logged in so redirect to login page with the return url
        console.log('no logged user found -> navigating to login');
        //this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }});
        this.router.navigate(['/login']);
        return false;
    }
}