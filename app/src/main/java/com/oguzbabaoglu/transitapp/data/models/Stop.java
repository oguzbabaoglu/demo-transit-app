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

import java.util.Date;

/**
 * @author Oguz Babaoglu
 */
public final class Stop {

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
}
