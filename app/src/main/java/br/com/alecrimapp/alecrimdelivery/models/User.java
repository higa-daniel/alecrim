package br.com.alecrimapp.alecrimdelivery.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by fernandogallo on 06/01/16.
 */
public class User implements Serializable {

    private String userId;
    private String firstName;
    private String lastName;

    @SerializedName("entityId")
    private String customerId;

    @SerializedName("access_token")
    private String token;

    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("expires_in")
    private int tokenExpiry;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getTokenExpiry() {
        return tokenExpiry;
    }

    public void setTokenExpiry(int tokenExpiry) {
        this.tokenExpiry = tokenExpiry;
    }
}
