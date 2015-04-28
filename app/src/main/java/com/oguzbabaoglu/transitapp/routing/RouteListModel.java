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

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.oguzbabaoglu.transitapp.data.models.Route;
import com.oguzbabaoglu.transitapp.data.models.Routes;
import com.oguzbabaoglu.transitapp.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * UI model for the route list.
 *
 * @author Oguz Babaoglu
 */
public class RouteListModel implements Parcelable {

    private final long departTime;
    private final long maxTotalTime;
    private final ArrayList<RouteModel> routeModels = new ArrayList<>();

    public RouteListModel(Context context, Routes routes, long departTime) {

        this.departTime = departTime;

        List<Route> routeList = routes.getRoutes();

        if (ListUtils.isEmpty(routeList)) {
            maxTotalTime = 0;
            return;
        }

        long tmpMax = 0;

        for (Route route : routeList) {
            RouteModel routeModel = new RouteModel(context, route, departTime);
            routeModels.add(routeModel);

            if (routeModel.getTotalTime() > tmpMax) {
                tmpMax = routeModel.getTotalTime();
            }
        }

        maxTotalTime = tmpMax;
    }

    public long getDepartTime() {
        return departTime;
    }

    public long getMaxTotalTime() {
        return maxTotalTime;
    }

    public ArrayList<RouteModel> getRouteModels() {
        return routeModels;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(this.departTime);
        dest.writeTypedList(this.routeModels);
        dest.writeLong(this.maxTotalTime);
    }

    private RouteListModel(Parcel in) {
        this.departTime = in.readLong();
        in.readTypedList(this.routeModels, RouteModel.CREATOR);
        this.maxTotalTime = in.readLong();
    }

    public static final Parcelable.Creator<RouteListModel> CREATOR = new Parcelable.Creator<RouteListModel>() {
        public RouteListModel createFromParcel(Parcel source) {
            return new RouteListModel(source);
        }

        public RouteListModel[] newArray(int size) {
            return new RouteListModel[size];
        }
    };
}
