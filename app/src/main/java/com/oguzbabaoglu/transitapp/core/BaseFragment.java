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

package com.oguzbabaoglu.transitapp.core;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.fragmentargs.FragmentArgs;

/**
 * Base Fragment class.
 *
 * @author Oguz Babaoglu
 */
public abstract class BaseFragment<Controller extends BaseController> extends Fragment {

    private Controller controller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentArgs.inject(this);
    }

    /**
     * @return layout id for the fragment.
     */
    public abstract int getLayoutId();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(getLayoutId(), container, false);
        onPrepareView(inflater, rootView, savedInstanceState);
        return rootView;
    }

    /**
     * Called during view creation with inflated layout.
     */
    public abstract void onPrepareView(LayoutInflater inflater, View rootView, Bundle savedInstanceState);

    public Controller getController() {
        return controller;
    }

    @Override
    @SuppressWarnings("unchecked") // Class cast exception handled
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            controller = (Controller) activity;
        } catch (ClassCastException e) {
            throw new RuntimeException(activity.getLocalClassName() + " must implement controller.", e);
        }
        controller.register(this);
    }

    @Override
    public void onDetach() {
        controller.unregister(this);
        controller = null;
        super.onDetach();
    }
}
