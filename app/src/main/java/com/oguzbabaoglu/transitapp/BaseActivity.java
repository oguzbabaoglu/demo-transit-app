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

package com.oguzbabaoglu.transitapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.oguzbabaoglu.transitapp.util.FontUtil;

/**
 * Base Activity class.
 *
 * @author Oguz Babaoglu
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.activity_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        // Style title with font
        if (actionBar != null) {
            actionBar.setTitle(FontUtil.applyFont(this,
                    getString(R.string.font_caviar_dreams), actionBar.getTitle()));
        }

        // Start with initial fragment
        if (savedInstanceState == null) {

            final Fragment fragment = getInitialFragment();
            if (fragment != null) {
                addContentFragment(fragment);
            }
        }
    }

    /**
     * The first Fragment seen in this Activity.
     *
     * @return first fragment
     */
    protected abstract Fragment getInitialFragment();

    /**
     * Add a fragment to the content container.
     *
     * @param fragment Fragment to be added
     */
    public void addContentFragment(Fragment fragment) {

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.activity_content, fragment, fragment.getClass().getSimpleName());
        transaction.commit();
    }

    @Override
    public void register(BaseFragment fragment) {
        // Register for Activity -> Fragment interaction (Network results etc.)
    }

    @Override
    public void unregister(BaseFragment fragment) {
        // unregister
    }

}
