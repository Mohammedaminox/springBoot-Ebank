package com.system.ebanky.Controller.vm.Transaction.Request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApproveTransactionRequest {
    private String role;
    private String choix;
}
