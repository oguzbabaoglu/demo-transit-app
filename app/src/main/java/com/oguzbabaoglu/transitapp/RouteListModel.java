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

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.oguzbabaoglu.transitapp.data.models.Route;
import com.oguzbabaoglu.transitapp.data.models.Routes;
import com.oguzbabaoglu.transitapp.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oguz Babaoglu
 */
public class RouteListModel implements Parcelable {

    private final ArrayList<RouteModel> routeModels = new ArrayList<>();

    public RouteListModel(Context context, Routes routes, long departTime, String destination) {

        List<Route> routeList = routes.getRoutes();

        if (ListUtils.isEmpty(routeList)) {
            return;
        }

        for (Route route : routeList) {
            routeModels.add(new RouteModel(context, route, departTime));
        }
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
        dest.writeTypedList(this.routeModels);
    }

    private RouteListModel(Parcel in) {
        in.readTypedList(this.routeModels, RouteModel.CREATOR);
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
