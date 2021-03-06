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

/**
 * Base controller interface for Activities.
 *
 * @author Oguz Babaoglu
 */
public interface BaseController {

    /**
     * Register to controller.
     *
     * @param fragment fragment to register.
     */
    void register(BaseFragment fragment);

    /**
     * Unregister from controller.
     *
     * @param fragment fragment to unregister.
     */
    void unregister(BaseFragment fragment);

}