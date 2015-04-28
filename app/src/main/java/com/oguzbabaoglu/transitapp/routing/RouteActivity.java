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
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.oguzbabaoglu.transitapp.R;
import com.oguzbabaoglu.transitapp.core.BaseActivity;
import com.oguzbabaoglu.transitapp.data.models.Routes;

/**
 * Routing activity. Allows the user to browse through available routes.
 *
 * @author Oguz Babaoglu
 */
public class RouteActivity extends BaseActivity implements RouteListController {

    private static final String KEY_ROUTES = "route.routes";
    private static final String KEY_DEPART_TIME = "route.depart";
    private static final String KEY_DESTINATION = "route.destination";

    private Routes routes;
    private long departTime;
    private String destination;
    private RouteListModel routeListModel;

    /**
     * Intent factory method.
     */
    public static Intent newIntent(Context context,
                                   Routes routes,
                                   long departTime,
                                   String destination) {

        final Intent intent = new Intent(context, RouteActivity.class);
        intent.putExtra(KEY_ROUTES, routes);
        intent.putExtra(KEY_DEPART_TIME, departTime);
        intent.putExtra(KEY_DESTINATION, destination);
        return intent;
    }

    @Override
    protected Fragment getInitialFragment() {

        routes = getIntent().getParcelableExtra(KEY_ROUTES);
        departTime = getIntent().getLongExtra(KEY_DEPART_TIME, 0);
        destination = getIntent().getStringExtra(KEY_DESTINATION);

        routeListModel = new RouteListModel(this, routes, departTime);

        return RouteListFragmentBuilder.newRouteListFragment(routeListModel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Toolbar toolbar = getToolbar();
        TextView ticker = (TextView) getLayoutInflater().inflate(R.layout.view_ticker_text, toolbar, false);
        ticker.setText(destination);
        ticker.setSelected(true); // start the ticker

        toolbar.addView(ticker);
    }

    @Override
    public void onRouteSelected(int routeIndex) {

        // TODO: Should start a new Activity for the map view.
        replaceContentFragment(RouteMapFragmentBuilder.newRouteMapFragment(routeIndex, routeListModel), true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // This is required because map is not a new Activity yet
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
