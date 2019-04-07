import { Injectable } from '@angular/core';
import { User, UserUpdate } from '../home/user.model';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  userChangeSubject = new Subject();

  constructor(private httpClient: HttpClient) { }

  register(user: User): Observable<any> {
    return this.httpClient.post('api/registration', user);
  }
  
  update(userUpdate: UserUpdate){

    let formData = new FormData();(
    formData.append('userId', userUpdate.id.toString()));
    formData.append('firstName', userUpdate.firstName);
    formData.append('lastName', userUpdate.lastName);
    formData.append('file', userUpdate.file, userUpdate.fileName);


    const HttpUploadOptions = {
      headers: new HttpHeaders({ "Accept": "application/json" })
    }

    return this.httpClient.post('api/users/update', formData, HttpUploadOptions);

  }


  getAllUsers():Observable<any> {
    return this.httpClient.get('api/users/all');
  }

  follow(userId){
    return this.httpClient.post(`api/users/follow/${userId}`, {});
  }
  getFollowers():Observable<any> {
    return this.httpClient.get('api/users/follow');
  }

  getFollowing():Observable<any> {
    return this.httpClient.get('api/users/following');
  }

}
