package com.example.mytranslator.network;

import com.example.mytranslator.entity.Detection;
import com.example.mytranslator.entity.Languages;
import com.example.mytranslator.entity.Translation;

import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiMessages {

    @FormUrlEncoded
    @POST("api/v1.5/tr.json/translate")
    Call<Translation> translate(@Query("key") String apiKey,
                                @Field("text") String text,
                                @Query("lang") String lang,
                                @Query("format") String format,
                                @Query("options") int options);

    @FormUrlEncoded
    @GET("api/v1.5/tr.json/detect")
    Call<Detection> detectLang(@Query("key") String apiKey,
                               @Field("text") String text,
                               @Query("hint") String hint);

    @GET("api/v1.5/tr.json/getLangs")
    Call<Languages> getLangs(@Query("key") String apiKey,
                             @Query("ui") String uiLangCode);
}
