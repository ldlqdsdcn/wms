package com.delmar.corebus.model;

public class TransactionWithBLOBs extends Transaction {
    private String tranparam;

    private String transresult;

    public String getTranparam() {
        return tranparam;
    }

    public void setTranparam(String tranparam) {
        this.tranparam = tranparam == null ? null : tranparam.trim();
    }

    public String getTransresult() {
        return transresult;
    }

    public void setTransresult(String transresult) {
        this.transresult = transresult == null ? null : transresult.trim();
    }
}