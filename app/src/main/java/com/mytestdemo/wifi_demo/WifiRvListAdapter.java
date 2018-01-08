package com.mytestdemo.wifi_demo;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.mytestdemo.R;

import java.util.List;

/**
 * @author cuishuxiang
 * @date 2017/12/21.
 *
 * wifi 列表adapter
 */

public class WifiRvListAdapter extends RecyclerView.Adapter<WifiRvListAdapter.ViewHolder> {

    private Context context;
    private List<WifiInfoBean> wifiInfoBeanList;
    private WifiInfo connectedWifiInfo;

    public void setConnectWifiInfo(WifiInfo wifiInfo) {
        this.connectedWifiInfo = wifiInfo;
        notifyDataSetChanged();
    }

    public WifiRvListAdapter(Context context, List<WifiInfoBean> wifiInfoBeanList) {
        this.context = context;
        this.wifiInfoBeanList = wifiInfoBeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        View view = null;
        if (view == null || viewHolder == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_wifi_list, parent, false);
            viewHolder = new ViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ScanResult scanResult = wifiInfoBeanList.get(position).getScanResult();
        int security_type = wifiInfoBeanList.get(position).getSecurityType();
        int level = wifiInfoBeanList.get(position).getLevel();

        holder.wifi_name.setText(scanResult.SSID);

        //首先判断加密方式   0 为没有密码
        if (0 == security_type) {
            switch (level) {
                case 0:
                    holder.wifi_img.setImageResource(R.drawable.ic_wifi_1);
                    break;
                case 1:
                    holder.wifi_img.setImageResource(R.drawable.ic_wifi_1);
                    break;
                case 2:
                    holder.wifi_img.setImageResource(R.drawable.ic_wifi_2);
                    break;
                case 3:
                    holder.wifi_img.setImageResource(R.drawable.ic_wifi_3);
                    break;
                case 4:
                    holder.wifi_img.setImageResource(R.drawable.ic_wifi_4);
                    break;
            }
        }

        //有密码的情况
        if (0 != security_type) {
            switch (level) {
                case 0:
                    holder.wifi_img.setImageResource(R.drawable.ic_wifi_lock0);
                    break;
                case 1:
                    holder.wifi_img.setImageResource(R.drawable.ic_wifi_lock1);
                    break;
                case 2:
                    holder.wifi_img.setImageResource(R.drawable.ic_wifi_lock2);
                    break;
                case 3:
                    holder.wifi_img.setImageResource(R.drawable.ic_wifi_lock3);
                    break;
                case 4:
                    holder.wifi_img.setImageResource(R.drawable.ic_wifi_lock3);
                    break;
            }
        }

        if (connectedWifiInfo != null && connectedWifiInfo.getSSID().equals("\""+scanResult.SSID+"\"")) {
            holder.connect_state.setText("已连接");
        }else {
            holder.connect_state.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return wifiInfoBeanList == null ? 0 : wifiInfoBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView wifi_img;
        TextView wifi_name,connect_state;
        public ViewHolder(View itemView) {
            super(itemView);
            wifi_img = (ImageView) itemView.findViewById(R.id.wifi_img);
            wifi_name = (TextView) itemView.findViewById(R.id.wifi_name);
            connect_state= (TextView) itemView.findViewById(R.id.connect_state);
            if (onRvItemClickListener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onRvItemClickListener.onItemClickListener(v, getAdapterPosition());
                    }
                });

                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        onRvItemClickListener.onLongItemClickListener(view, getAdapterPosition());
                        return false;
                    }
                });
            }

        }
    }

    private OnRvItemClickListener onRvItemClickListener;

    public void setOnRvItemClickListener(OnRvItemClickListener onRvItemClickListener) {
        this.onRvItemClickListener = onRvItemClickListener;
    }

    public interface OnRvItemClickListener {

        void onItemClickListener(View view, int position);

        void onLongItemClickListener(View view, int position);

    }
}

