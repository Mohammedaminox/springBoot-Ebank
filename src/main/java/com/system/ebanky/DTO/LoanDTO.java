package com.system.ebanky.DTO;

import com.system.ebanky.Entity.Enums.LoanStatus;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanDTO {
    private long id;
    private double principle;
    private double interest_rate;
    private int termMonths;
    private LocalDate start_date;
    private String garanty;
    private LoanStatus approved;
    private long userId; // To avoid embedding the full User object

}
