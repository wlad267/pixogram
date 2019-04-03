import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './main/home/home.component';
import { LoginComponent } from './core/authetication/login/login.component';
import { RegisterComponent } from './core/authetication/register/register.component';
import { AuthGuard } from './core/authetication/auth.guard';
import { MyAccountComponent } from './main/home/my-account/my-account.component';
import { UploadMediaComponent } from './main/home/upload-media/upload-media.component';
import { FollowComponent } from './main/home/follow/follow.component';
import { MyMediaComponent } from './main/home/my-media/my-media.component';
import { MediaDetailsComponent } from './main/home/media-details/media-details.component';

const appRoutes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard],
  children: [
    { path: 'uploadMedia', component: UploadMediaComponent },
    { path: 'myMedia', component: MyMediaComponent },
    { path: 'follow', component: FollowComponent },
    { path: 'myAccount', component: MyAccountComponent },
    { path: 'mediaDetails', component: MediaDetailsComponent },
    
    { path: '**', redirectTo: 'myMedia' }
    ] 
  },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  { path: '**', redirectTo: 'home' }
];

export const routing = RouterModule.forRoot(appRoutes);

@NgModule({
  imports: [RouterModule.forRoot(appRoutes, { enableTracing: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
