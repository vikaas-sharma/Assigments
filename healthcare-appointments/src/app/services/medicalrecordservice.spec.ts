import { TestBed } from '@angular/core/testing';

import { Medicalrecordservice } from './medicalrecordservice';

describe('Medicalrecordservice', () => {
  let service: Medicalrecordservice;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Medicalrecordservice);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
