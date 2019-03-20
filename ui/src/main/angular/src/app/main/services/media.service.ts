import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MediaUploadRequest } from '../home/upload-options.model';

@Injectable({
  providedIn: 'root'
})
export class MediaService {

  constructor(private httpClient: HttpClient) { 
  }

  public uploadMedia(media: MediaUploadRequest){
    let formData = new FormData();
    formData.append('title', media.title);
    formData.append('description', media.description);
    formData.append('file', media.file,'xxxxxx');
    formData.append('type', media.type)

    const HttpUploadOptions = {
      headers: new HttpHeaders({ "Accept": "application/json" })
    }

    return this.httpClient.post('api/media/upload', formData, HttpUploadOptions);
  }
}
