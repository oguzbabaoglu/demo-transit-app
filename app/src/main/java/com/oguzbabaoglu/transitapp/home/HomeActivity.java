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

package com.oguzbabaoglu.transitapp.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import com.oguzbabaoglu.transitapp.R;
import com.oguzbabaoglu.transitapp.routing.RouteActivity;
import com.oguzbabaoglu.transitapp.core.BaseActivity;
import com.oguzbabaoglu.transitapp.data.DataProvider;
import com.oguzbabaoglu.transitapp.data.models.Routes;
import com.oguzbabaoglu.transitapp.util.FontUtil;
import com.oguzbabaoglu.transitapp.util.TimeUtil;

import java.text.ParseException;

/**
 * First Activity presented to the user.
 * Used for initiating route finding.
 *
 * @author Oguz Babaoglu
 */
public class HomeActivity extends BaseActivity implements HomeController {

    // Assume request was made at this time for logical time values
    private static final String DEPART_TIME = "2015-04-17T13:25:00+02:00";

    // Dummy destination
    private static final String DESTINATION = "Chausseestraße 1, 10115 Berlin, Germany";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = getSupportActionBar();

        // Style title with font
        if (actionBar != null) {
            actionBar.setTitle(FontUtil.applyFont(this,
                    getString(R.string.font_caviar_dreams), actionBar.getTitle()));
        }
    }

    @Override
    protected Fragment getInitialFragment() {
        return HomeFragment.newInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRouteRequested() {

        // This should normally be a Network call
        final Routes routes = DataProvider.getRoutes();
        long departTime = 0;
        try {
            departTime = TimeUtil.parse(DEPART_TIME).getTime();
        } catch (ParseException e) {
            // Should not happen
        }
        startActivity(RouteActivity.newIntent(this, routes, departTime, DESTINATION));
    }

}
