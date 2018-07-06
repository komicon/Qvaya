package com.example.thando.qvaya.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by delaroy on 3/31/17.
 */
public class AlbumsResponseReadBusCo {

    @SerializedName("Driver")
    @Expose
    private List<AlbumReadBusCo> albums;

    public List<AlbumReadBusCo> getAlbums(){
        return albums;
    }
    public void setAlbums(List<AlbumReadBusCo> albums){
        this.albums = albums;
    }
}
