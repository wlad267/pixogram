import { TestBed } from '@angular/core/testing';

import { MediaStoreService } from './media-store.service';

describe('MediaStoreService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MediaStoreService = TestBed.get(MediaStoreService);
    expect(service).toBeTruthy();
  });
});
