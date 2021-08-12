package sg.edu.rp.c346.id20025732.academicrecords;

import java.io.Serializable;

public class Records implements Serializable {

    private int id;
    private String name;
    private int year;
    private String results;

    public Records(int id, String name, int year, String results){

        this.id = id;
        this.name = name;
        this.year = year;
        this.results = results;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }


}
