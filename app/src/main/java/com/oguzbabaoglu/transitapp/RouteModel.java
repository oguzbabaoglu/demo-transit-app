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

import android.os.Parcel;
import android.os.Parcelable;

import com.oguzbabaoglu.transitapp.data.models.Route;
import com.oguzbabaoglu.transitapp.data.models.Segment;
import com.oguzbabaoglu.transitapp.data.models.Stop;
import com.oguzbabaoglu.transitapp.util.ListUtils;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Oguz Babaoglu
 */
public class RouteModel implements Parcelable {

    private final long totalTime;
    private final String totalTimeText;

    public RouteModel(Route routeData, long departTime) {

        totalTime = calculateTotalTime(routeData.getSegments(), departTime);
        totalTimeText = createTimeText(totalTime);
    }

    /**
     * Calculate total time it takes for this route.
     */
    private long calculateTotalTime(List<Segment> segments, long departTime) {

        if (ListUtils.isEmpty(segments)) {
            return 0;
        }

        List<Stop> stops = segments.get(segments.size() - 1).getStops();

        if (ListUtils.isEmpty(stops)) {
            return 0;
        }

        Date endTime = stops.get(stops.size() - 1).getDateTime();
        return endTime.getTime() - departTime;
    }

    /**
     * @return a human readable format for time.
     */
    private String createTimeText(long time) {

        DateFormat df = DateFormat.getTimeInstance();
        return df.format(new Date(time));
    }

    public long getTotalTime() {
        return totalTime;
    }

    public String getTotalTimeText() {
        return totalTimeText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.totalTime);
        dest.writeString(this.totalTimeText);
    }

    private RouteModel(Parcel in) {
        this.totalTime = in.readLong();
        this.totalTimeText = in.readString();
    }

    public static final Parcelable.Creator<RouteModel> CREATOR = new Parcelable.Creator<RouteModel>() {
        public RouteModel createFromParcel(Parcel source) {
            return new RouteModel(source);
        }

        public RouteModel[] newArray(int size) {
            return new RouteModel[size];
        }
    };
}
