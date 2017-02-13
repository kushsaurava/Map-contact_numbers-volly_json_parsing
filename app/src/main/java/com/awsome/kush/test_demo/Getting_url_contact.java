package com.awsome.kush.test_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.awsome.kush.test_demo.Interfaces.AsyncTaskCompleteListener;
import com.awsome.kush.test_demo.Networking.VollyGetRewuest;
import com.awsome.kush.test_demo.Util.Commonutils;
import com.awsome.kush.test_demo.model.ContactDATA;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Getting_url_contact extends AppCompatActivity  implements AsyncTaskCompleteListener ,View.OnClickListener {



    ImageView back;
    public static RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    ArrayList<ContactDATA> review_data ;
    LinearLayoutManager lLayout;
    public  static TextView menu_name,noproduct_header,noproduct_footer;
    int reviewdeleteposition;
    private RelativeLayout rootRelativeLayout ,no_product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_url_contact);
        Widgets();


        review_data = new ArrayList<ContactDATA>();
        GetReviewDetailsonline();

    }
    private void Widgets() {

        rootRelativeLayout = (RelativeLayout) findViewById(R.id.activity_manage__reviews);



        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        lLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(lLayout);
    }

     @Override
    public void onClick(View view) {
        switch (view.getId()){
            /*case R.id.back:
                finish();
                break;*/
        }

    }

    private void GetReviewDetailsonline() {


            Commonutils.progressdialog_show(this, "");
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("url", "http://private-b08d8d-nikitest.apiary-mock.com/contacts" );
            Log.i("Data", map.toString());

            new VollyGetRewuest(this, 0, map, 1, this);

    }

    @Override
    public void onTaskCompleted(String response, int serviceCode) {
        switch (serviceCode){
            case 1:

                if (response != null) {
                    try {
                        JSONArray jsonObject = new JSONArray(response);
                        System.out.println("GETALL_REVIEWS inside task "+response);
                            JSONObject json_review_array = jsonObject.getJSONObject(0);
                            JSONArray json_review_contact = json_review_array.getJSONArray("contacts");
                            System.out.println("json_review_array"+json_review_array.length() +"     "+"json_review_contact"+ json_review_contact.length());

                             review_data = new ArrayList<ContactDATA>();
                        System.out.println("json_review_array"+json_review_array.length() +"     "+"json_review_contact"+ json_review_contact.length());

                        for (int i =0; i<json_review_contact.length();i++) {
                            String  name ="Not provided" , email = "Not provided" ,phone = "Not provided" , officePhone ="Not provided" , latitude ="Not provided" , longitude="Not provided" ;

                            try{
                                    JSONObject jsonobject = json_review_contact.getJSONObject(i);
                                     name = jsonobject.getString("name");
                                     phone = jsonobject.getString("phone");
                                     officePhone = jsonobject.getString("officePhone");
                                     latitude = jsonobject.getString("latitude");
                                     longitude = jsonobject.getString("longitude");
                                      email= jsonobject.getString("email");
                            }catch (Exception ee){
                                
                            }
                            System.out.println("clslsf"+i);

                            review_data.add(new ContactDATA(name,email, phone, officePhone, latitude, longitude));
                        }
                        show_Reviewsdata();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
    private void show_Reviewsdata() {
        mAdapter = new ContactAdapter(this,review_data);
        mRecyclerView.setAdapter(mAdapter);
        if(review_data.size()<1) {

            no_product.setVisibility(View.VISIBLE);
            noproduct_header.setText("Please post a review.");
            noproduct_footer.setVisibility(View.GONE);
        }
        return ;
    }
}