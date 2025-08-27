import { Routes } from '@angular/router';
import { PatientServiceComponent } from './patient-service/patient-service'; // ✅ use component, not service
import { DoctorServiceComponent } from './doctor-service/doctor-service';
import { AppointmentList } from './appointment-service/appointment-list/appointment-list';
import { AppointmentBook } from './appointment-service/appointment-book/appointment-book';
import { AddBill } from './billing-service/add-bill/add-bill';
import { BillList } from './billing-service/bill-list/bill-list';
import { AddVisit } from './medical-record-service/add-visit/add-visit';
import { PatientRecords } from './medical-record-service/patient-records/patient-records';
import { UpdatePrescription } from './medical-record-service/update-prescription/update-prescription';

export const routes: Routes = [
  { path: 'patients', component: PatientServiceComponent },
   { path: 'doctors', component: DoctorServiceComponent },
   { path: 'appointments', component: AppointmentList },
  { path: 'book-appointment', component: AppointmentBook },
   { path: 'add-bill', component: AddBill },
   { path: 'bill-list', component: BillList },
   { path: 'add-visit', component: AddVisit},
   { path: 'patient-records', component: PatientRecords},
   { path: 'update-prescription', component: UpdatePrescription }
];
