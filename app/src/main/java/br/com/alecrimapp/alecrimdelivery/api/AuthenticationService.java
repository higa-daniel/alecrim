package br.com.alecrimapp.alecrimdelivery.api;

import br.com.alecrimapp.alecrimdelivery.models.User;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by fernandogallo on 06/01/16.
 */
public interface AuthenticationService {
    @FormUrlEncoded
    @POST("/token")
    void authenticate(@Field("grant_type") String grantType,
                      @Field("username") String email,
                      @Field("password") String password,
                      @Field("scope") String scope,
                      Callback<User> cb);
}
