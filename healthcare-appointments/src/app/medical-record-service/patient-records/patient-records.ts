import { Component } from '@angular/core';
import { MedicalRecord, Medicalrecordservice } from '../../services/medicalrecordservice';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-patient-records',
  imports: [FormsModule, CommonModule],
  templateUrl: './patient-records.html',
  styleUrl: './patient-records.css'
})
export class PatientRecords {
patientId: number = 0;
  records: MedicalRecord[] = [];

  constructor(private service: Medicalrecordservice) {}

  loadRecords() {
    this.service.getRecordsByPatient(this.patientId).subscribe(res => this.records = res);
  }
}
