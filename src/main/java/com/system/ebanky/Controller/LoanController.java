package com.system.ebanky.Controller;

import com.system.ebanky.DTO.LoanDTO;
import com.system.ebanky.DTO.RoleDTO;
import com.system.ebanky.Entity.Enums.Role;
import com.system.ebanky.Service.LoanService;
import com.system.ebanky.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/loan")
public class LoanController {
private final LoanService loanService;

private final UserService userService;

    public LoanController(LoanService loanService, UserService userService) {
        this.loanService = loanService;
        this.userService=userService;
    }

    @PostMapping("/post")
    public ResponseEntity<String> createLoan(@RequestBody LoanDTO loan){
        try {
            LoanDTO loanCreated = loanService.createLoan(loan);
            return ResponseEntity.ok("the loan created successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/user/{user}")
    public ResponseEntity<?> getLoansByUser(@PathVariable long user){
        try {
            List<LoanDTO> loans = loanService.getLoansByUserId(user);
            return ResponseEntity.ok(loans);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> gerLoanById(@PathVariable long id){
        try{
            LoanDTO loan=loanService.getLoanById(id);
            return ResponseEntity.ok(loan);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/approve/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> approveLoan(@PathVariable long id) {
        loanService.approveLoan(id);
        return ResponseEntity.ok("Loan approved successfully.");
    }

    @GetMapping("/decline/{id}")
    public ResponseEntity<?> declineLoan(@PathVariable long id,@RequestBody RoleDTO role){
        try{
            if (!Role.ADMIN.toString().equals(role.getRole().toString())){
                throw new RuntimeException("you does not have the access to approve this loan");
            }else {
                loanService.rejectLoan(id);
                return ResponseEntity.ok("the loan has been rejected");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
