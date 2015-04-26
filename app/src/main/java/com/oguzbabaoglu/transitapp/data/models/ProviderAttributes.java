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

/**
 * If providers are expected to change, a {@link com.google.gson.JsonDeserializer}
 * can be implemented to handle generic Providers with "name" as an additional attribute.
 *
 * @author Oguz Babaoglu
 */
public final class ProviderAttributes implements Parcelable {

    @SerializedName("vbb")
    private Provider vbb;

    @SerializedName("drivenow")
    private Provider drivenow;

    @SerializedName("car2go")
    private Provider car2go;

    @SerializedName("google")
    private Provider google;

    @SerializedName("nextbike")
    private Provider nextbike;

    @SerializedName("callabike")
    private Provider callabike;

    public Provider getVbb() {
        return vbb;
    }

    public Provider getDrivenow() {
        return drivenow;
    }

    public Provider getCar2go() {
        return car2go;
    }

    public Provider getGoogle() {
        return google;
    }

    public Provider getNextbike() {
        return nextbike;
    }

    public Provider getCallabike() {
        return callabike;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.vbb, flags);
        dest.writeParcelable(this.drivenow, flags);
        dest.writeParcelable(this.car2go, flags);
        dest.writeParcelable(this.google, flags);
        dest.writeParcelable(this.nextbike, flags);
        dest.writeParcelable(this.callabike, flags);
    }

    public ProviderAttributes() {
    }

    private ProviderAttributes(Parcel in) {
        this.vbb = in.readParcelable(Provider.class.getClassLoader());
        this.drivenow = in.readParcelable(Provider.class.getClassLoader());
        this.car2go = in.readParcelable(Provider.class.getClassLoader());
        this.google = in.readParcelable(Provider.class.getClassLoader());
        this.nextbike = in.readParcelable(Provider.class.getClassLoader());
        this.callabike = in.readParcelable(Provider.class.getClassLoader());
    }

    public static final Parcelable.Creator<ProviderAttributes> CREATOR = new Parcelable.Creator<ProviderAttributes>() {
        public ProviderAttributes createFromParcel(Parcel source) {
            return new ProviderAttributes(source);
        }

        public ProviderAttributes[] newArray(int size) {
            return new ProviderAttributes[size];
        }
    };
}
