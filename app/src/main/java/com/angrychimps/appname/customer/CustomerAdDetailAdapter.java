package com.angrychimps.appname.customer;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.angrychimps.appname.R;
import com.angrychimps.appname.models.Service;

import java.util.List;

class CustomerAdDetailAdapter extends ArrayAdapter<Service> {

    private final LayoutInflater layoutInflater;
    private final List<Service> list;

    public CustomerAdDetailAdapter(Context context, List<Service> list) {
        super(context, android.R.layout.simple_list_item_1, list);
        this.layoutInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder viewHolder;

        // Reuse views
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.card_ad_detail_item, parent, false);

            // Configure ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.tvAdDetailTitle = (TextView) convertView.findViewById(R.id.tvAdDetailTitle);
            viewHolder.tvAdDetailDescription = (TextView) convertView.findViewById(R.id.tvAdDetailDescription);
            viewHolder.tvAdDetailDiscount = (TextView) convertView.findViewById(R.id.tvAdDetailDiscount);
            viewHolder.tvAdDetailPrice = (TextView) convertView.findViewById(R.id.tvAdDetailPrice);
            viewHolder.tvAdDetailPriceDecimal = (TextView) convertView.findViewById(R.id.tvAdDetailPriceDecimal);
            viewHolder.tvAdDetailPriceDecimal.setPaintFlags(viewHolder.tvAdDetailPriceDecimal.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvAdDetailDescription.setText(list.get(position).getDescription());
        viewHolder.tvAdDetailTitle.setText(list.get(position).getName());
        viewHolder.tvAdDetailPrice.setText("" + (int) list.get(position).getDiscounted_price());
        if (list.get(position).getDiscounted_price_decimal() > 0) viewHolder.tvAdDetailPriceDecimal.setText(""
                + list.get(position).getDiscounted_price_decimal());
        viewHolder.tvAdDetailDiscount.setText("     " + list.get(position).getDiscount() + "%\ndiscount");

        return convertView;
    }

    // ViewHolder increases speed and efficiency by recycling views rather than doing many findViewByIds, which are expensive.
    static class ViewHolder {
        TextView tvAdDetailTitle, tvAdDetailDescription, tvAdDetailDiscount, tvAdDetailPrice, tvAdDetailPriceDecimal;
    }

}