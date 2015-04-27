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
import android.os.Parcel;
import android.os.Parcelable;

import com.oguzbabaoglu.transitapp.R;
import com.oguzbabaoglu.transitapp.data.models.Price;
import com.oguzbabaoglu.transitapp.data.models.Route;
import com.oguzbabaoglu.transitapp.data.models.Segment;
import com.oguzbabaoglu.transitapp.data.models.Stop;
import com.oguzbabaoglu.transitapp.util.ListUtils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * UI model for a single route.
 *
 * @author Oguz Babaoglu
 */
public class RouteModel implements Parcelable {

    private static final int MINUTES_IN_HOUR = 60;

    // Data model has amounts multiplied by 100
    private static final double AMOUNT_FACTOR = 100;

    private final long totalTime;
    private final String totalTimeText;
    private final String priceText;

    public RouteModel(Context context, Route routeData, long departTime) {

        totalTime = calculateTotalTime(routeData.getSegments(), departTime);
        totalTimeText = createTimeText(context, totalTime);
        priceText = createPriceText(routeData.getPrice());
    }

    /**
     * Calculate total time it takes for this route.
     */
    private long calculateTotalTime(List<Segment> segments, long departTime) {

        if (ListUtils.isEmpty(segments)) {
            return 0;
        }

        List<Stop> stops = segments.get(segments.size() - 1).getStops();

        if (ListUtils.isEmpty(stops)) {
            return 0;
        }

        Date endTime = stops.get(stops.size() - 1).getDateTime();
        return endTime.getTime() - departTime;
    }

    /**
     * @return a human readable format for time.
     */
    private String createTimeText(Context context, long time) {

        long hours = TimeUnit.MILLISECONDS.toHours(time);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(time) % MINUTES_IN_HOUR;

        String hoursText = hours == 0 ? "" : hours + context.getString(R.string.routes_hours) + " ";
        String minutesText = minutes == 0 ? "" : minutes + context.getString(R.string.routes_minutes);

        return hoursText + minutesText;
    }

    /**
     * @return a human readable format for price.
     */
    private String createPriceText(Price price) {

        if (price == null) {
            return "";
        }

        Currency currency = Currency.getInstance(price.getCurrency());
        NumberFormat nf = NumberFormat.getCurrencyInstance();

        // Replace currency symbol
        DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) nf).getDecimalFormatSymbols();
        decimalFormatSymbols.setCurrencySymbol(currency.getSymbol());
        ((DecimalFormat) nf).setDecimalFormatSymbols(decimalFormatSymbols);

        return nf.format(price.getAmount() / AMOUNT_FACTOR);
    }

    public long getTotalTime() {
        return totalTime;
    }

    public String getTotalTimeText() {
        return totalTimeText;
    }

    public String getPriceText() {
        return priceText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.totalTime);
        dest.writeString(this.totalTimeText);
        dest.writeString(this.priceText);
    }

    private RouteModel(Parcel in) {
        this.totalTime = in.readLong();
        this.totalTimeText = in.readString();
        this.priceText = in.readString();
    }

    public static final Parcelable.Creator<RouteModel> CREATOR = new Parcelable.Creator<RouteModel>() {
        public RouteModel createFromParcel(Parcel source) {
            return new RouteModel(source);
        }

        public RouteModel[] newArray(int size) {
            return new RouteModel[size];
        }
    };
}
