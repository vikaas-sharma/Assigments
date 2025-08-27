// src/app/doctor-service/doctor-service.component.ts
import { Component, OnInit } from '@angular/core';
import { Doctor, DoctorService, DoctorSchedule } from '../services/doctorservice';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-doctor-service',
  imports: [FormsModule, CommonModule],
  templateUrl: './doctor-service.html',
  styleUrls: ['./doctor-service.css']
})
export class DoctorServiceComponent implements OnInit {
  doctors: Doctor[] = [];
  doctor: Doctor = {
    firstName: '',
    lastName: '',
    specialization: '',
    email: '',
    phone: '',
    schedule: ''
  };

  schedules: DoctorSchedule[] = [];
  editing = false;

  constructor(private doctorService: DoctorService) {}

  ngOnInit(): void {
    this.loadDoctors();
  }

  loadDoctors(): void {
    this.doctorService.getAllDoctors().subscribe(data => {
      this.doctors = data;
    });
  }

  saveDoctor(): void {
    if (this.editing && this.doctor.id) {
      this.doctorService.updateDoctor(this.doctor.id, this.doctor).subscribe(() => {
        this.loadDoctors();
        this.resetForm();
      });
    } else {
      this.doctorService.createDoctor(this.doctor).subscribe(() => {
        this.loadDoctors();
        this.resetForm();
      });
    }
  }

  editDoctor(doc: Doctor): void {
    this.doctor = { ...doc };
    this.editing = true;
  }

  deleteDoctor(id?: number): void {
    if (id) {
      this.doctorService.deleteDoctor(id).subscribe(() => {
        this.loadDoctors();
      });
    }
  }

  resetForm(): void {
    this.doctor = {
      firstName: '',
      lastName: '',
      specialization: '',
      email: '',
      phone: '',
      schedule: ''
    };
    this.editing = false;
  }

  viewSchedule(doctorId: number): void {
    this.doctorService.getDoctorSchedule(doctorId).subscribe(data => {
      this.schedules = data;
    });
  }

  blockSlot(doctorId: number, time: string): void {
    this.doctorService.blockDoctorSlot(doctorId, time).subscribe(success => {
      if (success) {
        alert(`Slot ${time} blocked successfully!`);
        this.viewSchedule(doctorId);
      } else {
        alert('Slot already booked or not available');
      }
    });
  }
}
