package com.example.infobyte;

public class StockItem {
    private String NAME;
    private String SYMBOLE;
    private double P_D_CLOSE;
    private double TODAY_OPEN;
    private double TODAY_HIGH;
    private double TODAY_LOW;
    private double LTP;
    private long TODAY_VOLUME;
    private double ChangeInPrice;
    private double ChangeInPRICE;
    private double Perc_changeLong;
    private double Perc_change;

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getSYMBOLE() {
        return SYMBOLE;
    }

    public void setSYMBOLE(String SYMBOLE) {
        this.SYMBOLE = SYMBOLE;
    }

    public double getP_D_CLOSE() {
        return P_D_CLOSE;
    }

    public void setP_D_CLOSE(double P_D_CLOSE) {
        this.P_D_CLOSE = P_D_CLOSE;
    }

    public double getTODAY_OPEN() {
        return TODAY_OPEN;
    }

    public void setTODAY_OPEN(double TODAY_OPEN) {
        this.TODAY_OPEN = TODAY_OPEN;
    }

    public double getTODAY_HIGH() {
        return TODAY_HIGH;
    }

    public void setTODAY_HIGH(double TODAY_HIGH) {
        this.TODAY_HIGH = TODAY_HIGH;
    }

    public double getTODAY_LOW() {
        return TODAY_LOW;
    }

    public void setTODAY_LOW(double TODAY_LOW) {
        this.TODAY_LOW = TODAY_LOW;
    }

    public double getLTP() {
        return LTP;
    }

    public void setLTP(double LTP) {
        this.LTP = LTP;
    }

    public long getTODAY_VOLUME() {
        return TODAY_VOLUME;
    }

    public void setTODAY_VOLUME(long TODAY_VOLUME) {
        this.TODAY_VOLUME = TODAY_VOLUME;
    }

    public double getChangeInPrice() {
        return ChangeInPrice;
    }

    public void setChangeInPrice(double ChangeInPrice) {
        this.ChangeInPrice = ChangeInPrice;
    }

    public double getChangeInPRICE() {
        return ChangeInPRICE;
    }

    public void setChangeInPRICE(double ChangeInPRICE) {
        this.ChangeInPRICE = ChangeInPRICE;
    }

    public double getPerc_changeLong() {
        return Perc_changeLong;
    }

    public void setPerc_changeLong(double Perc_changeLong) {
        this.Perc_changeLong = Perc_changeLong;
    }

    public double getPerc_change() {
        return Perc_change;
    }

    public void setPerc_change(double Perc_change) {
        this.Perc_change = Perc_change;
    }
}

