import { Component, OnInit } from '@angular/core';
import { MediaDetails } from '../upload-options.model';
import { ActivatedRoute } from '@angular/router';
import { MediaService } from '../../services/media.service';
import { User } from '../user.model';

@Component({
  selector: 'app-my-media',
  templateUrl: './my-media.component.html',
  styleUrls: ['./my-media.component.scss']
})
export class MyMediaComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private mediaService: MediaService) { 
  }

  images: any[];
  media: MediaDetails[] = [];
  userId: number;
  loggedUser: User;

  ngOnInit() {

    this.activatedRoute.params.subscribe(params =>{
      console.log('userId parameter ' + params);
      this.userId = params.userId;
      this.fetchGalery(params.userId);
    });

    this.loggedUser = JSON.parse(localStorage.getItem('currentUser'));  
  }

  deleteMedia(media){
    this.media.splice(this.media.indexOf(media), 1);
  }

  private fetchGalery(userId){
    this.mediaService.fetchGallery(userId).subscribe(
      gallery=> {
        this.media = gallery;
      },
      console.error
    );
  }


}
