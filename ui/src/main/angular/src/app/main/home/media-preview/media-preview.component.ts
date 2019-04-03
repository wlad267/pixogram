import { Component, OnInit, Input } from '@angular/core';
import { MediaDetails } from '../upload-options.model';
import { Router, ActivatedRoute } from '@angular/router';
import { MediaStoreService } from '../../services/media-store.service';

@Component({
  selector: 'app-media-preview',
  templateUrl: './media-preview.component.html',
  styleUrls: ['./media-preview.component.scss']
})
export class MediaPreviewComponent implements OnInit {

  @Input('media') set mediaSeeter(value: MediaDetails){
    console.log('title ' + value.title);
    this.media= value;
  }
  media: MediaDetails;
  constructor(private router: Router, 
    private activatedRoute: ActivatedRoute,
    private mediaStore: MediaStoreService) { }

  ngOnInit() {
  }

  showMediaDetails(){
    console.log('show deails for ' + JSON.stringify(this.media));
    this.mediaStore.media = this.media;
    this.router.navigate(['mediaDetails'], { relativeTo: this.activatedRoute.parent });
  }

}
