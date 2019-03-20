import { async, ComponentFixture, TestBed, inject } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { FormBuilder, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { DebugElement } from '@angular/core';
import { By } from '@angular/platform-browser';



describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      imports: [ReactiveFormsModule, FormsModule,  RouterTestingModule, HttpClientTestingModule],
      providers: [
        //provide some mocks
        // {provide: UserService, useClass: MockUser}
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('shoudl have a email field', () => {
    expect(component).toBeTruthy();
    let loginEl: DebugElement;
    loginEl = fixture.debugElement.query(By.css('input[type=email]'));
    expect(component).toBeTruthy();
  });

  it('login form shoudl be invalid withoud password', () => {
    expect(component).toBeTruthy();
    //Angular test won't call this one - so i have to call it manually
    component.ngOnInit();

    expect(component.loginForm.valid).toBeFalsy();
  });

  it('login form shoudl invalid with email defaul email and 4 letters password', () => {
    expect(component).toBeTruthy();
    component.ngOnInit();

    component.loginForm.controls['password'].setValue('1234');

    expect(component.loginForm.valid).toBeFalsy();
  });

  it('login form shoudl valid with email defaul email and 6 letters password', () => {
    expect(component).toBeTruthy();
    component.ngOnInit();

    component.loginForm.controls['password'].setValue('123456');

    expect(component.loginForm.valid).toBeTruthy();
  });

  it('login form shoudl invalid with invalid email', () => {
    component.ngOnInit();

    component.loginForm.controls['email'].setValue('123456');

    expect(component.loginForm.valid).toBeFalsy();
  });

  it('login form shoudl valid with email defaul email and 6 letters password', () => {
    component.ngOnInit();

    component.loginForm.controls['email'].setValue('admin@bluementors.com');
    component.loginForm.controls['password'].setValue('123456');

    expect(component.loginForm.valid).toBeTruthy();
  });

  it('login form shoudl submit login', inject([HttpTestingController], (httpMock: HttpTestingController) => {
    component.ngOnInit();

    component.loginForm.controls['email'].setValue('admin@bluementors.com');
    component.loginForm.controls['password'].setValue('123456');

    let button = fixture.debugElement.nativeElement.querySelector('button');
    button.click();

    // We set the expectations for the HttpClient mock
    const req = httpMock.expectOne(`api/auth/login`);
    expect(req.request.method).toEqual('POST');
    expect(req.request.body).toEqual({ email: 'admin@bluementors.com', password: '123456' });

    expect(component.loginForm.valid).toBeTruthy();
  }));

});
