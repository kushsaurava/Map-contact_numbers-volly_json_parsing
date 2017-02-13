package com.awsome.kush.test_demo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.awsome.kush.test_demo.model.ContactModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContaclistAdapter extends BaseAdapter {
    public List<ContactModel> _data;
    private ArrayList<ContactModel> arraylist;
    Context _c;
    Activity activity;
    ViewHolder v;
    Phone_contact invite_friends;

    public ContaclistAdapter(List<ContactModel> selectUsers, Phone_contact invite_friends) {
        _data = selectUsers;
        _c = (Context) invite_friends;
        this.arraylist = new ArrayList<ContactModel>();
        this.arraylist.addAll(_data);
        this.invite_friends = invite_friends;
    }

    @Override
    public int getCount() {
        return _data.size();
    }

    @Override
    public Object getItem(int i) {
        return _data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            LayoutInflater li = (LayoutInflater)_c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.contact_row, null);
            Log.e("Inside", "here--------------------------- In view1");
        } else {
            view = convertView;
            Log.e("Inside", "here--------------------------- In view2");
        }

        v = new ViewHolder();

        v.title = (TextView) view.findViewById(R.id.name);
        v.phone = (TextView) view.findViewById(R.id.no);
        v.imageView = (CircleImageView) view.findViewById(R.id.pic);



        final ContactModel data = (ContactModel) _data.get(i);
        try{

            if(data.getName().equals(data.getPhone())){
                v.title.setText("");
            }else{
                v.title.setText(data.getName());
                System.out.println("nameeee"+data.getName());
            }
        }catch (Exception e){
            System.out.println("Exception"+e);
        }
        // v.title.setText(data.getName());

        try{

            if(data.getPhone()!=null){
                v.phone.setText(data.getPhone());
            }else{
                v.phone.setText(data.getPhone());
            }
        }catch (Exception e){
            System.out.println("Exception"+e);
        }
        try {

            if (data.getThumb() != null) {
                v.imageView.setImageBitmap(data.getThumb());
            } else {
               // v.imageView.setImageResource(R.mipmap.about_us);
            }
            //Settings round image
            //Bitmap bm = BitmapFactory.decodeResource(view.getResources(), R.drawable.com_facebook_profile_picture_blank_portrait); // Load default image
            // roundedImage = new RoundImage(bm);
            // v.imageView.setImageDrawable(roundedImage);
        } catch (OutOfMemoryError e) {
            // Add default picture
            // v.imageView.setImageDrawable(this._c.getDrawable(R.drawable.image));
            e.printStackTrace();
        }

        Log.e("Image Thumb", "--------------" + data.getThumb());

        view.setTag(data);
        return view;
    }

    public int returnPos(int pos)
    {
        return pos;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        _data.clear();
        if (charText.length() == 0) {
            _data.addAll(arraylist);
        } else {
            for (ContactModel wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    _data.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
    static class ViewHolder {

        CircleImageView imageView;
        TextView title, phone;
    }
}