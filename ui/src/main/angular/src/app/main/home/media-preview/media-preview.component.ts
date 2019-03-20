import { Component, OnInit, Input } from '@angular/core';
import { MediaDetails } from '../upload-options.model';

@Component({
  selector: 'app-media-preview',
  templateUrl: './media-preview.component.html',
  styleUrls: ['./media-preview.component.scss']
})
export class MediaPreviewComponent implements OnInit {

  @Input() media: MediaDetails;
  
  constructor() { }

  ngOnInit() {
  }

}
