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

package com.oguzbabaoglu.transitapp.data;

import com.oguzbabaoglu.transitapp.BuildConfig;
import com.oguzbabaoglu.transitapp.data.models.Route;
import com.oguzbabaoglu.transitapp.data.models.Routes;
import com.oguzbabaoglu.transitapp.data.models.Stop;
import com.oguzbabaoglu.transitapp.util.TimeUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test with the new Gradle JUnit4 support.
 *
 * @author Oguz Babaoglu
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DataProviderTest {

    @Test
    public void testParser() throws Exception {

        DataProvider.init(RuntimeEnvironment.application);
        Routes routes = DataProvider.getRoutes();
        assertThat(routes)
                .isNotNull();

        ArrayList<Route> routeList = routes.getRoutes();
        assertThat(routeList)
                .isNotNull()
                .isNotEmpty();

        Stop stop = routeList.get(0).getSegments().get(0).getStops().get(0);
        assertThat(stop)
                .isNotNull();

        double lat = stop.getLat();
        assertThat(lat)
                .isNotNull()
                .isEqualTo(52.530227);

        Date expectedDate = TimeUtil.parse("2015-04-17T13:30:00+02:00");
        Date date = stop.getDateTime();
        assertThat(date)
                .isNotNull()
                .isEqualTo(expectedDate);

        String nextBike = routes.getProviderAttributes().getNextbike().getDisplayName();
        assertThat(nextBike)
                .isEqualTo("Nextbike");

    }
}