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
 * @author Oguz Babaoglu
 */
public final class Provider implements Parcelable {

    @SerializedName("provider_icon_url")
    private String iconUrl;

    @SerializedName("disclaimer")
    private String disclaimer;

    @SerializedName("android_package_name")
    private String packageName;

    @SerializedName("display_name")
    private String displayName;

    public String getIconUrl() {
        return iconUrl;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.iconUrl);
        dest.writeString(this.disclaimer);
        dest.writeString(this.packageName);
        dest.writeString(this.displayName);
    }

    public Provider() {
    }

    private Provider(Parcel in) {
        this.iconUrl = in.readString();
        this.disclaimer = in.readString();
        this.packageName = in.readString();
        this.displayName = in.readString();
    }

    public static final Parcelable.Creator<Provider> CREATOR = new Parcelable.Creator<Provider>() {
        public Provider createFromParcel(Parcel source) {
            return new Provider(source);
        }

        public Provider[] newArray(int size) {
            return new Provider[size];
        }
    };
}
