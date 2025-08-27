import { Component, OnInit } from '@angular/core';
import { Bill, BillService } from '../../services/billservice';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-bill-list',
  imports: [CommonModule],
  templateUrl: './bill-list.html',
  styleUrl: './bill-list.css'
})
export class BillList implements OnInit{
bills: Bill[] = [];

  constructor(private billService: BillService) {}

  ngOnInit(): void {
    this.loadBills();
  }

  loadBills() {
    this.billService.getAll().subscribe(data => this.bills = data);
  }

  payBill(id: number, method: string) {
    this.billService.pay(id, method).subscribe(() => this.loadBills());
  }

  updateInsurance(id: number, status: string) {
    this.billService.updateInsurance(id, status).subscribe(() => this.loadBills());
  }
  
}
