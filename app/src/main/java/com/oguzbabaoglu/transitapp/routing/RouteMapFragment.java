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

package com.oguzbabaoglu.transitapp.routing;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolylineOptions;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.oguzbabaoglu.transitapp.R;
import com.oguzbabaoglu.transitapp.core.BaseMapFragment;

import java.util.List;

import butterknife.InjectView;

/**
 * Map view for routes.
 * Contains a map and swipeable route details.
 *
 * @author Oguz Babaoglu
 */
public class RouteMapFragment extends BaseMapFragment {

    @Arg
    RouteListModel routeListModel;

    @Arg
    int routeIndex;

    @InjectView(R.id.route_map_name_text)
    TextView nameText;

    @InjectView(R.id.route_map_price_text)
    TextView priceText;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_route_map;
    }

    @Override
    protected int getMapContainerId() {
        return R.id.route_map_container;
    }

    @Override
    public void onPrepareView(LayoutInflater inflater, View rootView, Bundle savedInstanceState) {
        super.onPrepareView(inflater, rootView, savedInstanceState);

        RouteModel route = routeListModel.getRouteModels().get(routeIndex);
        StringBuilder routeNameBuilder = new StringBuilder();

        for (SegmentModel segment : route.getSegments()) {
            routeNameBuilder.append(segment.getName());
            routeNameBuilder.append(" ");
        }

        nameText.setText(routeNameBuilder.toString());
        priceText.setText(route.getPriceText() + " - " + route.getTotalTimeText());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        super.onMapReady(googleMap);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int mapWidth = displaymetrics.widthPixels;
        int mapHeight = getResources().getDimensionPixelSize(R.dimen.routes_map_height);
        int mapPadding = getResources().getDimensionPixelSize(R.dimen.routes_map_padding);

        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();

        float polyWidth = getResources().getDimension(R.dimen.routes_map_polyline_width);
        RouteModel route = routeListModel.getRouteModels().get(routeIndex);

        for (SegmentModel segment : route.getSegments()) {

            List<LatLng> path = segment.getPath();

            if (path.isEmpty()) {
                continue;
            }

            for (LatLng point : path) {
                boundsBuilder.include(point);
            }

            PolylineOptions polylineOptions = new PolylineOptions()
                    .addAll(segment.getPath())
                    .color(segment.getColor())
                    .width(polyWidth);
            googleMap.addPolyline(polylineOptions);
        }

        // Make sure map projection includes route
        googleMap.moveCamera(CameraUpdateFactory
                .newLatLngBounds(boundsBuilder.build(), mapWidth, mapHeight, mapPadding));
    }
}
