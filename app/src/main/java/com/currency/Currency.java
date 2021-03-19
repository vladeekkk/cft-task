package com.currency;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Currency {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = Constants.CHAR_CODE)
    private String charCode;

    @ColumnInfo(name = Constants.NOMINAL)
    private int nominal;

    @ColumnInfo(name = Constants.NAME)
    private String name;

    @ColumnInfo(name = Constants.VALUE)
    private String value;

    @ColumnInfo(name = Constants.PREVIOUS)
    private String previous;

    public Currency(String charCode, int nominal, String name, String value, String previous) {
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
        this.previous = previous;
    }

    public Currency(String charCode, int nominal, String name, double value, double previous) {
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = String.valueOf(value);
        this.previous = String.valueOf(previous);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }
}