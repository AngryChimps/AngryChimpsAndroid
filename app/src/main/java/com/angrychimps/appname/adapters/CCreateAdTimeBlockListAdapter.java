package com.angrychimps.appname.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.angrychimps.appname.R;
import com.angrychimps.appname.models.Availabilities;

import java.util.List;

/*
    This adapter handles the time blocks added in the Company Create Ad screen
 */

public class CCreateAdTimeBlockListAdapter extends ArrayAdapter<Availabilities>{

    private final LayoutInflater inflater;

    public CCreateAdTimeBlockListAdapter(Context context, List<Availabilities> list) {
        super(context, android.R.layout.simple_list_item_1, list);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;

        // Reuse views
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.card_time_block, parent, false);

            // Configure ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.bRemoveTimeBlock = (ImageButton) convertView.findViewById(R.id.bRemoveTimeBlock);
            viewHolder.tvAvailableTimeBlocks = (TextView) convertView.findViewById(R.id.tvAvailableTimeBlocks);
            viewHolder.bStartDate = (Button) convertView.findViewById(R.id.bStartDate);
            viewHolder.bStartTime = (Button) convertView.findViewById(R.id.bStartTime);
            viewHolder.bEndDate = (Button) convertView.findViewById(R.id.bEndDate);
            viewHolder.bEndTime = (Button) convertView.findViewById(R.id.bEndTime);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(position == 0){
            viewHolder.bRemoveTimeBlock.setVisibility(View.GONE);
            viewHolder.tvAvailableTimeBlocks.setVisibility(View.VISIBLE);
        }else{
            viewHolder.bRemoveTimeBlock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        return convertView;
    }

    // ViewHolder increases speed and efficiency by recycling views rather than doing many findViewByIds, which are expensive.
    static class ViewHolder {
        TextView tvAvailableTimeBlocks;
        Button bStartDate, bStartTime, bEndDate, bEndTime;
        ImageButton bRemoveTimeBlock;
    }


}
