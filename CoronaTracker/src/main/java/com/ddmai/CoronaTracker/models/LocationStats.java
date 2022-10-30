package com.ddmai.CoronaTracker.models;

public class LocationStats {
    private String state;
    private String country;
    private int latestTotalCases;

    private int diffFromPreviousDay;



    private int[] cases;
    public LocationStats(String state, String country, int latestTotalCases) {
        setState(state);
        setCountry(country);
        setLatestTotalCases(latestTotalCases);
    }

    public int[] getCases() {
        return cases;
    }

    public void setCases(int[] cases) {
        this.cases = cases;
    }
    public int getDiffFromPreviousDay() {
        return diffFromPreviousDay;
    }

    public void setDiffFromPreviousDay(int diffFromPreviousDay) {
        this.diffFromPreviousDay = diffFromPreviousDay;
    }

    public String getState() {
        return state;
    }

    private void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    private void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    private void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalCases=" + latestTotalCases +
                '}';
    }
}
