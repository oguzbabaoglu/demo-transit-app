/*
 * Copyright (C) 2015 Oguz Babaoglu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.oguzbabaoglu.transitapp;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.oguzbabaoglu.fancymarkers.MarkerManager;

import java.util.ArrayList;

/**
 * @author Oguz Babaoglu
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG_MAP_FRAGMENT = "mainActivity.map";

    // Berlin
    private static final LatLng CENTER = new LatLng(52.520007, 13.404954);
    private static final int ZOOM = 11;

    private static final LatLng[] LOCATIONS = new LatLng[]{
            new LatLng(52.56068229, 13.40632554),
            new LatLng(52.47953663, 13.39603998),
            new LatLng(52.50501533, 13.45857429),
            new LatLng(52.53226326, 13.42090103),
            new LatLng(52.53702663, 13.34097879)
    };

    private MarkerManager<TransitMarker> markerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Only need to create map once
        final SupportMapFragment mapFragment = savedInstanceState == null
                ? createAndAddMap()
                : (SupportMapFragment) getSupportFragmentManager().findFragmentByTag(TAG_MAP_FRAGMENT);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CENTER, ZOOM));
                googleMap.setLocationSource(new MockLocationSource());
                googleMap.setMyLocationEnabled(true);

                markerManager = new MarkerManager<>(googleMap);

                final ArrayList<TransitMarker> markers = new ArrayList<>(LOCATIONS.length);

                for (LatLng latLng : LOCATIONS) {
                    markers.add(new TransitMarker(latLng));
                }

                markerManager.addMarkers(markers);
            }
        });
    }

    /**
     * Creates a Map fragment and adds to view.
     *
     * @return created map fragment
     */
    private SupportMapFragment createAndAddMap() {
        final SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.map_container, mapFragment, TAG_MAP_FRAGMENT);
        transaction.commit();

        return mapFragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
