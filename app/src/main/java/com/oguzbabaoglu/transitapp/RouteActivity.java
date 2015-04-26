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
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.oguzbabaoglu.transitapp.data.models.Routes;

/**
 * @author Oguz Babaoglu
 */
public class RouteActivity extends BaseActivity {

    private static final String KEY_ROUTES = "route.routes";
    private static final String KEY_DEPART_TIME = "route.depart";
    private static final String KEY_DESTINATION = "route.destination";

    private Routes routes;
    private long departTime;
    private String destination;

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

        final RouteListModel uiModel = new RouteListModel(routes, departTime, destination);

        return RouteListFragmentBuilder.newRouteListFragment(uiModel);
    }
}
