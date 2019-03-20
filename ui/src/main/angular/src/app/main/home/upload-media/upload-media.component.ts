import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { MediaUploadRequest } from '../upload-options.model';
import { Validators, FormBuilder } from '@angular/forms';
import { MediaService } from '../../services/media.service';
import { MessagingService } from '../../../core/messaging/messaging.service';


@Component({
  selector: 'app-upload-media',
  templateUrl: './upload-media.component.html',
  styleUrls: ['./upload-media.component.scss']
})
export class UploadMediaComponent implements OnInit {

  uploadOptions: MediaUploadRequest;

  uploadedFiles: any[] = [];

  formGroup = this.formBuilder.group({
    file: [null, Validators.required],
    fileName: [''],
    title: ['', Validators.required],
    description: [''],
    type: []
});

  constructor(private formBuilder: FormBuilder,  
    private cd: ChangeDetectorRef,
    private mediaService: MediaService,
    private messagingService: MessagingService) { }

  ngOnInit() {
    this.uploadOptions = new MediaUploadRequest();
  }

  onFileChange(event) { ;
    console.log('file change' + event);
 
  if(event.target.files && event.target.files.length) {
    const file = event.target.files[0];
    const fileName =  event.target.files[0].name;

    console.log('is Video ' + this.isVideo(fileName));
    console.log('is Image ' + this.isImage(fileName));

    let fileType = null;
    if (this.isImage(fileName)) {
      fileType = 'image';
    } else if (this.isVideo(fileName)){
      fileType = 'video';
    } else{
      this.messagingService.error('Selected file type is not suported (Read the user manul - Pixogram supports only to img/png/bmp/jpg avi/mp4/m4v/mpg).')
      return;
    }

    // Upload form might become valid only with supported media types
    this.formGroup.patchValue({
        file: file,
        fileName: fileName,
        type: fileType
      });

    // Need to run CD since file load runs outside of zone
    this.cd.markForCheck();
      
    };
   
  }

 onSubmit(){
  console.log("submitting:" + JSON.stringify(this.formGroup.value));
  
  this.mediaService.uploadMedia(this.formGroup.value).subscribe(
    response => {
      console.log('saved' + response);
    },
    error => {
      this.messagingService.error(error.message);
    }
  );
 }

 private getExtension(filename) {
  var parts = filename.split('.');
  return parts[parts.length - 1];
 }

private isImage(filename) {
  var ext = this.getExtension(filename);
  switch (ext.toLowerCase()) {
  case 'jpg':
  case 'gif':
  case 'bmp':
  case 'png':
      //etc
      return true;
  }
  return false;
}

private isVideo(filename) {
  var ext = this.getExtension(filename);
  switch (ext.toLowerCase()) {
  case 'm4v':
  case 'avi':
  case 'mpg':
  case 'mp4':
      // etc
      return true;
  }
  return false;
  }
}
