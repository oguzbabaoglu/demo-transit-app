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

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @author Oguz Babaoglu
 */
public final class Segment implements Parcelable {

    @SerializedName("name")
    private String name;

    @SerializedName("num_stops")
    private int numStops;

    @SerializedName("stops")
    private ArrayList<Stop> stops = new ArrayList<>();

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.numStops);
        dest.writeTypedList(this.stops);
        dest.writeString(this.travelMode);
        dest.writeString(this.description);
        dest.writeString(this.color);
        dest.writeString(this.iconUrl);
        dest.writeString(this.polyline);
    }

    public Segment() {
    }

    private Segment(Parcel in) {
        this.name = in.readString();
        this.numStops = in.readInt();
        in.readTypedList(this.stops, Stop.CREATOR);
        this.travelMode = in.readString();
        this.description = in.readString();
        this.color = in.readString();
        this.iconUrl = in.readString();
        this.polyline = in.readString();
    }

    public static final Parcelable.Creator<Segment> CREATOR = new Parcelable.Creator<Segment>() {
        public Segment createFromParcel(Parcel source) {
            return new Segment(source);
        }

        public Segment[] newArray(int size) {
            return new Segment[size];
        }
    };
}
