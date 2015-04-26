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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oguzbabaoglu.transitapp.data.models.Routes;
import com.oguzbabaoglu.transitapp.util.TimeUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Reads Json data and parses it into an object.
 * Needs to be initialized with an input stream to the Json resource.
 *
 * @author Oguz Babaoglu
 */
public final class DataProvider {

    private static Routes routes;

    private DataProvider() {
    }

    /**
     * @param stream input stream for the Json file.
     */
    public static void init(InputStream stream) throws IOException {

        final InputStreamReader reader = new InputStreamReader(stream);

        final Gson gson = new GsonBuilder().setDateFormat(TimeUtil.FORMAT).create();
        routes = gson.fromJson(reader, Routes.class);

        reader.close();
    }

    public static Routes getRoutes() {

        if (routes == null) {
            throw new IllegalStateException("Route data unavailable. Have you called init()?");
        }

        return routes;
    }
}
