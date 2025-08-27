import { TestBed } from '@angular/core/testing';

import { DoctorService } from './doctorservice';

describe('Doctorservice', () => {
  let service:DoctorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DoctorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
