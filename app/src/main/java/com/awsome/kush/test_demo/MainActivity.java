package com.awsome.kush.test_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    TextView read_contact,url_contact ,contacts_Map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        widget();

    }

    private void widget() {
        read_contact =(TextView)findViewById(R.id.read_contact);
        url_contact =(TextView)findViewById(R.id.url_contact);
        contacts_Map =(TextView)findViewById(R.id.contacts_Map);

        read_contact.setOnClickListener(this);
        url_contact.setOnClickListener(this);
        contacts_Map.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.read_contact:
                Intent rc = new Intent(MainActivity.this  , Phone_contact.class);
                startActivity(rc);
                break;
            case R.id.url_contact:
                Intent rct = new Intent(MainActivity.this  , Getting_url_contact.class);
                startActivity(rct);
                break;

            case R.id.contacts_Map:
                Intent rctm = new Intent(MainActivity.this  , Contacts_Map.class);
                startActivity(rctm);
                break;
        }
    }
}
