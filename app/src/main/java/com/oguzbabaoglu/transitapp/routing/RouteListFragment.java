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

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.oguzbabaoglu.transitapp.R;
import com.oguzbabaoglu.transitapp.core.BaseFragment;
import com.oguzbabaoglu.transitapp.views.BiScrollView;
import com.oguzbabaoglu.transitapp.views.RouteColumn;
import com.oguzbabaoglu.transitapp.views.ScrollSync;
import com.oguzbabaoglu.transitapp.views.SyncedScrollView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.InjectView;

/**
 * List view for routes. Contains a scrollable table of routes
 * with their segments visualised.
 *
 * @author Oguz Babaoglu
 */
public class RouteListFragment extends BaseFragment<RouteListController> implements View.OnClickListener {

    @InjectView(R.id.route_list_time_price_row)
    LinearLayout priceRow;

    @InjectView(R.id.route_list_icon_row)
    LinearLayout iconRow;

    @InjectView(R.id.route_list_table)
    LinearLayout routeTable;

    @InjectView(R.id.route_list_time_column)
    LinearLayout timeColumn;

    @InjectView(R.id.route_list_horizontal_scroll)
    BiScrollView biScrollView;

    @InjectView(R.id.route_list_table_scroll)
    SyncedScrollView routeTableScroll;

    @InjectView(R.id.route_list_time_column_scroll)
    SyncedScrollView timeColumnScroll;

    @Arg
    RouteListModel routeListModel;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_route_list;
    }

    @Override
    public void onPrepareView(LayoutInflater inflater, View rootView, Bundle savedInstanceState) {

        createPriceRow(inflater);
        createIconRow(inflater);
        createRouteTable(inflater);
        createTimeColumn(inflater);

        // Sync scroll views
        ScrollSync sync = new ScrollSync();
        sync.register(routeTableScroll);
        sync.register(timeColumnScroll);

        // Connect bi-directional scrollview
        biScrollView.setChild(routeTableScroll);
    }

    private void createPriceRow(LayoutInflater inflater) {

        final ArrayList<RouteModel> routeList = routeListModel.getRouteModels();

        for (RouteModel route : routeList) {
            TextView priceText = (TextView) inflater.inflate(R.layout.view_routes_price, priceRow, false);
            priceText.setText(route.getTotalTimeText() + "\n" + route.getPriceText());
            priceRow.addView(priceText);
        }
    }

    private void createIconRow(LayoutInflater inflater) {

        final ArrayList<RouteModel> routeList = routeListModel.getRouteModels();

        // TODO: Provider icons should be fetched from the network
        for (RouteModel route : routeList) {
            ImageView iconView = (ImageView) inflater.inflate(R.layout.view_routes_icon, iconRow, false);
            iconRow.addView(iconView);
        }
    }

    private void createRouteTable(LayoutInflater inflater) {

        final ArrayList<RouteModel> routeList = routeListModel.getRouteModels();

        for (int i = 0; i < routeList.size(); i++) {
            RouteColumn routeColumn = new RouteColumn(inflater.getContext(), routeList.get(i));
            routeColumn.setTag(i);
            routeColumn.setOnClickListener(this);
            routeTable.addView(routeColumn);
        }
    }

    private void createTimeColumn(LayoutInflater inflater) {

        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
        Date date = new Date();

        long departTime = routeListModel.getDepartTime();
        int intervalMins = getResources().getInteger(R.integer.routes_time_interval_min);
        long intervalMillis = TimeUnit.MINUTES.toMillis(intervalMins);
        long maxTotalTime = departTime + routeListModel.getMaxTotalTime();

        for (long time = departTime; time < maxTotalTime; time += intervalMillis) {

            date.setTime(time);
            String timeText = dateFormat.format(date);

            TextView timeView = (TextView) inflater.inflate(R.layout.view_routes_time, timeColumn, false);
            timeView.setText(timeText);
            timeColumn.addView(timeView);
        }
    }

    @Override
    public void onClick(View v) {

        getController().onRouteSelected((int) v.getTag());
    }
}
