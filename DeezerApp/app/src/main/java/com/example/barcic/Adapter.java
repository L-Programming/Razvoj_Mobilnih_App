package com.example.barcic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {


    private Context context;
    private List<ListItem> listItems;
    private OnItemClickListener mListener;




    public interface OnItemClickListener{
        void onItemClick(int position);
    }



    public void setOnItemClickListener (OnItemClickListener listener){
        mListener = listener;
    }



    public Adapter (List<ListItem> listItems,Context context){

        this.listItems = listItems;
        this.context = context;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, final int position) {

        final ListItem listItem = listItems.get(position);

        holder.myText1.setText(listItem.getHead());
        holder.myText2.setText(listItem.getDesc());

        Picasso.with(context).load(listItem.getImgUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return listItems.size() ;
    }

    public  class  MyViewHolder extends RecyclerView.ViewHolder {

        public TextView myText1, myText2;
        public ImageView imageView;



        public MyViewHolder (@NonNull View itemView){
            super(itemView);
            myText1 = itemView.findViewById(R.id.myText1);
            myText2 = itemView.findViewById(R.id.myText2);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
