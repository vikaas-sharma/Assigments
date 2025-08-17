package com.example.billing_service.controller;

import com.example.billing_service.model.Bill;
import com.example.billing_service.service.BillingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillingController {

    private final BillingService service;

    public BillingController(BillingService service) {
        this.service = service;
    }

    @PostMapping
    public Bill createBill(@RequestBody Bill bill) {
        return service.generateBill(bill);
    }

    @PutMapping("/{id}/pay")
    public Bill payBill(@PathVariable Long id, @RequestParam String method) {
        return service.markAsPaid(id, method);
    }

    @PutMapping("/{id}/insurance")
    public Bill updateInsurance(@PathVariable Long id, @RequestParam String status) {
        return service.updateInsuranceStatus(id, status);
    }

    @GetMapping
    public List<Bill> getAll() {
        return service.getAllBills();
    }
}
