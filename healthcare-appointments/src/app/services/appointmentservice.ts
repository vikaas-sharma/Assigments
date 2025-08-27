import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Appointment {
  id?: number;
  patientId: number;
  doctorId: number;
  timeSlot: string;
  status?: string;
}

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  private baseUrl = 'http://localhost:9090/api/appointments';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(this.baseUrl);
  }

  getById(id: number): Observable<Appointment> {
    return this.http.get<Appointment>(`${this.baseUrl}/${id}`);
  }

  book(patientId: number, doctorId: number, timeSlot: string): Observable<Appointment> {
    return this.http.post<Appointment>(
      `${this.baseUrl}/book?patientId=${patientId}&doctorId=${doctorId}&timeSlot=${timeSlot}`,
      {}
    );
  }

  reschedule(id: number, newTimeSlot: string): Observable<Appointment> {
    return this.http.put<Appointment>(
      `${this.baseUrl}/reschedule/${id}?newTimeSlot=${newTimeSlot}`, {}
    );
  }

  cancel(id: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/cancel/${id}`, {}, { responseType: 'text' });
  }
}
