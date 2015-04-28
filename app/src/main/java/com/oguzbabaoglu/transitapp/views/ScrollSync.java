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

import java.util.ArrayList;

/**
 * Syncs all registered scrollable views with each other.
 *
 * @author Oguz Babaoglu
 */
public class ScrollSync implements ScrollListener {

    private boolean isSyncing;
    private ArrayList<ScrollNotifier> registeredViews;

    public ScrollSync() {
        registeredViews = new ArrayList<>();
    }

    /**
     * @param scrollNotifier notifier view to register
     */
    public void register(ScrollNotifier scrollNotifier) {

        scrollNotifier.setScrollListener(this);
        registeredViews.add(scrollNotifier);
    }

    @Override
    public void onScrollChanged(ScrollNotifier scrollNotifier, int l, int t, int oldl, int oldt) {

        // avoid notifications while scroll bars are being synchronized
        if (isSyncing) {
            return;
        }

        isSyncing = true;

        final int senderIndex = registeredViews.indexOf(scrollNotifier);

        for (int i = 0; i < registeredViews.size(); i++) {

            // No need to notify sender
            if (i != senderIndex) {
                registeredViews.get(i).scrollTo(l, t);
            }
        }

        isSyncing = false;
    }
}
