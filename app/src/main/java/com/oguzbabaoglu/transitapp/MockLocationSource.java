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

import android.location.Location;

import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.model.LatLng;

/**
 * A LocationSource implementation that always shows the user in Berlin.
 *
 * @author Oguz Babaoglu
 */
public final class MockLocationSource implements LocationSource {

    private static final LatLng BERLIN = new LatLng(52.520007, 13.404954);

    private static final float ACCURACY = 1; // Meters
    private static final int SPEED = 0;

    public Location generateLocation() {

        final Location location = new Location(getClass().getSimpleName());
        location.setTime(System.currentTimeMillis());
        location.setAccuracy(ACCURACY);
        location.setSpeed(SPEED);
        location.setLatitude(BERLIN.latitude);
        location.setLongitude(BERLIN.longitude);

        return location;
    }

    @Override
    public void activate(OnLocationChangedListener locationChangedListener) {
        locationChangedListener.onLocationChanged(generateLocation());
    }

    @Override
    public void deactivate() {
        // Do nothing
    }

}
