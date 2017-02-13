package com.awsome.kush.test_demo;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.awsome.kush.test_demo.Interfaces.AsyncTaskCompleteListener;
import com.awsome.kush.test_demo.Networking.VollyGetRewuest;
import com.awsome.kush.test_demo.Util.Commonutils;
import com.awsome.kush.test_demo.model.ContactDATA;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Contacts_Map extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapLongClickListener,AsyncTaskCompleteListener,
        View.OnClickListener{



    //Our Map
    private GoogleMap mMap;
    //To store longitude and latitude from map
    private double longitude;
    private double latitude;

    //Buttons
    private ImageButton buttonCurrent;

    ArrayList<ContactDATA> review_data ;


    //Google ApiClient
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts__map);


        review_data = new ArrayList<ContactDATA>();
        GetReviewDetailsonline();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        //Initializing googleapi client
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        //Initializing views and adding onclick listeners

        buttonCurrent = (ImageButton) findViewById(R.id.buttonCurrent);


        buttonCurrent.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    //Getting current location
    private void getCurrentLocation() {
        mMap.clear();
        //Creating a location object
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (location != null) {
            //Getting longitude and latitude
            longitude = location.getLongitude();
            latitude = location.getLatitude();

            //moving the map to location
            moveMap(longitude ,latitude , "Your curent location ", "your_mail@id.com" , "your number" , "your_office_numbe");
        }
    }


    private void GetReviewDetailsonline() {

            Commonutils.progressdialog_show(this, "");
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("url", "http://private-b08d8d-nikitest.apiary-mock.com/contacts" );
            Log.i("Data", map.toString());

            new VollyGetRewuest(this, 0, map, 1, this);

    }

    //Function to move the map
    private void moveMap( double longitude,
             double latitude, String name , String email , String phone , String officephone) {
        //String to display current latitude and longitude
        String msg = latitude + ", "+longitude;

        //Creating a LatLng Object to store Coordinates
        LatLng latLng = new LatLng(latitude, longitude);

        //Adding marker to map
        mMap.addMarker(new MarkerOptions()
                .position(latLng) //setting position
                .draggable(true) //Making the marker draggable
                .title(name +", "+email  +", " +"phone :-"+phone +", Office phone:-"+officephone)); //Adding a title

        //Moving the camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        //Animating the camera
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        //Displaying current coordinates in toast
        //Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(latLng).draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.setOnMarkerDragListener(this);
        mMap.setOnMapLongClickListener(this);
    }

    @Override
    public void onConnected(Bundle bundle) {
        getCurrentLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        //Clearing all the markers
        mMap.clear();

        //Adding a new marker to the current pressed position
        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .draggable(true));
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        //Getting the coordinates
        latitude = marker.getPosition().latitude;
        longitude = marker.getPosition().longitude;

        //Moving the map
        moveMap(longitude ,latitude, "Your curent location ", "your_mail@id.com" , "your number" , "your_office_numbe");
    }

    @Override
    public void onClick(View v) {
        if(v == buttonCurrent){
            getCurrentLocation();
            moveMap(longitude , latitude, "Your curent location ", "your_mail@id.com" , "your number" , "your_office_numbe");
        }
    }

    @Override
    public void onTaskCompleted(String response, int serviceCode) {


        switch (serviceCode) {
            case 1:

                if (response != null) {
                    try {
                        JSONArray jsonObject = new JSONArray(response);
                        System.out.println("GETALL_REVIEWS inside task " + response);
                        JSONObject json_review_array = jsonObject.getJSONObject(0);
                        JSONArray json_review_contact = json_review_array.getJSONArray("contacts");
                        System.out.println("json_review_array" + json_review_array.length() + "     " + "json_review_contact" + json_review_contact.length());

                        review_data = new ArrayList<ContactDATA>();
                        System.out.println("json_review_array" + json_review_array.length() + "     " + "json_review_contact" + json_review_contact.length());

                        for (int i = 0; i < json_review_contact.length(); i++) {
                            String name = "Not provided", email = "Not provided", phone = "Not provided", officePhone = "Not provided", latitude = "Not provided", longitude = "Not provided";

                            try {
                                JSONObject jsonobject = json_review_contact.getJSONObject(i);
                                name = jsonobject.getString("name");
                                phone = jsonobject.getString("phone");
                                officePhone = jsonobject.getString("officePhone");
                                latitude = jsonobject.getString("latitude");
                                longitude = jsonobject.getString("longitude");
                                email = jsonobject.getString("email");
                            } catch (Exception ee) {

                            }
                            System.out.println("clslsf" + i);

                            review_data.add(new ContactDATA(name, email, phone, officePhone, latitude, longitude));

                            moveMap(Double.parseDouble(longitude) , Double.parseDouble(latitude) , name , email , phone ,officePhone);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;

        }
    }}