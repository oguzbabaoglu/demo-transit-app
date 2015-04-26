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
public final class Routes implements Parcelable {

    @SerializedName("routes")
    private ArrayList<Route> routes = new ArrayList<>();

    @SerializedName("provider_attributes")
    private ProviderAttributes providerAttributes;

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public ProviderAttributes getProviderAttributes() {
        return providerAttributes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.routes);
        dest.writeParcelable(this.providerAttributes, flags);
    }

    public Routes() {
    }

    private Routes(Parcel in) {
        in.readTypedList(this.routes, Route.CREATOR);
        this.providerAttributes = in.readParcelable(ProviderAttributes.class.getClassLoader());
    }

    public static final Parcelable.Creator<Routes> CREATOR = new Parcelable.Creator<Routes>() {
        public Routes createFromParcel(Parcel source) {
            return new Routes(source);
        }

        public Routes[] newArray(int size) {
            return new Routes[size];
        }
    };
}
