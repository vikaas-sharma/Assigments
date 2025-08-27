import { Component } from '@angular/core';
import { Bill, BillService } from '../../services/billservice';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-bill',
  imports: [FormsModule, CommonModule],
  templateUrl: './add-bill.html',
  styleUrl: './add-bill.css'
})
export class AddBill {

  bill: Bill = {
    patientId: 0,
    doctorId: 0,
    appointmentId: 0,
    amount: 0,
    status: 'PENDING',
    paymentMethod: '',
    insuranceProvider: '',
    insuranceClaimStatus: ''
  };

  constructor(private billService: BillService, private router: Router) {}

  save() {
    this.billService.create(this.bill).subscribe(() => {
      this.router.navigate(['/bills']);
    });
  }
}
