import { Component, OnInit } from '@angular/core';
import { User } from '../user.model';

@Component({
  selector: 'app-follow',
  templateUrl: './follow.component.html',
  styleUrls: ['./follow.component.scss']
})
export class FollowComponent implements OnInit {

  users: User[];
  constructor() { }

  ngOnInit() {
    this.users = [];
    for (let i=0; i<33; i++){
      const  u = new User();
      u.firstName = 'Gigi ' + i;
      u.id =i;
      this.users.push(u);
    }
  }

}
