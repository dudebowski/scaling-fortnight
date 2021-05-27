package com.kdb.clients.service.dto;

import java.math.BigDecimal;

public class TotalExpenseDTO {

    public TotalExpenseDTO(Long clientId, BigDecimal totalAmount) {
        this.clientId = clientId;
        this.totalAmount = totalAmount;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    Long clientId;
    BigDecimal totalAmount;

}
