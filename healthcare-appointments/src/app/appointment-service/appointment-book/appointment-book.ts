import { Component } from '@angular/core';

import { AppointmentService} from '../../services/appointmentservice';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-appointment-book',
  imports: [CommonModule, FormsModule],
  templateUrl: './appointment-book.html',
  styleUrl: './appointment-book.css'
})
export class AppointmentBook {
patientId!: number;
  doctorId!: number;
  timeSlot!: string;
  message: string = '';

  constructor(private service: AppointmentService) {}

  book() {
    this.service.book(this.patientId, this.doctorId, this.timeSlot).subscribe({
      next: () => this.message = 'Appointment booked successfully!',
      error: () => this.message = 'Failed to book appointment'
    });
  }
}
