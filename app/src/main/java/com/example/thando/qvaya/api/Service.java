package com.example.thando.qvaya.api;

import com.example.thando.qvaya.model.AlbumsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by delaroy on 3/31/17.
 */
public interface Service {
    //https://qvayaapp.000webhostapp.com/Thando/readdrivers.json
    @GET("/Thando/readdrivers.json")
    Call<AlbumsResponse> getAlbums();
}