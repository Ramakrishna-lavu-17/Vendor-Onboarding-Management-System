package com.HCl.VOCLMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HCl.VOCLMS.entity.Contract;
import com.HCl.VOCLMS.service.ContractService;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;

    // Create contract
    @PostMapping("/{vendorId}")
    public Contract create(@PathVariable Long vendorId,
                           @RequestBody Contract contract) {
        return contractService.createContract(vendorId, contract);
    }

    // Activate contract
    @PutMapping("/{id}/activate")
    public Contract activate(@PathVariable Long id) {
        return contractService.activateContract(id);
    }
}