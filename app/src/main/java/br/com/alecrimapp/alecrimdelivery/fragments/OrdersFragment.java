package br.com.alecrimapp.alecrimdelivery.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.alecrimapp.alecrimdelivery.BuildConfig;
import br.com.alecrimapp.alecrimdelivery.R;
import br.com.alecrimapp.alecrimdelivery.adapters.OrderListAdapter;
import br.com.alecrimapp.alecrimdelivery.api.OrderService;
import br.com.alecrimapp.alecrimdelivery.api.ServiceGenerator;
import br.com.alecrimapp.alecrimdelivery.models.Order;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OrdersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class OrdersFragment extends BaseFragment {

    private OnFragmentInteractionListener mListener;
    private List<Order> orderList;
    private ListView listView;
    private ImageButton btnOrderInfo;
    private TextView txtNextDelivery;
    private TextView txtOrderNumber;
    private TextView txtDeliveryTime;
    private TextView txtWaitingDeliveries;
    private TextView txtPaymentMethod;

    public OrdersFragment() {
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

        fetchOrders();

        View rootView = null;

        rootView = inflater.inflate(R.layout.fragment_orders, container, false);

        setupUI(rootView);

        setupListeners();

        return rootView;

    }

    private void setupListeners() {
        btnOrderInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Local variables
                OrderInfoFragment fragment = null;
                Bundle args = null;
                Order order = null;

                args = new Bundle();

                if (orderList.size() > 0)
                    order = orderList.get(0);

                args.putSerializable("order", order);

                fragment = new OrderInfoFragment();

                fragment.setArguments(args);

                replaceFragment(fragment, "order");
            }
        });
    }

    private void setupUI(View rootView) {
        Typeface font = null;

        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/gotham_book.otf");

        listView = (ListView) rootView.findViewById(R.id.lstOrders);
        btnOrderInfo = (ImageButton) rootView.findViewById(R.id.btnOrderInfo);
        txtNextDelivery = (TextView) rootView.findViewById(R.id.txtNextDelivery);
        txtOrderNumber = (TextView) rootView.findViewById(R.id.txtOrderNumber);
        txtDeliveryTime = (TextView) rootView.findViewById(R.id.txtDeliveryTime);
        txtWaitingDeliveries = (TextView) rootView.findViewById(R.id.txtWaitingDeliveries);
        txtPaymentMethod = (TextView) rootView.findViewById(R.id.txtPaymentMethod);

        txtNextDelivery.setTypeface(font);
        txtOrderNumber.setTypeface(font);
        txtDeliveryTime.setTypeface(font);
        txtWaitingDeliveries.setTypeface(font);
        txtPaymentMethod.setTypeface(font);
    }

    public void populateFields(){
        Order order = null;
        OrderListAdapter adapter = null;
        List<Order> orders = null;

        if (orderList != null && orderList.size() > 0){
            order = orderList.get(0);

            if (orderList.size() > 1){
                orders = orderList.subList(1, orderList.size());

                adapter = new OrderListAdapter(this.getActivity(), orders);

                this.listView.setAdapter(adapter);

                adapter.notifyDataSetChanged();
            }

            txtOrderNumber.setText(order.getOrderId().toString());
            txtDeliveryTime.setText(order.getCalculatedTime()+" Min");
            txtPaymentMethod.setText(order.getPaymentType());
        } else {
            this.btnOrderInfo.setVisibility(View.GONE);
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

    private void fetchOrders() {
        SharedPreferences sharedPreferences = null;
        String token = null;

        getMainActivity().showThrobber();

        sharedPreferences = getActivity().getSharedPreferences(BuildConfig.PREFS_NAME,
                Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", null);

        OrderService orderService = ServiceGenerator.createService(OrderService.class, token);
        orderService.fetchOrdersToDelivery(new Callback<List<Order>>() {
            @Override
            public void success(List<Order> orders, Response response) {
                orderList = orders;

                populateFields();

                getMainActivity().hideThrobber();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(error.getMessage(), null);
                getMainActivity().hideThrobber();
            }
        });
    }

}
