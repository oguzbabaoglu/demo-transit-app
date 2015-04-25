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
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * A ScrollView that notifies listener of scroll events.
 *
 * @author Oguz Babaoglu
 */
public class SyncedScrollView extends ScrollView implements ScrollNotifier {

    private ScrollListener scrollListener = null;

    public SyncedScrollView(Context context) {
        super(context);
    }

    public SyncedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SyncedScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (scrollListener != null) {
            scrollListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }

    @Override
    public void setScrollListener(ScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }

    @Override
    public ScrollListener getScrollListener() {
        return scrollListener;
    }
}