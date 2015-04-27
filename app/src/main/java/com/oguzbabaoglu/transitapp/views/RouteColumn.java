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

package com.oguzbabaoglu.transitapp.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.view.View;

import com.oguzbabaoglu.transitapp.R;
import com.oguzbabaoglu.transitapp.routing.RouteModel;
import com.oguzbabaoglu.transitapp.routing.SegmentModel;

import java.util.concurrent.TimeUnit;

/**
 * A custom view for visual representation of a Route's Segments.
 *
 * @author Oguz Babaoglu
 */
public class RouteColumn extends View {

    private static final String MEASURE_TEXT = "U20";

    private final Paint barPaint;
    private final TextPaint textPaint;
    private final int textHeight;

    private final long totalTime;
    private final double milliToPixels; // conversion between milliseconds to pixels
    private final int width;
    private final int height;

    public RouteColumn(Context context, RouteModel model) {
        super(context);

        final Resources res = context.getResources();

        totalTime = model.getTotalTime();

        int intervalMinutes = res.getInteger(R.integer.routes_time_interval_min);
        long intervalMillis = TimeUnit.MINUTES.toMillis(intervalMinutes);
        int intervalPixels = res.getDimensionPixelSize(R.dimen.routes_interval_size);

        milliToPixels = (double) intervalPixels / intervalMillis;

        width = res.getDimensionPixelSize(R.dimen.routes_column_size);
        height = (int) (totalTime * milliToPixels);

        barPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        barPaint.setStyle(Paint.Style.FILL);

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.SUBPIXEL_TEXT_FLAG);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(getResources().getDimension(R.dimen.text_normal));

        Rect textBounds = new Rect();
        textPaint.getTextBounds(MEASURE_TEXT, 0, MEASURE_TEXT.length() - 1, textBounds);
        textHeight = textBounds.height();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // Testing height calculation
        barPaint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), barPaint);
    }

    private void drawEmptySegment(Canvas canvas, SegmentModel segmentModel) {

        barPaint.setColor(segmentModel.getColor());
    }

    private void drawSegment(Canvas canvas, SegmentModel segmentModel) {

        barPaint.setColor(segmentModel.getColor());
    }

}
