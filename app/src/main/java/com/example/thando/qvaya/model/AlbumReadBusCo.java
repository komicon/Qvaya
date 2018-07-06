package com.example.thando.qvaya.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Delaroy Studios
 */
public class AlbumReadBusCo {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ratings")
    @Expose
    private String numOfSongs;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;



    @SerializedName("YearOfRelease")
    @Expose
    private String driverjoinyear;
    public AlbumReadBusCo() {
    }

    public AlbumReadBusCo(String name, String numOfSongs, String thumbnail, String driverjoinyear) {
        this.name = name;
        this.numOfSongs = numOfSongs;
        this.thumbnail = thumbnail;
        this.driverjoinyear = driverjoinyear;
    }

    public String getName() {
        return name;
    }
    public String getDriverjoinyear() {return driverjoinyear;}
    public void setDriverjoinyear(String driverjoinyear) {this.driverjoinyear = driverjoinyear;}
    public void setName(String name) {
        this.name = name;
    }

    public String getNumOfSongs() {
        return numOfSongs;
    }

    public void setNumOfSongs(String numOfSongs) {
        this.numOfSongs = numOfSongs;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
