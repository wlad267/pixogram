import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MediaDetails } from '../upload-options.model';
import { MediaStoreService } from '../../services/media-store.service';
import { MediaService } from '../../services/media.service';

@Component({
  selector: 'app-media-details',
  templateUrl: './media-details.component.html',
  styleUrls: ['./media-details.component.scss']
})
export class MediaDetailsComponent implements OnInit {

  media: MediaDetails;
  commnet: string;
  constructor(private mediaStore: MediaStoreService, private mediaService: MediaService) { }

  ngOnInit() {
    this.media = this.mediaStore.media;
  }

  like(){
    console.log('like');
    this.mediaService.likeMedia(this.media.id)
        .subscribe(media=> {this.media = media;}, console.error);
  }

  dislike(){
    console.log('like');
    this.mediaService.dislikeMedia(this.media.id)
        .subscribe(media=> {this.media = media;}, console.error);
  }

  doChange(event){
    this.commnet = event.target.value;
  }

  addComment(){
    this.mediaService.addComment(this.commnet, this.media.id)
        .subscribe(media=> {this.media = media;
        }, console.error);
  }

}
