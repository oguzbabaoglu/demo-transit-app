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

package com.oguzbabaoglu.transitapp.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @author Oguz Babaoglu
 */
public final class Segment {

    @SerializedName("name")
    private String name;

    @SerializedName("num_stops")
    private int numStops;

    @SerializedName("stops")
    private ArrayList<Stop> stops;

    @SerializedName("travel_mode")
    private String travelMode;

    @SerializedName("description")
    private String description;

    // TODO: De-Serialize this into an int.
    @SerializedName("color")
    private String color;

    @SerializedName("icon_url")
    private String iconUrl;

    @SerializedName("polyline")
    private String polyline;

    public String getName() {
        return name;
    }

    public int getNumStops() {
        return numStops;
    }

    public ArrayList<Stop> getStops() {
        return stops;
    }

    public String getTravelMode() {
        return travelMode;
    }

    public String getDescription() {
        return description;
    }

    public String getColor() {
        return color;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getPolyline() {
        return polyline;
    }
}
