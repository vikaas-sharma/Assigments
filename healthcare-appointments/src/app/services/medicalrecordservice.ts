import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

export interface MedicalRecord {
  id?: number;
  patientId: number;
  visitDate: string;
  doctorName: string;
  diagnosis: string;
  prescription?: string;
  testResults?: string;
}


@Injectable({
  providedIn: 'root'
})
export class Medicalrecordservice {
  
  private baseUrl = 'http://localhost:9090/api/medical-records';

  constructor(private http: HttpClient) {}

  addVisit(patientId: number, record: any): Observable<MedicalRecord> {
    return this.http.post<MedicalRecord>(`${this.baseUrl}/${patientId}/visits`, record);
  }

  updatePrescription(recordId: number, prescription: string): Observable<MedicalRecord> {
    return this.http.put<MedicalRecord>(`${this.baseUrl}/${recordId}/prescription`, { prescription });
  }

  getRecordsByPatient(patientId: number): Observable<MedicalRecord[]> {
    return this.http.get<MedicalRecord[]>(`${this.baseUrl}/${patientId}`);
  }

  getAll(): Observable<MedicalRecord[]> {
    return this.http.get<MedicalRecord[]>(this.baseUrl);
  }
}
