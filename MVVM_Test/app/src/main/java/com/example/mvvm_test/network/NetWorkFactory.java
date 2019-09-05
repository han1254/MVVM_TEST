package com.example.mvvm_test.network;

import android.content.SharedPreferences;


import com.example.mvvm_test.cacheutil.CacheKey;
import com.example.mvvm_test.cacheutil.CacheUtil;
import com.example.mvvm_test.commn.Config;
import com.example.mvvm_test.network.converter.ResponseConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import retrofit2.Retrofit;

import static com.example.mvvm_test.commn.Config.BASE_URL;


public  final class NetWorkFactory {

    private static OkHttpClient sOkHttpClient;
    private static Retrofit sRetrofit;
    private static ApiService sApiService;

    /**
     * 生成service接口
     *
     * @retrun RetrofitService
     */
    public static ApiService getApiService(){
        if (sApiService == null ) {
            sApiService = getsRetrofit().create(ApiService.class);
        }
        return sApiService;
    }

    /**
     * 构造Retrofit，设置相关参数
     *
     * @return Retrofit
     */
    private static Retrofit getsRetrofit() {
        if(sRetrofit == null){
            sRetrofit = new Retrofit.Builder()
                    .client(getsOkHttpClient())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ResponseConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }

    public static OkHttpClient getsOkHttpClient() {
        if( sOkHttpClient == null ) {
            sOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(Config.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
//                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

                    .addNetworkInterceptor(getNetworkInterceptor())
                    .build();



        }
        return sOkHttpClient;
    }

    public static Interceptor getNetworkInterceptor(){
        final SharedPreferences sp = CacheUtil.getSP();
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String token = sp.getString(CacheKey.CACHEKEY_TOKEN, "");
                sp.edit().putString(CacheKey.CACHEKEY_TOKEN,"aca3f3eb377e49e09e17b2f8ae23a924").apply();
                Request request = chain.request().newBuilder()
                        .header(CacheKey.CACHEKEY_TOKEN,token)
                        .build();
                return chain.proceed(request);
            }
        };

    }


}

