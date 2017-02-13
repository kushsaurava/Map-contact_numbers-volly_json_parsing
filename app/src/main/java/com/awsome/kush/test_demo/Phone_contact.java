package com.awsome.kush.test_demo;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.awsome.kush.test_demo.model.ContactModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Phone_contact extends AppCompatActivity {


    //  View view;
    String name, phoneNumber;
    ArrayList<ContactModel> selectUsers;
    List<String> AllContactlist = new ArrayList<String>();
    // Contact List
    ListView listView;
    // Cursor to load contacts list
    Cursor phones, email;

    String contactnumber;
    // Pop up
    ContentResolver resolver;
    SearchView search;
    ContaclistAdapter adapter;
    TextView InviteAll;
    // ArrayList<String> AllContacts;
    ArrayList<String> arryList = new ArrayList<String>();
    ArrayList<String> arryList1 = new ArrayList<String>();
    ImageView done, back;
    public static TextView menu_name;
    private RelativeLayout rootRelativeLayout;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_contact);


        selectUsers = new ArrayList<ContactModel>();
        resolver = this.getContentResolver();

        rootRelativeLayout = (RelativeLayout) findViewById(R.id.activity_url_contact);


        listView = (ListView) findViewById(R.id.contacts_list);


        phones = this.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        LoadContact loadContact = new LoadContact();
        loadContact.execute();
    }
    public void onTaskCompleted(String response, int serviceCode) {


        switch (serviceCode) {


        }
    }



    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    // Load data on background
    class LoadContact extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Get Contact list from Phone

            if (phones != null) {
                Log.e("count", "" + phones.getCount());
                try {
                    while (phones.moveToNext()) {
                        Bitmap bit_thumb = null;
                        String id = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                        String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        String EmailAddr = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA2));
                        String image_thumb = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_ID));


                        try {
                            if (image_thumb != null) {
                                bit_thumb = MediaStore.Images.Media.getBitmap(resolver, Uri.parse(image_thumb));
                            } else {
                                Log.e("No Image Thumb", "--------------");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        ContactModel selectUser = new ContactModel();
                        selectUser.setThumb(bit_thumb);
                        selectUser.setName(name);
                        selectUser.setPhone(phoneNumber);
                        selectUser.setEmail(id);
                        selectUser.setCheckedBox(false);
                        selectUsers.add(selectUser);
                    }
                } catch (Exception e) {
                    System.out.println("Exception invite friend" + e);
                }

            } else {
                Log.e("Cursor close 1", "----------------");
                Toast.makeText(getApplicationContext(), "No contacts in your contact list.", Toast.LENGTH_LONG).show();
            }

            //phones.close();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            System.out.println("ArrayList contains: " + selectUsers);
            LinkedHashSet<String> lhs = new LinkedHashSet<String>();
            System.out.println("After:");
            adapter = new ContaclistAdapter(selectUsers, Phone_contact.this);
            listView.setAdapter(adapter);



            // listView.setFastScrollEnabled(true);


        }
    }







    @Override
    public void onStop() {
        super.onStop();
        phones.close();

    }

}
