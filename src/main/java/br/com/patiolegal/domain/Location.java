package br.com.patiolegal.domain;

public class Location {

    private Shed shed;
    private String row;
    private String column;
    private String floor;

    public Shed getShed() {
        return shed;
    }

    public String getRow() {
        return row;
    }

    public String getColumn() {
        return column;
    }

    public String getFloor() {
        return floor;
    }

    public void setShed(Shed shed) {
        this.shed = shed;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

}
