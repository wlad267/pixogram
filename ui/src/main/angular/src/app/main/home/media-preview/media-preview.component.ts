import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { MediaDetails } from '../upload-options.model';
import { Router, ActivatedRoute } from '@angular/router';
import { MediaStoreService } from '../../services/media-store.service';
import { MediaService } from '../../services/media.service';


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
  @Output() deletion: EventEmitter<any> = new EventEmitter<any>();

  @Input("isSelf") set selfViewSetter(value: string){
    this._isSelfEditiong = Boolean(value);
  } 
  _isSelfEditiong = false;

  media: MediaDetails;
  constructor(private router: Router, 
    private activatedRoute: ActivatedRoute,
    private mediaStore: MediaStoreService,
    private mediaService: MediaService ) { }

  ngOnInit() {
  }

  showMediaDetails(){
    console.log('show deails for ' + JSON.stringify(this.media));
    this.mediaStore.media = this.media;
    this.router.navigate(['mediaDetails'], { relativeTo: this.activatedRoute.parent });
  }

  delete(){
    console.log('delete ' + JSON.stringify(this.media));
    this.mediaService.deleteMedia(this.media.id).subscribe(
    ()=> {
      console.log(' media[' + this.media.id+' was deleted');
      this.deletion.emit({});    
    },
    console.error
    )
  }  

}
