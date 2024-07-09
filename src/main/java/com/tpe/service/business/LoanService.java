package com.tpe.service.business;

import com.tpe.entity.business.Loan;
import com.tpe.repository.business.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    public Page<Loan> getLoans(Pageable pageable)
    {
        return loanRepository.findAll(pageable);
    }
}
