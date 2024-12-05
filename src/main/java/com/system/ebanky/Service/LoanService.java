package com.system.ebanky.Service;

import com.system.ebanky.DTO.LoanDTO;

import java.util.List;

public interface LoanService {

    LoanDTO createLoan(LoanDTO loanDTO);

    LoanDTO approveLoan(Long loanId);

    LoanDTO rejectLoan(Long loanId);

    LoanDTO getLoanById(Long loanId);

    List<LoanDTO> getLoansByUserId(Long userId);

    List<LoanDTO> getAllLoans();
}

