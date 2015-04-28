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
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

/**
 * A bi-directional scrollview, passes touch events to child view.
 *
 * @author Oguz Babaoglu
 */
public class BiScrollView extends HorizontalScrollView {

    private ScrollView child;

    public BiScrollView(Context context) {
        super(context);
    }

    public BiScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BiScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        super.onTouchEvent(event);
        child.dispatchTouchEvent(event);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull MotionEvent event) {
        super.onInterceptTouchEvent(event);
        child.onInterceptTouchEvent(event);
        return true;
    }

    public void setChild(ScrollView child) {
        this.child = child;
    }
}
