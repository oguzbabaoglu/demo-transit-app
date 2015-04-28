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

package com.oguzbabaoglu.transitapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Oguz Babaoglu
 */
public final class TimeUtil {

    /**
     * Time format used is ISO_8601.
     * Android's implementation accepts 'ZZZZZ' for zones in the +01:00 format.
     * Java 7 expects 'XXX' for this format.
     * This means 'ZZZZZ' works on devices but fails the new local unit tests.
     */
    public static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZZZZ"; // Java "yyyy-MM-dd'T'HH:mm:ssXXX"

    private TimeUtil() {
    }

    /**
     * @return parsed date
     */
    public static Date parse(String date) throws ParseException {
        return new SimpleDateFormat(FORMAT, Locale.US).parse(date);
    }
}
