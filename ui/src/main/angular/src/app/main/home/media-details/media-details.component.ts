import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MediaDetails } from '../upload-options.model';
import { MediaStoreService } from '../../services/media-store.service';

@Component({
  selector: 'app-media-details',
  templateUrl: './media-details.component.html',
  styleUrls: ['./media-details.component.scss']
})
export class MediaDetailsComponent implements OnInit {

  media: MediaDetails;
  constructor(private mediaStore: MediaStoreService) { }

  ngOnInit() {
    this.media = this.mediaStore.media;
  }

}
