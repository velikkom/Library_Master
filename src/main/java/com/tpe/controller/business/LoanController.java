package com.tpe.controller.business;

import com.tpe.entity.business.Loan;
import com.tpe.payload.mapper.LoanMapper;
import com.tpe.payload.response.business.LoanResponse;
import com.tpe.service.business.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loans")
public class LoanController
{
    private final LoanService loanService;
    private final LoanMapper loanMapper;

    //http://localhost:8080/loans/loans? page = size 10& sort loanDate& type desc + MEMBER
    @GetMapping("/loan")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<Page<LoanResponse>> getLoans(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "loanDate") String sort,
            @RequestParam(defaultValue = "desc") String type)
    {
        Pageable pageable = PageRequest.of(page, size, Sort.by(type.equalsIgnoreCase("asc") ? Sort.Order.asc(sort) : Sort.Order.desc(sort)));
        Page<Loan> loanPage = loanService.getLoans(pageable);

        List<LoanResponse> loanResponses = loanPage.getContent().stream()
                .map(LoanMapper::toLoanResponse)
                .collect(Collectors.toList());

        Page<LoanResponse> responsePage = new PageImpl<>(loanResponses, pageable, loanPage.getTotalElements());

        return new ResponseEntity<>(responsePage, HttpStatus.OK);
    }



}
