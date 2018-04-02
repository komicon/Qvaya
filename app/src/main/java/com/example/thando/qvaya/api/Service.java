package com.example.thando.qvaya.api;

import com.example.thando.qvaya.model.AlbumsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by delaroy on 3/31/17.
 */
public interface Service {
    @GET("/QvayaApp/readdrivers.json")
    Call<AlbumsResponse> getAlbums();
}
