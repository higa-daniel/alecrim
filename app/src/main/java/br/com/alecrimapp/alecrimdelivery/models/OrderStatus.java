package br.com.alecrimapp.alecrimdelivery.models;

import java.io.Serializable;

/**
 * Created by ricardomiranda on 09/01/16.
 */
public class OrderStatus implements Serializable {
    private long orderStatusId;
    private String name;
    private String description;
    private String statusMessage;
    private Boolean isFinal;
    private Boolean isReady;
    private Boolean canChangeOrderParams;
    private Boolean isReturningQuantity;
    private Boolean isStartProduction;

    public long getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(long orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Boolean getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(Boolean isFinal) {
        this.isFinal = isFinal;
    }

    public Boolean getIsReady() {
        return isReady;
    }

    public void setIsReady(Boolean isReady) {
        this.isReady = isReady;
    }

    public Boolean getCanChangeOrderParams() {
        return canChangeOrderParams;
    }

    public void setCanChangeOrderParams(Boolean canChangeOrderParams) {
        this.canChangeOrderParams = canChangeOrderParams;
    }

    public Boolean getIsReturningQuantity() {
        return isReturningQuantity;
    }

    public void setIsReturningQuantity(Boolean isReturningQuantity) {
        this.isReturningQuantity = isReturningQuantity;
    }

    public Boolean getIsStartProduction() {
        return isStartProduction;
    }

    public void setIsStartProduction(Boolean isStartProduction) {
        this.isStartProduction = isStartProduction;
    }
}
