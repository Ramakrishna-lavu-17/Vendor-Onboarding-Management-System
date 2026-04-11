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

    
    public Contract createContract(Long vendorId, Contract contract) {

        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow();

        // Business Rule: Vendor must be approved
        if (!vendor.getStatus().equals("APPROVED")) {
            throw new RuntimeException("Vendor not approved");
        }

        contract.setVendor(vendor);
        contract.setStatus("DRAFT");

        return contractRepository.save(contract);
    }

    public Contract submitForApproval(Long contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow();

        if (!contract.getStatus().equals("DRAFT")) {
            throw new RuntimeException("Only DRAFT contracts can be submitted");
        }

        contract.setStatus("PENDING_APPROVAL");
        return contractRepository.save(contract);
    }

    public Contract approveContract(Long contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow();

        if (!contract.getStatus().equals("PENDING_APPROVAL")) {
            throw new RuntimeException("Contract not in approval stage");
        }

        contract.setStatus("APPROVED");
        return contractRepository.save(contract);
    }

    public Contract activateContract(Long id) {
        Contract contract = contractRepository.findById(id).orElseThrow();

        if (!contract.getStatus().equals("APPROVED")) {
            throw new RuntimeException("Contract must be approved before activation");
        }

        contract.setStatus("ACTIVE");
        return contractRepository.save(contract);
    }
}