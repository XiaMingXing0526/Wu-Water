package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ObjectAdapter extends ArrayAdapter<Object> {
    private  int resourceId;
        public ObjectAdapter(Context context, int textViewResourceId, List<Object> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Object object=getItem(position);
        View view;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }
        else {
            view=convertView;
        }
        ImageView fruitImage=(ImageView) view.findViewById(R.id.object_image);
        TextView fruitName=(TextView) view.findViewById(R.id.object_name);
        fruitImage.setImageResource(object.getImageId());
        fruitName.setText(object.getName());
        return view;
    }
}
