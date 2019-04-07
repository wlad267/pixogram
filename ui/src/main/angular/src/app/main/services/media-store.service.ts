import { Injectable } from '@angular/core';
import { MediaDetails } from '../home/upload-options.model';

@Injectable({
  providedIn: 'root'
})
export class MediaStoreService {

  public media: MediaDetails;
  
  constructor() { }

}
