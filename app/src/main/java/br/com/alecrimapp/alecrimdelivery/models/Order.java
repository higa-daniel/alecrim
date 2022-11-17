package br.com.alecrimapp.alecrimdelivery.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by ricardomiranda on 07/01/16.
 */

public class Order implements Serializable {
    private Long orderId;
    private String paymentType;
    private Double amount;
    private Customer customer;
    private CustomerDeliveryAddress customerDeliveryAddress;
    private OrderStatus currentStatus;
    private Schedule schedule;
    private String beginDateTime;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerDeliveryAddress getCustomerDeliveryAddress() {
        return customerDeliveryAddress;
    }

    public void setCustomerDeliveryAddress(CustomerDeliveryAddress customerDeliveryAddress) {
        this.customerDeliveryAddress = customerDeliveryAddress;
    }

    public OrderStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(OrderStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getBeginDateTime() {
        return beginDateTime;
    }

    public void setBeginDateTime(String beginDateTime) {
        this.beginDateTime = beginDateTime;
    }

    public int getCalculatedTime(){
        SimpleDateFormat sdf = null;
        Date orderTime = null;
        Date now = null;
        Calendar calendar = null;
        int result = 0;
        long diff = 0;

        if (this.getBeginDateTime() != null && this.getSchedule() != null) {
            try {
                now = new Date();
                sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                sdf.setTimeZone(TimeZone.getTimeZone("Europe/London"));
                orderTime = sdf.parse(this.getBeginDateTime());
                diff = now.getTime() - orderTime.getTime();

                diff /= 1000;
                diff /= 60;

                result = (int) (this.getSchedule().getEtaInMinutes() - diff);

            } catch (Exception e){
                e.printStackTrace();
                // TODO: logar
            }
        }

        return result;
    }

    public enum PaymentMethod {
        CreditCard("CREDIT_CARD"), AleloVisaVale("ALELO_VISA_VALE"), Sodexo("SODEXO"), TicketRestaurante("TICKET_RESTAURANTE"), VrSmart("VR_SMART");

        private String value;

        public String getValue(){
            return this.value;
        }

        private PaymentMethod(String value) {
            this.value = value;
        }
    }


}
