// src/app/patient-service/patient-service.component.ts
import { Component, OnInit } from '@angular/core';
import { Patient, PatientService } from '../services/patientservice';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-patient-service',
  imports:[CommonModule, FormsModule, RouterLink],
  templateUrl: './patient-service.html',
  styleUrls: ['./patient-service.css']
})
export class PatientServiceComponent implements OnInit {

  patients: Patient[] = [];
  patient: Patient = {
    firstName: '',
    lastName: '',
    dob: '',
    gender: '',
    insuranceProvider: '',
    insuranceNumber: '',
    phone: '',
    email: '',
    address: ''
  };

  editing: boolean = false;

  constructor(private patientService: PatientService) {}

  ngOnInit(): void {
    this.loadPatients();
  }

  loadPatients(): void {
    this.patientService.getAllPatients().subscribe(data => {
      this.patients = data;
    });
  }

  savePatient(): void {
    if (this.editing && this.patient.id) {
      this.patientService.updatePatient(this.patient.id, this.patient).subscribe(() => {
        this.loadPatients();
        this.resetForm();
      });
    } else {
      this.patientService.createPatient(this.patient).subscribe(() => {
        this.loadPatients();
        this.resetForm();
      });
    }
  }

  editPatient(patient: Patient): void {
    this.patient = { ...patient };
    this.editing = true;
  }

  deletePatient(id: number | undefined): void {
    if (id) {
      this.patientService.deletePatient(id).subscribe(() => {
        this.loadPatients();
      });
    }
  }

  resetForm(): void {
    this.patient = {
      firstName: '',
      lastName: '',
      dob: '',
      gender: '',
      insuranceProvider: '',
      insuranceNumber: '',
      phone: '',
      email: '',
      address: ''
    };
    this.editing = false;
  }
}
