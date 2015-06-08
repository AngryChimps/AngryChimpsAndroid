package com.angrychimps.appname.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.angrychimps.appname.R;
import com.angrychimps.appname.models.DrawerItem;

import java.util.List;

public class DrawerAdapter extends ArrayAdapter<DrawerItem> {

	private final Activity context;
	private final List<DrawerItem> drawerItemList;
    private final boolean serviceProviderMode;

	public DrawerAdapter(Activity context, List<DrawerItem> listItems, boolean serviceProviderMode) {
		super(context, R.layout.navigation_drawer_item, listItems);
		this.context = context;
		this.drawerItemList = listItems;
        this.serviceProviderMode = serviceProviderMode;
	}

    //ViewHolder is pointless because there is no scrolling, and thus no need to recycle
    @SuppressLint({"CutPasteID","ViewHolder"})
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

        DrawerItem item = drawerItemList.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(item.getLayoutID(), null);

        int color = context.getResources().getColor(serviceProviderMode? R.color.primary_dark : R.color.primary);

        //Get which xml file is used so we know what we can set
        switch(item.getLayoutID()){
            case R.layout.navigation_drawer_profile:
                ImageView imageProfile = (ImageView) v.findViewById(R.id.drawer_profile_image);
                TextView tvName = (TextView) v.findViewById(R.id.drawer_profile_name);
                TextView tvEmail = (TextView) v.findViewById(R.id.drawer_profile_email);
                LinearLayout layout = (LinearLayout) v.findViewById(R.id.drawer_profile_settings);
                imageProfile.setImageResource(item.getImageID());
                tvName.setText(item.getItemName());
                tvEmail.setText(item.getEmail());
                if (item.getImageBackground() != null) {
                    layout.setBackgroundResource(item.getImageBackground());
                }else layout.setBackgroundColor(context.getResources().getColor(R.color.primary));

                break;

            case R.layout.navigation_drawer_item:
                View sideColor = v.findViewById(R.id.drawer_mode_color);
                ImageView icon = (ImageView) v.findViewById(R.id.drawer_icon);
                TextView itemName = (TextView) v.findViewById(R.id.drawer_itemName);
                if(item.isIndented()){
                    sideColor.setBackgroundColor(color);
                    itemName.setTextColor(color);
                    v.setBackgroundColor(context.getResources().getColor(R.color.navigation_drawer_mode_background));
                }else{
                    if(item.isBlue()) itemName.setTextColor(context.getResources().getColor(R.color.primary));
                }
                itemName.setText(item.getItemName());
                icon.setImageResource(item.getImageID());
                break;

            case R.layout.navigation_drawer_messages_item:
                ImageView iconMessage = (ImageView) v.findViewById(R.id.drawer_icon);
                TextView itemNameMessage = (TextView) v.findViewById(R.id.drawer_itemName);
                TextView itemMessage = (TextView) v.findViewById(R.id.drawer_messages);
                iconMessage.setImageResource(item.getImageID());
                itemNameMessage.setTextColor(context.getResources().getColor(R.color.primary));
                itemNameMessage.setText(item.getItemName());
                itemMessage.setText(item.getMessages());
                if(!item.getMessages().equals("0")) itemMessage.setVisibility(View.VISIBLE);
                break;

            case R.layout.navigation_drawer_switch_item:
                View side = v.findViewById(R.id.drawer_mode_color);
                TextView itemModeName = (TextView) v.findViewById(R.id.drawer_itemName);
                SwitchCompat modeSwitch = (SwitchCompat) v.findViewById(R.id.drawer_switch);
                side.setBackgroundColor(color);
                itemModeName.setTextColor(color);
                itemModeName.setText(item.getItemName());
                modeSwitch.setChecked(serviceProviderMode);
                break;
        }
		return v;
	}

}