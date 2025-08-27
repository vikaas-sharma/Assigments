// src/app/services/doctorservice.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Doctor {
  id?: number;
  firstName: string;
  lastName: string;
  specialization: string;
  email: string;
  phone: string;
  schedule: string;
}

export interface DoctorSchedule {
  id?: number;
  doctorId: number;
  timeSlot: string;
  booked: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class DoctorService {
  private baseUrl = 'http://localhost:9090/api/doctors';

  constructor(private http: HttpClient) {}

  getAllDoctors(): Observable<Doctor[]> {
    return this.http.get<Doctor[]>(this.baseUrl);
  }

  getDoctor(id: number): Observable<Doctor> {
    return this.http.get<Doctor>(`${this.baseUrl}/${id}`);
  }

  getDoctorsBySpecialization(specialization: string): Observable<Doctor[]> {
    return this.http.get<Doctor[]>(`${this.baseUrl}/specialization/${specialization}`);
  }

  createDoctor(doctor: Doctor): Observable<Doctor> {
    return this.http.post<Doctor>(this.baseUrl, doctor);
  }

  updateDoctor(id: number, doctor: Doctor): Observable<Doctor> {
    return this.http.put<Doctor>(`${this.baseUrl}/${id}`, doctor);
  }

  deleteDoctor(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  getDoctorSchedule(doctorId: number): Observable<DoctorSchedule[]> {
    return this.http.get<DoctorSchedule[]>(`${this.baseUrl}/${doctorId}/schedule`);
  }

  blockDoctorSlot(doctorId: number, time: string): Observable<boolean> {
    return this.http.post<boolean>(`${this.baseUrl}/${doctorId}/block?time=${time}`, {});
  }
}
