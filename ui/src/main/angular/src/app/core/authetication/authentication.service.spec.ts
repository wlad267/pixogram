import { TestBed, inject } from '@angular/core/testing';

import { AuthenticationService } from './authentication.service';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing'
import { User } from '../../main/mentorsearch/user.model';

describe('AuthenticationService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [RouterTestingModule, HttpClientTestingModule]
  }));

  it('should be created', () => {
    const service: AuthenticationService = TestBed.get(AuthenticationService);
    expect(service).toBeTruthy();
  });

  it('should should login', inject([HttpTestingController], (httpMock: HttpTestingController) => {
    const service: AuthenticationService = TestBed.get(AuthenticationService);
    
    service.login('admin@bluementors.com', 'password');

    // We set the expectations for the HttpClient mock
    const req = httpMock.expectOne(`api/auth/login`);
    expect(req.request.method).toEqual('POST');
    expect(req.request.body).toEqual({ email: 'admin@bluementors.com', password: 'password' });

    // Then we set the fake data to be returned by the mock
    let mockUser ={ data: { username: 'admin@bluementors.com', firstName: 'pep', lastName: 'giovani', mentor: false}}
    req.flush(mockUser);

    let user: User = JSON.parse(localStorage.getItem('currentUser'));

    expect(user).toEqual(mockUser);

  }));

  it('should should logout', inject([HttpTestingController], (httpMock: HttpTestingController) => {
    const service: AuthenticationService = TestBed.get(AuthenticationService);
    service.logout();

    // We set the expectations for the HttpClient mock
    const req = httpMock.expectOne(`api/auth/logout`);
    expect(req.request.method).toEqual('GET');

    // Then we set the fake data to be returned by the mock
    req.flush({ data: { username: 'pep@bluementors.com', firstName: 'pep', lastName: 'giovani', mentor: false}});
  }));

});
