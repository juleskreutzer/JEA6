import { TestBed, inject } from '@angular/core/testing';

import { NewKweetService } from './new-kweet.service';

describe('NewKweetService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [NewKweetService]
    });
  });

  it('should be created', inject([NewKweetService], (service: NewKweetService) => {
    expect(service).toBeTruthy();
  }));
});
