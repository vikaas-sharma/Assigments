import { Component } from '@angular/core';
import { MedicalRecord, Medicalrecordservice} from '../../services/medicalrecordservice';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-visit',
  imports: [FormsModule, CommonModule],
  templateUrl: './add-visit.html',
  styleUrl: './add-visit.css'
})
export class AddVisit {
record: Partial<MedicalRecord> = {
    patientId: 0,
    visitDate: '',
    doctorName: '',
    diagnosis: ''
  };

  constructor(private service: Medicalrecordservice) {}

  save() {
    if (this.record.patientId) {
      this.service.addVisit(this.record.patientId, this.record).subscribe({
        next: res => {
          alert('Visit Added Successfully!');
          this.record = { patientId: 0, visitDate: '', doctorName: '', diagnosis: '' };
        },
        error: err => console.error(err)
      });
    }
  }
}
