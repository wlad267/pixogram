import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MessagingComponent } from './messaging.component';
import { MessageModule } from 'primeng/message';
import { MessagesModule } from 'primeng/messages';
import { TabMenuModule } from 'primeng/tabmenu';

describe('MessagingComponent', () => {
  let component: MessagingComponent;
  let fixture: ComponentFixture<MessagingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MessagingComponent ],
      //imports: [ReactiveFormsModule, FormsModule,  RouterTestingModule, HttpClientTestingModule],
      imports: [MessageModule, MessagesModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MessagingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
