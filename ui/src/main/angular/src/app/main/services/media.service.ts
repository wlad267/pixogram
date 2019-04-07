import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MediaUploadRequest } from '../home/upload-options.model';
import { Observable } from 'rxjs';

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
    formData.append('file', media.file,media.fileName);
    formData.append('type', media.type);
    formData.append('userId', media.userId);

    const HttpUploadOptions = {
      headers: new HttpHeaders({ "Accept": "application/json" })
    }

    return this.httpClient.post('api/media/upload', formData, HttpUploadOptions);
  }

  fetchGallery(userId): Observable<any> {
    return this.httpClient.get(`api/media/gallery/${userId}`);
  }

  deleteMedia(mediaId) {
    return this.httpClient.post(`api/media/delete/${mediaId}`, {});
  }

  likeMedia(mediaId): Observable<any>{
    return this.httpClient.post(`api/media/like/${mediaId}`, {});
  }

  dislikeMedia(mediaId): Observable<any>{
    return this.httpClient.post(`api/media/dislike/${mediaId}`, {});
  }

  addComment(commentV, mediaId): Observable<any>{
     return this.httpClient.post(`api/media/comment/${mediaId}`, {comment: commentV})
  }
}
