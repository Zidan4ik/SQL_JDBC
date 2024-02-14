package com.sql.jdbc.entity;

public class Order {
    private int id;
    private String list;
    private int totalSumma;

    public Order() {
    }

    public Order(int id, String list, int totalSumma) {
        this.id = id;
        this.list = list;
        this.totalSumma = totalSumma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public int getTotalSumma() {
        return totalSumma;
    }

    public void setTotalSumma(int totalSumma) {
        this.totalSumma = totalSumma;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", list=(" + list +
                "), totalSumma=" + totalSumma;
    }
}
