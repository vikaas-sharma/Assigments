package com.example.billing_service.service;

import com.example.billing_service.model.Bill;
import com.example.billing_service.repository.BillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingService {

    private final BillRepository repository;

    public BillingService(BillRepository repository) {
        this.repository = repository;
    }

    public Bill generateBill(Bill bill) {
        bill.setStatus("PENDING");
        return repository.save(bill);
    }

    public Bill markAsPaid(Long billId, String paymentMethod) {
        Bill bill = repository.findById(billId).orElseThrow(() -> new RuntimeException("Bill not found"));
        bill.setStatus("PAID");
        bill.setPaymentMethod(paymentMethod);
        return repository.save(bill);
    }

    public Bill updateInsuranceStatus(Long billId, String claimStatus) {
        Bill bill = repository.findById(billId).orElseThrow(() -> new RuntimeException("Bill not found"));
        bill.setInsuranceClaimStatus(claimStatus);
        return repository.save(bill);
    }

    public List<Bill> getAllBills() {
        return repository.findAll();
    }
}
