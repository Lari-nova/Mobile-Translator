package com.example.mytranslator.network;

import com.example.mytranslator.entity.Languages;
import com.example.mytranslator.entity.Translation;
import java.io.IOException;
import java.util.function.ToDoubleBiFunction;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager implements InterfaceNetworkManager {

    private ApiMessages apiMessages;
    private  Retrofit retrofit;

    public NetworkManager (String baseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiMessages = retrofit.create(ApiMessages.class);
    }

    @Override
    public Observable<Translation> subscribeTranslation(String apiKey, final String text, final String lang, final String format, final int options) {
        //TODO все также падает
        return Observable.create(e -> e.onNext(translation(apiKey, text, lang, format, options)));
    }

    @Override
    public Observable<Languages> subscribeLangs(String apiKey, String uiLangCode) {
        return Observable.create(emitter -> emitter.onNext(requestLanguages(apiKey, uiLangCode)));
    }

    private Translation translation(String apiKey, String text, String lang, String format, int options) {
        Call<Translation> translate = apiMessages.translate(apiKey, text,  lang, format, options);
        try {
            Response<Translation> response = translate.execute();
            return response.body();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private Languages requestLanguages(String apiKey, String uiLangCode) {
        Call<Languages> languages =  apiMessages.getLangs(apiKey, uiLangCode);
        try {
            Response<Languages> response = languages.execute();
            return response.body();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
