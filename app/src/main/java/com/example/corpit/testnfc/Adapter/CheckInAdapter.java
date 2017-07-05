package com.example.corpit.testnfc.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.corpit.testnfc.DataManager.CustomerDataManager;
import com.example.corpit.testnfc.Model.CheckIn;
import com.example.corpit.testnfc.Model.Customer;
import com.example.corpit.testnfc.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by corpit on 4/7/2017.
 */

public class CheckInAdapter extends BaseAdapter {
    private Context context;
    private List<CheckIn> data;
    private SQLiteDatabase db;

    public CheckInAdapter(Context context, List<CheckIn> data, SQLiteDatabase db) {
        this.context = context;
        this.data = data;
        this.db = db;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_check_in_list, parent, false);
            holder.tv_check_in_item_name = (TextView) convertView.findViewById(R.id.tv_check_in_item_name);
            holder.tv_check_in_item_time = (TextView) convertView.findViewById(R.id.tv_check_in_item_time);
            String name = getName(db, data.get(position));
            holder.tv_check_in_item_name.setText(name);
            holder.tv_check_in_item_time.setText(data.get(position).checkInTime);
            convertView.setTag(holder);
            //对于listview，注意添加这一行，即可在item上使用高度
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    class ViewHolder {
        TextView tv_check_in_item_name;
        TextView tv_check_in_item_time;
    }

    private String getName(SQLiteDatabase db, CheckIn c) {
        Customer customer = new Customer();
        String name = new String("");
        List<Customer> customerList = CustomerDataManager.getCustomer(db, c.NFCNumber);
        if (customerList.size() > 0) {
            customer = customerList.get(0);
            name = customer.name;
            return name;
        } else {
            return name;
        }
    }
}
