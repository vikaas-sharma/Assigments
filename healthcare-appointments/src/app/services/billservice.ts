import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Bill {
  id?: number;
  patientId: number;
  doctorId: number;
  appointmentId: number;
  amount: number;
  status: string;
  paymentMethod: string;
  insuranceProvider: string;
  insuranceClaimStatus: string;
  createdAt?: string;
}

@Injectable({
  providedIn: 'root'
})
export class BillService {
  private apiUrl = 'http://localhost:9090/api/bills';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Bill[]> {
    return this.http.get<Bill[]>(this.apiUrl);
  }

  create(bill: Bill): Observable<Bill> {
    return this.http.post<Bill>(this.apiUrl, bill);
  }

  pay(id: number, method: string): Observable<Bill> {
    return this.http.put<Bill>(`${this.apiUrl}/${id}/pay?method=${method}`, {});
  }

  updateInsurance(id: number, status: string): Observable<Bill> {
    return this.http.put<Bill>(`${this.apiUrl}/${id}/insurance?status=${status}`, {});
  }
}
