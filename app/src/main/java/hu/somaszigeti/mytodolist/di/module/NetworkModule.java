package hu.somaszigeti.mytodolist.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.somaszigeti.mytodolist.BuildConfig;
import hu.somaszigeti.mytodolist.network.TodoApi;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    public Gson provideGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
    }

    @Provides
    public Retrofit provideRetrofit(Gson gson) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.connectTimeout(20, TimeUnit.SECONDS);
        httpClient.readTimeout(50, TimeUnit.SECONDS);
        httpClient.writeTimeout(20, TimeUnit.SECONDS);

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
    }

    @Provides
    public Scheduler provideScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    public TodoApi provideTodoApi(Retrofit retrofit) {
        return retrofit.create(TodoApi.class);
    }
}