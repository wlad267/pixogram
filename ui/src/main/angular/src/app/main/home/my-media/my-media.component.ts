import { Component, OnInit } from '@angular/core';
import { MediaDetails } from '../upload-options.model';

@Component({
  selector: 'app-my-media',
  templateUrl: './my-media.component.html',
  styleUrls: ['./my-media.component.scss']
})
export class MyMediaComponent implements OnInit {

  constructor() { }

  images: any[];
  media: MediaDetails[] = [];
    
  ngOnInit() {
      this.images = [];
      for (let i=0; i<33; i++){
        this.media.push(new MediaDetails());
      }
  }

}
