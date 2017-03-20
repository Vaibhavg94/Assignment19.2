package com.vaibhav.assignment192.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vaibhav.assignment192.R;
import com.vaibhav.assignment192.models.DataHandler;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{

    private Context context;
    private List<DataHandler> data;
    private ClickListener clickListener;

    public DataAdapter(Context context, List<DataHandler> data){
        this.context=context;
        this.data=data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row, null);
        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.name.setText(data.get(position).getName());
        holder.order.setText(data.get(position).getOrder()+"");
        holder.character.setText(data.get(position).getCharacter());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListener!=null){
                    clickListener.ItemClicked(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, order, character;
        RelativeLayout parent;

        ViewHolder(View itemView) {
            super(itemView);
            name= (TextView)itemView.findViewById(R.id.name);
            order= (TextView)itemView.findViewById(R.id.order);
            character= (TextView)itemView.findViewById(R.id.character);
            parent= (RelativeLayout)itemView.findViewById(R.id.parent);
        }
    }

    interface ClickListener{
        void ItemClicked(View v, int position);
    }
}
