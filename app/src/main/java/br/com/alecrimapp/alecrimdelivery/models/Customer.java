package br.com.alecrimapp.alecrimdelivery.models;

import java.io.Serializable;

/**
 * Created by ricardomiranda on 09/01/16.
 */
public class Customer implements Serializable {
    private long customerId;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
}
