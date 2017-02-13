package com.awsome.kush.test_demo;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.awsome.kush.test_demo.model.ContactDATA;

import java.util.List;
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder>{

    private List<ContactDATA> reviewdata;
    Getting_url_contact activity;
    ContactDATA productdata;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView comment,product_name,brand_name , office_phone;

        CardView r_cardView;

        public MyViewHolder(final View view) {
            super(view);
            comment = (TextView) view.findViewById(R.id.r_description);
            product_name = (TextView) view.findViewById(R.id.r_title);
            office_phone=(TextView)view.findViewById(R.id.office_phone);
            brand_name=(TextView)view.findViewById(R.id.request_brand_name_display);
            r_cardView=(CardView)view.findViewById(R.id.r_cardView);
        }
    }

    public ContactAdapter(Getting_url_contact activity, List<ContactDATA> reviewdata) {
        System.out.println("pname in size "+reviewdata.size());
        this.reviewdata = reviewdata;
        this.activity=activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_item_layout, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        productdata = reviewdata.get(position);

        holder.comment.setText(productdata.getphone());
        holder.product_name.setText(productdata.getname());
        holder.brand_name.setText(productdata.getemail());
        holder.office_phone.setText(productdata.getofficePhone());

    }

    @Override
    public int getItemCount() {
        return reviewdata.size();
    }
}
