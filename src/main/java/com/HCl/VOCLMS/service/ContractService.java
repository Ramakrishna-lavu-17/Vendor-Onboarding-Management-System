package com.HCl.VOCLMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HCl.VOCLMS.entity.Contract;
import com.HCl.VOCLMS.entity.Vendor;
import com.HCl.VOCLMS.repository.ContractRepository;
import com.HCl.VOCLMS.repository.VendorRepository;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private VendorRepository vendorRepository;

    // Create contract
    public Contract createContract(Long vendorId, Contract contract) {

        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow();

        if (!vendor.getStatus().equals("APPROVED")) {
            throw new RuntimeException("Vendor not approved");
        }

        contract.setVendor(vendor);
        contract.setStatus("DRAFT");

        return contractRepository.save(contract);
    }

    // Activate contract
    public Contract activateContract(Long id) {
        Contract contract = contractRepository.findById(id).orElseThrow();
        contract.setStatus("ACTIVE");
        return contractRepository.save(contract);
    }
}