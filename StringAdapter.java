package com.jjkbashlord.poll_e;

/**
 * Created by JJK on 10/18/16.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by JJK on 7/23/15.
 */
public class StringAdapter extends ArrayAdapter<String> {
    int flag = 0;
    public StringAdapter(Context context, ArrayList<String> statuses) {
        super(context, 0 , statuses);
    }

    public StringAdapter(Context context, ArrayList<String> statuses, int flag) {
        super(context, 0 , statuses);
        this.flag = flag;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String status = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.stringcell, parent, false);
        }else{
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.stringcell, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.etTitle);
        tvName.setText(status);
            // Return the completed view to render on screen
        return convertView;
    }
}
