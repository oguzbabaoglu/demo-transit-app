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

package com.oguzbabaoglu.transitapp.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.oguzbabaoglu.transitapp.R;
import com.oguzbabaoglu.transitapp.core.BaseMapFragment;
import com.oguzbabaoglu.transitapp.util.MockLocationSource;
import com.oguzbabaoglu.transitapp.views.TransitMarker;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * First view presented to the user.
 * Consists mainly of a map for initiating route finding.
 *
 * @author Oguz Babaoglu
 */
public class HomeFragment extends BaseMapFragment<HomeController> implements View.OnClickListener {

    private static final int ZOOM = 11;

    // Some mocked locations for initial map markers
    private static final LatLng[] LOCATIONS = new LatLng[]{
            new LatLng(52.56068229, 13.40632554),
            new LatLng(52.47953663, 13.39603998),
            new LatLng(52.50501533, 13.45857429),
            new LatLng(52.53226326, 13.42090103),
            new LatLng(52.53702663, 13.34097879)
    };

    /**
     * @return new fragment instance.
     */
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @InjectView(R.id.home_button_go)
    View buttonGo;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onPrepareView(LayoutInflater inflater, View rootView, Bundle savedInstanceState) {
        super.onPrepareView(inflater, rootView, savedInstanceState);

        buttonGo.setOnClickListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        super.onMapReady(googleMap);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MockLocationSource.BERLIN, ZOOM));

        final ArrayList<TransitMarker> markers = new ArrayList<>(LOCATIONS.length);

        for (LatLng latLng : LOCATIONS) {
            markers.add(new TransitMarker(latLng));
        }

        getMarkerManager().addMarkers(markers);
    }

    @Override
    protected int getMapContainerId() {
        return R.id.home_map_container;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.home_button_go:
                getController().onRouteRequested();
                break;

            default:
                break;
        }

    }
}
