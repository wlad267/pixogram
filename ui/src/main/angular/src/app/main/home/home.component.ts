import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../core/authetication/authentication.service';
import {MenuItem} from 'primeng/api';
import { User } from './user.model';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  /**
   * controls the side menu 
   */
  displayMenu = false;
  /**
   * TabMenu items
   */
  tabMenuItems: MenuItem[];
  activTabMenuItem: MenuItem;
  showTabMenu = true;

  private user: User
  
  /**
   * Handlers for the tab menu
   */
  onSkills: any;
  onMentors: any;
  onUsers: any;
  onTrainings: any;
  onStatistics: any;
  

  constructor(private authenticationSevice: AuthenticationService,
             private router:Router, 
             private route: ActivatedRoute) { 

              this.onSkills = function(event) {
                router.navigate(['/home/skills'] );
              };

              this.onMentors = function(event) {
                router.navigate(['/home/mentors'] );
              };

              this.onUsers = function(event) {
                router.navigate(['/home/users'] );
              };

              this.onTrainings = function(event) {
                router.navigate(['/home/trainings'] );
              };

              this.onStatistics = function(event) {
                router.navigate(['/home/statistics'] );
              };

             }

  ngOnInit() {
    
    this.user = JSON.parse(localStorage.getItem('currentUser'));

    console.log('current user ' + JSON.stringify(this.user));
    

  }

  logout(){
    this.authenticationSevice.logout();
  }
 
  takeAction(action: string){
    console.log('tacking action ' + action);
    
    this.displayMenu = false;   
    this.showTabMenu = true;
    
    switch (action){
      case 'skills':
         this.activTabMenuItem = this.tabMenuItems[0];
         this.router.navigate([action], {relativeTo: this.route});
      break;
      case 'trainings':
        this.activTabMenuItem = this.tabMenuItems[3]
        this.router.navigate([action], {relativeTo: this.route});
      break;
      case 'donate':
        this.router.navigate([action], {relativeTo: this.route});
        this.showTabMenu = false;
      break;
      default:
        this.activTabMenuItem = this.tabMenuItems[0];
      break;  
    }
  }

  

}
