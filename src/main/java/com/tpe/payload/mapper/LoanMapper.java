package com.tpe.payload.mapper;

import com.tpe.entity.business.Loan;
import com.tpe.payload.response.business.LoanResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoanMapper
{
    /*private LoanResponse convertToLoanResponse(Loan loan) {
        return LoanResponse.builder()
                .id(loan.getId())
                .userId(loan.getUser().getId())
                .book(loan.getBook())
                .loanDate(loan.getLoanDate())
                .expireDate(loan.getExpireDate())
                .returnDate(loan.getReturnDate())
                .build();
    }*/
    public static LoanResponse toLoanResponse(Loan loan) {
        return LoanResponse.builder()
                .id(loan.getId())
                .userId(loan.getUser().getId())
                .bookId(loan.getBook().getId())
                .book(loan.getBook())
                .loanDate(loan.getLoanDate())
                .expireDate(loan.getExpireDate())
                .returnDate(loan.getReturnDate())
                .build();
    }

}
