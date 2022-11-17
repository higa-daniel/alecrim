package br.com.alecrimapp.alecrimdelivery.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import br.com.alecrimapp.alecrimdelivery.BuildConfig;
import br.com.alecrimapp.alecrimdelivery.R;
import br.com.alecrimapp.alecrimdelivery.api.OrderService;
import br.com.alecrimapp.alecrimdelivery.api.ServiceGenerator;
import br.com.alecrimapp.alecrimdelivery.models.Customer;
import br.com.alecrimapp.alecrimdelivery.models.CustomerDeliveryAddress;
import br.com.alecrimapp.alecrimdelivery.models.Order;
import br.com.alecrimapp.alecrimdelivery.models.OrderStatus;
import br.com.alecrimapp.alecrimdelivery.models.User;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ricardomiranda on 08/01/16.
 */
public class OrderInfoFragment extends BaseFragment {

    private OnFragmentInteractionListener mListener;
    private TextView txtOrderAddress;
    private TextView txtOrderCustomer;
    private TextView txtOrderPaymentMethod;
    private TextView txtOrderValue;
    private TextView lblOrderAddress;
    private TextView lblOrderCustomer;
    private TextView lblPaymentMethod;
    private TextView lblOrderStatus;
    private Button btnOrderStatus;
    private Order order;

    public OrderInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = null;

        rootView = inflater.inflate(R.layout.fragment_order_info, container, false);

        getActivity().setTitle(R.string.order_info_data);

        setupUI(rootView);

        if (getArguments() != null && getArguments().getSerializable("order") != null){
            this.order = (Order) getArguments().getSerializable("order");

            changeOrderStatus();

            this.populateFields();
        }

        this.registerForContextMenu(btnOrderStatus);

        return rootView;

    }

    private void setupUI(View rootView) {
        Typeface font = null;

        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/gotham_book.otf");

        txtOrderAddress = (TextView) rootView.findViewById(R.id.txtOrderAddress);
        txtOrderCustomer = (TextView) rootView.findViewById(R.id.txtOrderCustomer);
        txtOrderPaymentMethod = (TextView) rootView.findViewById(R.id.txtOrderPaymentMethod);
        txtOrderValue = (TextView) rootView.findViewById(R.id.txtValue);
        lblOrderAddress = (TextView) rootView.findViewById(R.id.lblOrderAddress);
        lblOrderCustomer = (TextView) rootView.findViewById(R.id.lblOrderCustomer);
        lblPaymentMethod = (TextView) rootView.findViewById(R.id.lblPaymentMethod);
        lblOrderStatus = (TextView) rootView.findViewById(R.id.lblOrderStatus);
        btnOrderStatus = (Button) rootView.findViewById(R.id.btnOrderStatus);

        txtOrderAddress.setTypeface(font);
        txtOrderCustomer.setTypeface(font);
        txtOrderPaymentMethod.setTypeface(font);
        txtOrderValue.setTypeface(font);
        lblOrderAddress.setTypeface(font);
        lblOrderCustomer.setTypeface(font);
        lblPaymentMethod.setTypeface(font);
        lblOrderStatus.setTypeface(font);
        btnOrderStatus.setTypeface(font);
    }

    public void populateFields(){
        // Local variables
        String customerAddress = null;
        String customerName = null;
        CustomerDeliveryAddress deliveryAddress = null;
        Customer customer = null;
        User user = null;
        OrderStatus currentStatus = null;

        if (this.order != null){
            if (this.order.getCustomerDeliveryAddress() != null) {
                deliveryAddress = order.getCustomerDeliveryAddress();
                customerAddress = deliveryAddress.getStreet()+", "+deliveryAddress
                        .getStreetNumber()+" "+deliveryAddress.getComplement();
                this.txtOrderAddress.setText(customerAddress);
            }

            if (this.order.getCustomer() != null) {
                customer = order.getCustomer();

                if (customer.getUser() != null) {
                    user = customer.getUser();
                    customerName = user.getFirstName()+" "+user.getLastName();

                    this.txtOrderCustomer.setText(customerName);
                }
            }

            if (this.order.getPaymentType() != null)
                this.txtOrderPaymentMethod.setText(this.order.getPaymentType());

            if (this.order.getAmount() != null)
                this.txtOrderValue.setText("R$ "+this.order.getAmount().toString());

//            if (this.order.getCurrentStatus() != null) {
//                currentStatus = order.getCurrentStatus();
//
//                this.btnOrderStatus.setText(currentStatus.getStatusMessage());
//            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getActivity().getMenuInflater().inflate(R.menu.menu_order_status, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.order_status_3minutes:
                setOrderStatus(getString(R.string.order_status_3minutes));

                break;

            case R.id.order_status_arrived:
                setOrderStatus(getString(R.string.order_status_arrived));

                break;

            case R.id.order_status_delivered:
                setOrderStatus(getString(R.string.order_status_delivered));

                break;

            case R.id.order_status_delivery_problem:
                setOrderStatus(getString(R.string.order_status_delivery_problem));

                break;

            case R.id.order_status_payment_error:
                setOrderStatus(getString(R.string.order_status_payment_error));

                break;
        }

        this.populateFields();

        return super.onContextItemSelected(item);
    }

    public void setOrderStatus(String status){
        btnOrderStatus.setText(status);

        // TODO: pegar o id e fazer requisição

    }

    private void changeOrderStatus() {
        SharedPreferences sharedPreferences = null;
        String token = null;

        getMainActivity().showThrobber();

        sharedPreferences = getActivity().getSharedPreferences(BuildConfig.PREFS_NAME,
                Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", null);

        OrderService orderService = ServiceGenerator.createService(OrderService.class, token);
        orderService.changeOrderStatus(order.getOrderId().intValue(), order.getCustomer().getUser().getUserId(), 6, "", "MOBILE",
                new Callback<Void>() {
                    @Override
                    public void success(Void aVoid, Response response) {
                        getMainActivity().hideThrobber();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //Log.d(error.getMessage(), null);
                        getMainActivity().hideThrobber();
                    }
                });
    }
}
