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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oguzbabaoglu.transitapp.data.models.Price;
import com.oguzbabaoglu.transitapp.data.models.Route;
import com.oguzbabaoglu.transitapp.data.models.Routes;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Oguz Babaoglu
 */
public class RouteListFragment extends BaseFragment {

    private static final String KEY_ROUTES = "routelist.routes";

    @InjectView(R.id.route_list_time_column)
    LinearLayout timeColumn;

    @InjectView(R.id.route_list_time_price_row)
    LinearLayout priceRow;

    private Routes routes;

    public static RouteListFragment newInstance(Routes routes) {
        final Bundle args = new Bundle();
        args.putParcelable(KEY_ROUTES, routes);

        final RouteListFragment fragment = new RouteListFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_route_list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        routes = getArguments().getParcelable(KEY_ROUTES);
    }

    @Override
    public void onPrepareView(LayoutInflater inflater, View rootView, Bundle savedInstanceState) {
        ButterKnife.inject(this, rootView);

        createTimeColumn(inflater, (ViewGroup) rootView);
        createPriceRow(inflater, (ViewGroup) rootView);
    }

    private void createPriceRow(LayoutInflater inflater, ViewGroup rootView) {

        final ArrayList<Route> routeList = routes.getRoutes();

        for (Route route : routeList) {
            TextView priceText = (TextView) inflater.inflate(R.layout.view_routes_price, rootView, false);
            Price price = route.getPrice();
            priceText.setText(price == null ? "" : price.getAmount() + "");
            priceRow.addView(priceText);
        }
    }

    private void createTimeColumn(LayoutInflater inflater, ViewGroup rootView) {

        for (int i = 0; i < 6; i++) {
            TextView time = (TextView) inflater.inflate(R.layout.view_routes_time, rootView, false);
            time.setText(i + "");
            timeColumn.addView(time);
        }
    }
}
