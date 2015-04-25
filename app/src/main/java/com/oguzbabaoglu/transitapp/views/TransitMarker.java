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

package com.oguzbabaoglu.transitapp.views;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.oguzbabaoglu.fancymarkers.CustomMarker;

/**
 * @author Oguz Babaoglu
 */
public class TransitMarker extends CustomMarker {

    private LatLng position;

    public TransitMarker(LatLng position) {
        this.position = position;
    }

    @Override
    public void onPrepareMarker(MarkerOptions markerOptions) {
        // Do not set icon for now.
    }

    @Override
    public BitmapDescriptor getBitmapDescriptor() {
        return null;
    }

    @Override
    public LatLng getPosition() {
        return position;
    }
}
