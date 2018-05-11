package com.example.christian.creativecitymapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, ToolbarsFragment.OnFragmentInteractionListener, DefaultFragment.OnFragmentInteractionListener{

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

        TextView text1 = (TextView)findViewById(R.id.Timetable);

        LatLng manchester = new LatLng(53.483959, -2.244644);
        LatLng manchester2 = new LatLng(53.485979, -2.244634);

        Drawable myDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.pins, null);
        Bitmap markerBitmap = ((BitmapDrawable)myDrawable).getBitmap();

        Marker MDance = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(markerBitmap)).position(manchester2).title("Dance Performance - CHORLTON VILLAGE SQR."));
        Marker MTheater = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(markerBitmap)).position(manchester).title("MacBeth - EDGE THEATER"));

        float zoomLevel = 15.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(manchester, zoomLevel));

        final FragmentManager manager = getFragmentManager();

        final ToolbarsFragment fragtools = new ToolbarsFragment();
        final DefaultFragment fragdefault = new DefaultFragment();

        manager.beginTransaction()
                .replace(R.id.toolbarLayout, fragdefault, fragdefault.getTag())
                .commit();

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override

            public boolean onMarkerClick(Marker marker) {

                Bundle toolbarbundle = new Bundle();

                if (marker.getTitle().equals("Dance Performance - CHORLTON VILLAGE SQR.")) {
                    toolbarbundle.putString("PLACE", "Chorlton Village Sqr.");
                    toolbarbundle.putString("WHATS", "Dance Performance");
                    toolbarbundle.putString("TIMES", "19/05 - 11:00PM");

                }
                if (marker.getTitle().equals("MacBeth - EDGE THEATER")) {
                    toolbarbundle.putString("PLACE", "Edge Theater");
                    toolbarbundle.putString("WHATS", "MacBeth Performance");
                    toolbarbundle.putString("TIMES", "13/05 - 9:00PM");

                }

                fragtools.setArguments(toolbarbundle);

                transactFragment(fragtools,true);

                return false;
            }
        });

    }

    /**
     * handle marker click event
     */

    public void transactFragment(Fragment fragment, boolean reload) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        if (reload) {
            getSupportFragmentManager().popBackStack();
        }
        transaction.replace(R.id.toolbarLayout, fragment);
        transaction.addToBackStack(null);
        transaction.detach(fragment).attach(fragment).commit();
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

    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.inform:
                Intent acerca = new Intent(MapsActivity.this, InfoActivity.class);
                startActivity(acerca);
                return true;
            case R.id.about:
                Intent pedido = new Intent(MapsActivity.this, AboutActivity.class);
                startActivity(pedido);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void onFragmentInteractions(Uri uri) {

    }
}
