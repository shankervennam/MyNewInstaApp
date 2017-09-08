package com.example.cr.mynewinstaapp.geolocation;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.cr.mynewinstaapp.MainActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeoActivity extends AppCompatActivity
{
    private static final int MY_PERMISSION_REQUEST_LOCATION = 1;
    public String locName = "abc";
    MainActivity mainActivity;

    public void getLocation()
    {
        if(ActivityCompat.checkSelfPermission(GeoActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(GeoActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION))
            {
                ActivityCompat.requestPermissions(GeoActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);
            }
            else
            {
                ActivityCompat.requestPermissions(GeoActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);
            }
        }
        else
        {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            try
            {
               // locName.setText(hereLocation(location.getLatitude(), location.getLongitude()));
                //locName = hereLocation(location.getLatitude(), location.getLongitude());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    /*
    Get the City Name
     */
    public String hereLocation(double lat, double lon)
    {
        String curCity ="";
        Geocoder geocoder = new Geocoder(GeoActivity.this, Locale.getDefault());

        List<Address> addressList;

        try
        {
            addressList = geocoder.getFromLocation(lat, lon, 1);
            if (addressList.size() > 0)
            {
                curCity = addressList.get(0).getLocality();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return curCity;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {

        switch (requestCode)
        {
            case MY_PERMISSION_REQUEST_LOCATION:
            {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(GeoActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED)
                    {
                        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        try
                        {
                            locName = hereLocation(location.getLatitude(), location.getLongitude());
                            //mainActivity.getLoc(locName);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        Toast.makeText(this, "No permission granted", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
    public String getData()
    {
        return locName;
    }
}
