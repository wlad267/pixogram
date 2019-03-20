import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { UserService } from './services/user.service';
import { RouterModule } from '@angular/router';
import { TabMenuModule } from 'primeng/tabmenu';
import { TableModule } from 'primeng/table';
import { InputSwitchModule } from 'primeng/inputswitch';



import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DialogModule } from 'primeng/dialog';
import { TieredMenuModule } from 'primeng/tieredmenu';
import { SidebarModule } from 'primeng/sidebar';
import { DynamicDialogModule } from 'primeng/dynamicdialog';
import { EditorModule } from 'primeng/editor';
import { CheckboxModule } from 'primeng/checkbox';
import { RadioButtonModule } from 'primeng/radiobutton';
import { PaginatorModule } from 'primeng/paginator';
import { FullCalendarModule } from 'primeng/fullcalendar';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { AccordionModule } from 'primeng/accordion';
import { RatingModule } from 'primeng/rating';
import { StepsModule } from 'primeng/steps';
import { UploadMediaComponent } from './home/upload-media/upload-media.component';
import { MyMediaComponent } from './home/my-media/my-media.component';
import { FollowComponent } from './home/follow/follow.component';
import { MyAccountComponent } from './home/my-account/my-account.component';
import { FileUploadModule } from 'primeng/fileupload';
import { GalleriaModule } from 'primeng/galleria';
import { MediaService } from './services/media.service';
import { MediaPreviewComponent } from './home/media-preview/media-preview.component';
import { MediaDetailsComponent } from './home/media-details/media-details.component';

@NgModule({
  declarations: [HomeComponent, UploadMediaComponent, MyMediaComponent, FollowComponent, MyAccountComponent, MediaPreviewComponent, MediaDetailsComponent],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    TableModule,
    FileUploadModule,
    InputSwitchModule,
    FormsModule,
    DialogModule,
    TieredMenuModule,
    SidebarModule,
    DynamicDialogModule,
    EditorModule,
    CheckboxModule,
    RadioButtonModule,
    PaginatorModule,
    FullCalendarModule,
    ScrollPanelModule,
    AccordionModule,
    RatingModule,
    StepsModule,
    GalleriaModule
  ],
  exports: [HomeComponent, RouterModule],
  providers: [UserService, MediaService]
})
export class MainModule { }
