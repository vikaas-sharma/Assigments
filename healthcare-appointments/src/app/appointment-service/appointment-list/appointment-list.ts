import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppointmentService, Appointment } from '../../services/appointmentservice';

@Component({
  selector: 'app-appointment-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './appointment-list.html',
  styleUrls: ['./appointment-list.css']

})
export class AppointmentList implements OnInit {
  appointments: Appointment[] = [];

  constructor(private service: AppointmentService) {}

  ngOnInit() {
    this.loadAppointments();
  }

  loadAppointments() {
    this.service.getAll().subscribe(data => this.appointments = data);
  }

  cancel(id: number) {
    this.service.cancel(id).subscribe(() => this.loadAppointments());
  }

  reschedule(id: number) {
    const newTime = prompt('Enter new time slot:');
    if (newTime) {
      this.service.reschedule(id, newTime).subscribe(() => this.loadAppointments());
    }
  }
}
