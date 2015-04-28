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

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.view.View;

import com.oguzbabaoglu.transitapp.R;
import com.oguzbabaoglu.transitapp.routing.RouteModel;
import com.oguzbabaoglu.transitapp.routing.SegmentModel;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * A custom view for visual representation of a Route's Segments.
 *
 * @author Oguz Babaoglu
 */
@SuppressLint("ViewConstructor") // Does not support xml inflation
public class RouteColumn extends View {

    private final Paint barPaint;
    private final TextPaint textPaint;

    private final double milliToPixels; // conversion between milliseconds to pixels
    private final int width;
    private final int height;
    private final int segmentPadding;

    private final ArrayList<SegmentModel> segmentModels;

    private final long departTime; // use depart time to normalize time values

    public RouteColumn(Context context, RouteModel model) {
        super(context);

        final Resources res = context.getResources();

        segmentModels = model.getSegments();
        departTime = model.getDepartTime();

        int intervalMinutes = res.getInteger(R.integer.routes_time_interval_min);
        long intervalMillis = TimeUnit.MINUTES.toMillis(intervalMinutes);
        int intervalPixels = res.getDimensionPixelSize(R.dimen.routes_interval_size);

        milliToPixels = (double) intervalPixels / intervalMillis;

        width = res.getDimensionPixelSize(R.dimen.routes_column_size);

        // We want to sync height with that of the time column
        int realHeight = (int) (model.getTotalTime() * milliToPixels);
        height = realHeight + intervalPixels - (realHeight % intervalPixels);

        int segmentWidth = res.getDimensionPixelSize(R.dimen.routes_segment_width);
        segmentPadding = (width - segmentWidth) >> 1;

        // Init paint objects
        barPaint = createBarPaint();
        textPaint = createTextPaint();
    }

    /**
     * @return paint object for drawing segments
     */
    private Paint createBarPaint() {

        Paint barPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        barPaint.setStyle(Paint.Style.FILL);

        return barPaint;
    }

    /**
     * @return paint object for drawing text
     */
    private TextPaint createTextPaint() {

        TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.SUBPIXEL_TEXT_FLAG);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(getResources().getColor(R.color.white));
        textPaint.setTextSize(getResources().getDimension(R.dimen.text_normal));

        return textPaint;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (SegmentModel segment : segmentModels) {
            drawSegment(canvas, segment);
        }
    }

    /**
     * Draw an individual segment.
     *
     * @param canvas  canvas to draw on
     * @param segment segment to draw
     */
    private void drawSegment(Canvas canvas, SegmentModel segment) {

        barPaint.setColor(segment.getColor());

        int top = (int) ((segment.getStartTime() - departTime) * milliToPixels);
        int bottom = (int) ((segment.getEndTime() - departTime) * milliToPixels);

        int left = segmentPadding;
        int right = width - segmentPadding;

        canvas.drawRect(left, top, right, bottom, barPaint);

        String name = segment.getName();

        int xPos = width >> 1;
        int yPos = (int) (top + ((bottom - top) / 2) - ((textPaint.descent() + textPaint.ascent()) / 2));

        canvas.drawText(name, xPos, yPos, textPaint);
    }

}
