package com.data.net;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    public static final String TRACE_ID_PRE_SHOP = "11";
    public static final String TRACE_ID_PRE_TERMINAL = "10";
    private static final int CONNECT_TIMEOUT_SECOND = 15;
    private static final int READ_TIMEOUT_SECOND = 15;
    private static final int WRITE_TIMEOUT_SECOND = 15;

    private API api;
    private ClientConfig mClientConfig;

    public Client(ClientConfig clientConfig) {
        mClientConfig = clientConfig;
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BASIC))
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request request = original.newBuilder()
                                .method(original.method(), original.body())
                                .build();
                        return chain.proceed(request);
                    }
                })
                .connectTimeout(CONNECT_TIMEOUT_SECOND, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_SECOND, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .writeTimeout(WRITE_TIMEOUT_SECOND, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mClientConfig.apiBaseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = retrofit.create(API.class);
    }

    public API getAPI() {
        return api;
    }

    //测试接口：
    //http://api.m.taobao.com/rest/api3.do?api=mtop.common.getTimestamp
    public static class ClientConfig {
        String apiBaseUrl = "http://api.m.taobao.com/";

//        public ClientConfig(String apiBaseUrl) {
//            this.apiBaseUrl = apiBaseUrl;
//        }
    }
}
