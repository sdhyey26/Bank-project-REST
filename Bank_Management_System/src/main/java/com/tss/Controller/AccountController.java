package com.tss.Controller;

import com.tss.Dto.AccountResponseDto;
import com.tss.Dto.AccountStatusRequestDto;
import com.tss.Entity.Account;
import com.tss.Service.AccountManagementService;
import com.tss.Service.AccountService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

	@Autowired
    private final AccountService accountService;
    private final AccountManagementService accountManagementService;

    @GetMapping
    public ResponseEntity<List<AccountResponseDto>> getAll() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountResponseDto> getOne(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.getAccount(accountNumber));
    }

    @GetMapping("/my-account")
    public ResponseEntity<AccountResponseDto> getMyAccount(@AuthenticationPrincipal String username) {
        return ResponseEntity.ok(accountService.getAccountByUsername(username));
    }

    @PostMapping("/status/update")
    public ResponseEntity<Account> updateAccountStatus(@RequestBody AccountStatusRequestDto request) {
        return ResponseEntity.ok(accountManagementService.updateAccountStatus(request));
    }

    @PostMapping("/{accountNumber}/freeze")
    public ResponseEntity<Account> freezeAccount(@PathVariable String accountNumber, @RequestParam(required = false) String reason) {
        return ResponseEntity.ok(accountManagementService.freezeAccount(accountNumber, reason));
    }

    @PostMapping("/{accountNumber}/unfreeze")
    public ResponseEntity<Account> unfreezeAccount(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountManagementService.unfreezeAccount(accountNumber));
    }

    @PostMapping("/{accountNumber}/suspend")
    public ResponseEntity<Account> suspendAccount(@PathVariable String accountNumber, @RequestParam(required = false) String reason) {
        return ResponseEntity.ok(accountManagementService.suspendAccount(accountNumber, reason));
    }

    @PostMapping("/{accountNumber}/close")
    public ResponseEntity<Account> closeAccount(@PathVariable String accountNumber, @RequestParam(required = false) String reason) {
        return ResponseEntity.ok(accountManagementService.closeAccount(accountNumber, reason));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Account>> getAccountsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(accountManagementService.getAccountsByStatus(status));
    }
}


