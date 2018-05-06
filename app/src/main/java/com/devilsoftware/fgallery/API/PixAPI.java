package com.devilsoftware.fgallery.API;

import com.devilsoftware.fgallery.API.Models.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixAPI {

    @GET("/api")
    Call<Response> getPhotos(@Query("key") String key, @Query("q") String searchTerm,
                             @Query("page") int page ,@Query("category") String category,
                             @Query("colors") String colors);

}
