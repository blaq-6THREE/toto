package com.blaq.hylton.toto.data;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.blaq.hylton.toto.R;

import java.util.ArrayList;

public class UserList extends ArrayAdapter
{
    private ArrayList<User> mUserArrayList;
    private Activity mActivity;

    public UserList(Activity activity, ArrayList<User> mUserArrayList)
    {
        super(activity, R.layout.list_item, mUserArrayList);
        this.mActivity = activity;
        this.mUserArrayList = mUserArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        User user = mUserArrayList.get(position);

        if (listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView todoTitleTextView = listItemView.findViewById(R.id.list_item_todo_title);
        todoTitleTextView.setText(user.getName());

        TextView todoDescTextView = listItemView.findViewById(R.id.list_item_todo_time);
        todoDescTextView.setText(user.getEmail());


        return listItemView;
    }
}
