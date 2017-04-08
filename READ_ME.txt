Hi All,

    This is a demo of reading contact , getting api data using volly library showing it on recycler view , and call them on the google maps
	so don't forget to add these permission to your menifest file
	
	<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.GET_ACCOUNTS" />
    <uses-permission android:name="android.permission.USE_CREDENTIALS" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.READ_CONTACTS" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.CALL_PHONE" />

    <!-- Required to show current location -->
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
	
	
	
	
	And add these file on your gradle 
	
    compile 'com.android.volley:volley:1.0.0'
    compile 'com.google.android.gms:play-services:6.5.87'
    compile 'com.android.support:recyclerview-v7:24.1.1'
    compile 'com.android.support:cardview-v7:24.1.1'
    compile 'com.android.support:design:24.1.1'
	
	manage the versions according the vesrion you are using.
	
	
	
	
	And one more major thing is to register your app on  google developer console 
	
	https://console.developers.google.com
	
	and activate google map api  and use the api key on the menifest file on the.
	
	
	Cheers..