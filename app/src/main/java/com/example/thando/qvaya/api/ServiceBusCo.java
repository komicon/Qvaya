package com.example.thando.qvaya.api;

import com.example.thando.qvaya.model.AlbumsResponseReadBusCo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by delaroy on 3/31/17.
 */
public interface ServiceBusCo {
    @GET("MB/readBusCo.json")
    Call<AlbumsResponseReadBusCo> getAlbums();
}
