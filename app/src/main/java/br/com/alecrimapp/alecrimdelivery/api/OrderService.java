package br.com.alecrimapp.alecrimdelivery.api;

import java.util.List;

import br.com.alecrimapp.alecrimdelivery.models.Order;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.PUT;

/**
 * Created by fernandogallo on 09/01/16.
 */
public interface OrderService {
    @GET("/api/order/readytodelivery")
    void fetchOrdersToDelivery(Callback<List<Order>> cb);

    @PUT("/api/order/changestatus")
    void changeOrderStatus(@Field("orderId") int orderId,
                           @Field("userId") String userId,
                           @Field("desiredOrderStatusId") int statusId,
                           @Field("notes") String notes,
                           @Field("interfaceEntry") String interfaceEntry,
                           Callback<Void> cb);
}
