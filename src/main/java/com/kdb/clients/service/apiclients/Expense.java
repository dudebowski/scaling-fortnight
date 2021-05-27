package com.kdb.clients.service.apiclients;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class Expense {


    @JsonProperty("client_id")
    public Long getClientId() {
        return clientId;
    }

    @JsonProperty("client_id")
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @JsonProperty("date")
    public Date getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(Date date) {
        this.date = date;
    }

    @JsonProperty("amount")
    public BigDecimal getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    private Long clientId;

    private Date date;

    private BigDecimal amount;
}
