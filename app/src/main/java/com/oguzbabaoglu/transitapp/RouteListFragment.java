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

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.annotation.Arg;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Oguz Babaoglu
 */
public class RouteListFragment extends BaseFragment {

    @InjectView(R.id.route_list_time_price_row)
    LinearLayout priceRow;

    @InjectView(R.id.route_list_icon_row)
    LinearLayout iconRow;

    @InjectView(R.id.route_list_time_column)
    LinearLayout timeColumn;

    @Arg
    RouteListModel routeListModel;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_route_list;
    }

    @Override
    public void onPrepareView(LayoutInflater inflater, View rootView, Bundle savedInstanceState) {
        ButterKnife.inject(this, rootView);

        createPriceRow(inflater, (ViewGroup) rootView);
        createIconRow(inflater, (ViewGroup) rootView);
        createTimeColumn(inflater, (ViewGroup) rootView);
    }

    private void createPriceRow(LayoutInflater inflater, ViewGroup rootView) {

        final ArrayList<RouteModel> routeList = routeListModel.getRouteModels();

        for (RouteModel route : routeList) {
            TextView priceText = (TextView) inflater.inflate(R.layout.view_routes_price, rootView, false);
            priceText.setText(route.getTotalTimeText() + "\n" + route.getPriceText());
            priceRow.addView(priceText);
        }
    }

    private void createIconRow(LayoutInflater inflater, ViewGroup rootView) {

        final ArrayList<RouteModel> routeList = routeListModel.getRouteModels();

        // TODO: Provider icons should be fetched from the network
        for (RouteModel route : routeList) {
            ImageView iconView = (ImageView) inflater.inflate(R.layout.view_routes_icon, rootView, false);
            iconRow.addView(iconView);
        }
    }

    private void createTimeColumn(LayoutInflater inflater, ViewGroup rootView) {

        for (int i = 0; i < 20; i++) {
            TextView time = (TextView) inflater.inflate(R.layout.view_routes_time, rootView, false);
            time.setText(i + ""); // dummy
            timeColumn.addView(time);
        }
    }
}
