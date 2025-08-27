import { Component } from '@angular/core';
import { Medicalrecordservice } from '../../services/medicalrecordservice';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-update-prescription',
  imports: [FormsModule, CommonModule],
  templateUrl: './update-prescription.html',
  styleUrl: './update-prescription.css'
})
export class UpdatePrescription {
recordId: number = 0;
  prescription: string = '';

  constructor(private service: Medicalrecordservice) {}

  update() {
    this.service.updatePrescription(this.recordId, this.prescription).subscribe({
      next: () => alert('Prescription Updated!'),
      error: err => console.error(err)
    });
  }
}
