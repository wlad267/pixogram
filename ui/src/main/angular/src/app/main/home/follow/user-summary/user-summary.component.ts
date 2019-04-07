import { Component, OnInit, Input } from '@angular/core';
import { User } from '../../user.model';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from '../../../services/user.service';

@Component({
  selector: 'app-user-summary',
  templateUrl: './user-summary.component.html',
  styleUrls: ['./user-summary.component.scss']
})
export class UserSummaryComponent implements OnInit {

  @Input() user: User;
  constructor(private router: Router, private activatedRoute: ActivatedRoute, private userService: UserService) { }

  ngOnInit() {
    console.log('input user ' + this.user);
  }

  onUserSelect(){
    console.log('navigate user galery ' + this.user.id);
    this.router.navigate(['myMedia', this.user.id], {relativeTo: this.activatedRoute.parent});
  }

  follow(){
    console.log('follow user ' + JSON.stringify(this.user))
    this.userService.follow(this.user.id).subscribe(()=>{},
        console.error
    )
  }

}
