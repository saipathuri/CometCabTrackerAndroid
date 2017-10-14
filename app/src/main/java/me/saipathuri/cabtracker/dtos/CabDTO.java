package me.saipathuri.cabtracker.dtos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by saipathuri on 10/13/17.
 * Cab data transfer object. Used to deserialize cab information from API.
 */

public class CabDTO {
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private String id;
    @SerializedName("heading")
    private int heading;
    @SerializedName("ignition")
    private boolean ignition;
    @SerializedName("latitude")
    protected String latitude;
    @SerializedName("longitude")
    protected String longitude;
    @SerializedName("moved")
    private String moved;
    @SerializedName("updated")
    private String updated;
    @SerializedName("velocity")
    private int velocity;

    public CabDTO(String name, String id, int heading, boolean ignition, String latitude, String longitude, String moved, String updated, int velocity) {
        this.name = name;
        this.id = id;
        this.heading = heading;
        this.ignition = ignition;
        this.latitude = latitude;
        this.longitude = longitude;
        this.moved = moved;
        this.updated = updated;
        this.velocity = velocity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public boolean isIgnition() {
        return ignition;
    }

    public void setIgnition(boolean ignition) {
        this.ignition = ignition;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMoved() {
        return moved;
    }

    public void setMoved(String moved) {
        this.moved = moved;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    @Override
    public String toString() {
        return "CabDTO{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", heading=" + heading +
                ", ignition=" + ignition +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", moved='" + moved + '\'' +
                ", updated='" + updated + '\'' +
                ", velocity=" + velocity +
                '}';
    }
}
