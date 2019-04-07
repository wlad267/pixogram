import { Component, OnInit } from '@angular/core';
import { User } from '../user.model';
import { UserService } from '../../services/user.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-follow',
  templateUrl: './follow.component.html',
  styleUrls: ['./follow.component.scss']
})
export class FollowComponent implements OnInit {

  users: User[];
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.fetchAllUsers();    
  }

  changeTo(view){
    switch (view) {
      case 'all':
        console.log('get all users');
        this.fetchAllUsers();
      break;
      case 'followers':
      console.log('get followers');
        this.fetchFollow();
      break;
      case 'following':
        this.fetchFollowing();
        console.log('get following');
      break;
    }
  }


  private fetchAllUsers(){
    this.userService.getAllUsers().subscribe(
      users => this.users = users,
      console.error
    );
  }

  private fetchFollow(){
    this.userService.getFollowers().subscribe(
      users => this.users = users,
      console.error
    );
  }

  private fetchFollowing(){
    this.userService.getFollowing().subscribe(
      users => this.users = users,
      console.error
    );
  }
}
