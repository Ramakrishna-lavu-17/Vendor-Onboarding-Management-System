package com.HCl.VOCLMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HCl.VOCLMS.entity.Vendor;
import com.HCl.VOCLMS.service.VendorService;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    // Register vendor
    @PostMapping("/register")
    public Vendor register(@RequestBody Vendor vendor) {
        return vendorService.registerVendor(vendor);
    }

    // Get all vendors
    @GetMapping
    public List<Vendor> getAll() {
        return vendorService.getAllVendors();
    }

    // Get vendors by status
    @GetMapping("/status")
    public List<Vendor> getByStatus(@RequestParam String status) {
        return vendorService.getByStatus(status);
    }

    // Approve vendor
    @PutMapping("/{id}/approve")
    public Vendor approve(@PathVariable Long id) {
        return vendorService.approveVendor(id);
    }

    // Reject vendor
    @PutMapping("/{id}/reject")
    public Vendor reject(@PathVariable Long id) {
        return vendorService.rejectVendor(id);
    }
}