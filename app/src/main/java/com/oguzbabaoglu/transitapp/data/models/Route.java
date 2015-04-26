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
public final class Route implements Parcelable {

    @SerializedName("type")
    private String type;

    @SerializedName("provider")
    private String provider;

    @SerializedName("segments")
    private ArrayList<Segment> segments = new ArrayList<>();

    // TODO: need specific properties for each type
//    @SerializedName("properties")
//    private Properties properties;

    @SerializedName("price")
    private Price price;

    public String getType() {
        return type;
    }

    public String getProvider() {
        return provider;
    }

    public ArrayList<Segment> getSegments() {
        return segments;
    }

    public Price getPrice() {
        return price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.provider);
        dest.writeTypedList(this.segments);
        dest.writeParcelable(this.price, flags);
    }

    public Route() {
    }

    private Route(Parcel in) {
        this.type = in.readString();
        this.provider = in.readString();
        in.readTypedList(this.segments, Segment.CREATOR);
        this.price = in.readParcelable(Price.class.getClassLoader());
    }

    public static final Parcelable.Creator<Route> CREATOR = new Parcelable.Creator<Route>() {
        public Route createFromParcel(Parcel source) {
            return new Route(source);
        }

        public Route[] newArray(int size) {
            return new Route[size];
        }
    };
}
