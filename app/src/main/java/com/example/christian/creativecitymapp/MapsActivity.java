package com.example.christian.creativecitymapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, ToolbarsFragment.OnFragmentInteractionListener{

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        Marker marker_1;

        TextView text1 = (TextView)findViewById(R.id.Timetable);

        LatLng manchester = new LatLng(53.483959, -2.244644);
        LatLng manchester2 = new LatLng(53.485979, -2.244634);

        Drawable myDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.pin2, null);
        Bitmap markerBitmap = ((BitmapDrawable)myDrawable).getBitmap();

        Marker m = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(markerBitmap)).position(manchester2).title("Dance Performance - CHORLTON VILLAGE SQR."));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(markerBitmap)).position(manchester).title("MacBeth - EDGE THEATER"));

        float zoomLevel = 15.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(manchester, zoomLevel));

        FragmentManager manager = getFragmentManager();

        ToolbarsFragment fragtools = new ToolbarsFragment();
        manager.beginTransaction()
                .replace(R.id.toolbarLayout, fragtools, fragtools.getTag())
                .commit();


        onMarkerClick(m);



    }

    /**
     * handle marker click event
     */

    public void onMarkerClick(Marker marker) {
            Log.w("Click", "test");
    }

    public void showHideFragment(final Fragment fragment){

        FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
        fragTransaction.setCustomAnimations(android.R.animator.fade_in,
                android.R.animator.fade_out);

        if (fragment.isHidden()) {
            fragTransaction.show(fragment);
            Log.d("hidden","Show");
        } else {
            fragTransaction.hide(fragment);
            Log.d("Shown","Hide");
        }

        fragTransaction.commit();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
