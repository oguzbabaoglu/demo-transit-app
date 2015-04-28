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

package com.oguzbabaoglu.transitapp.core;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.oguzbabaoglu.fancymarkers.MarkerManager;
import com.oguzbabaoglu.transitapp.util.MockLocationSource;
import com.oguzbabaoglu.transitapp.views.TransitMarker;

/**
 * Base for map fragments.
 *
 * @author Oguz Babaoglu
 */
public abstract class BaseMapFragment<Controller extends BaseController>
        extends BaseFragment<Controller>
        implements OnMapReadyCallback {

    private static final String TAG_MAP_FRAGMENT = "fragment.map";

    private MarkerManager<TransitMarker> markerManager;

    @Override
    public void onPrepareView(LayoutInflater inflater, View rootView, Bundle savedInstanceState) {

        // Only need to create map once
        final SupportMapFragment mapFragment = savedInstanceState == null
                ? createAndAddMap(getMapContainerId(), TAG_MAP_FRAGMENT)
                : (SupportMapFragment) getChildFragmentManager().findFragmentByTag(TAG_MAP_FRAGMENT);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.setLocationSource(new MockLocationSource());
        googleMap.setMyLocationEnabled(true);

        markerManager = new MarkerManager<>(googleMap);
    }

    /**
     * @return container id to place map
     */
    protected abstract int getMapContainerId();

    public MarkerManager<TransitMarker> getMarkerManager() {
        return markerManager;
    }

    /**
     * Creates a Map fragment and adds to view.
     *
     * @return created map fragment
     */
    protected SupportMapFragment createAndAddMap(int containerId, String tag) {
        final SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        final FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(containerId, mapFragment, tag);
        transaction.commit();

        return mapFragment;
    }

}
