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

package com.oguzbabaoglu.transitapp.routing;

import android.os.Parcel;
import android.os.Parcelable;

import com.oguzbabaoglu.transitapp.data.models.Segment;
import com.oguzbabaoglu.transitapp.data.models.Stop;
import com.oguzbabaoglu.transitapp.util.ListUtils;

import java.util.List;

/**
 * UI model for a single segment.
 *
 * @author Oguz Babaoglu
 */
public class SegmentModel implements Parcelable {

    private final String name;
    private final int color;
    private final long startTime;
    private final long endTime;

    public SegmentModel(Segment segment) {

        name = segment.getName() == null ? segment.getTravelMode() : segment.getName();

        int tmpColor;

        try {
            tmpColor = Integer.decode(segment.getColor());
        } catch (NumberFormatException e) {
            tmpColor = 0;
        }

        color = tmpColor | 0xFF000000; // add full alpha

        List<Stop> stops = segment.getStops();

        if (ListUtils.isEmpty(stops)) {
            startTime = 0;
            endTime = 0;
            return;
        }

        startTime = stops.get(0).getDateTime().getTime();
        endTime = stops.get(stops.size() - 1).getDateTime().getTime();
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.color);
        dest.writeLong(this.startTime);
        dest.writeLong(this.endTime);
    }

    private SegmentModel(Parcel in) {
        this.name = in.readString();
        this.color = in.readInt();
        this.startTime = in.readLong();
        this.endTime = in.readLong();
    }

    public static final Parcelable.Creator<SegmentModel> CREATOR = new Parcelable.Creator<SegmentModel>() {
        public SegmentModel createFromParcel(Parcel source) {
            return new SegmentModel(source);
        }

        public SegmentModel[] newArray(int size) {
            return new SegmentModel[size];
        }
    };
}
