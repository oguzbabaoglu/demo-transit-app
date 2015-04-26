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

import java.util.Date;

/**
 * @author Oguz Babaoglu
 */
public final class Stop implements Parcelable {

    @SerializedName("lat")
    private double lat;

    @SerializedName("lng")
    private double lng;

    @SerializedName("datetime")
    private Date dateTime;

    @SerializedName("name")
    private String name;

    // TODO: May not be needed
//    @SerializedName("properties")
//    private Properties properties;

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.lat);
        dest.writeDouble(this.lng);
        dest.writeLong(dateTime != null ? dateTime.getTime() : -1);
        dest.writeString(this.name);
    }

    public Stop() {
    }

    private Stop(Parcel in) {
        this.lat = in.readDouble();
        this.lng = in.readDouble();
        long tmpDateTime = in.readLong();
        this.dateTime = tmpDateTime == -1 ? null : new Date(tmpDateTime);
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Stop> CREATOR = new Parcelable.Creator<Stop>() {
        public Stop createFromParcel(Parcel source) {
            return new Stop(source);
        }

        public Stop[] newArray(int size) {
            return new Stop[size];
        }
    };
}
