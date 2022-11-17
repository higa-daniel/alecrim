package br.com.alecrimapp.alecrimdelivery.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.alecrimapp.alecrimdelivery.R;
import br.com.alecrimapp.alecrimdelivery.models.Order;

/**
 * Created by ricardomiranda on 08/01/16.
 */
public class OrderListAdapter extends ArrayAdapter<Order>{


    private Context context;
    private List<Order> items;

    public OrderListAdapter(Context ctx, List<Order> list){
        super(ctx, R.layout.orders_item, list);

        this.context = ctx;
        this.items = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Local variables
        TextView txtOrderNumber = null;
        TextView txtDeliveryTime = null;
        View view = null;
        Order item = null;
        Typeface font = null;

        font = Typeface.createFromAsset(context.getAssets(), "fonts/gotham_book.otf");

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.orders_item, null);
        } else{
            view = convertView;
        }

        item = this.items.get(position);

        txtOrderNumber = (TextView) view.findViewById(R.id.txtItemOrderNumber);
        txtDeliveryTime = (TextView) view.findViewById(R.id.txtItemDeliveryTime);

        txtOrderNumber.setTypeface(font);
        txtDeliveryTime.setTypeface(font);

        // TODO: set values for fields
        txtOrderNumber.setText(item.getOrderId().toString());
        txtDeliveryTime.setText(item.getCalculatedTime()+" Min");

        return view;
    }
}
