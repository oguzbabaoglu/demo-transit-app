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

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;

import java.util.HashMap;

/**
 * Utility for handling custom fonts.
 *
 * @author Oguz Babaoglu
 */
public final class FontUtil {

    /**
     * TypeFace cache.
     */
    private static final HashMap<String, Typeface> fontMap = new HashMap<>();

    private FontUtil() {
    }

    /**
     * Applies a font span to target text.
     *
     * @param context context
     * @param font    full file name ex: roboto.tff
     * @param text    text to apply font to
     * @return styled text
     */
    public static CharSequence applyFont(Context context, String font, CharSequence text) {

        if (TextUtils.isEmpty(text)) {
            return "";
        }

        final SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(getFontSpan(context, font), 0, text.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * Creates and returns a font span.
     *
     * @param context context
     * @param font    full file name ex: roboto.tff
     * @return span for related Typeface
     */
    public static FontSpan getFontSpan(Context context, String font) {

        return new FontSpan(getFont(context, font));
    }

    /**
     * Reads font from assets.
     *
     * @param context context
     * @param font    full file name ex: roboto.tff
     * @return related Typeface
     */
    public static Typeface getFont(Context context, String font) {

        Typeface typeface = fontMap.get(font);

        if (typeface != null) {
            return typeface;
        }

        try {
            typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + font);
        } catch (Exception e) {
            throw new RuntimeException("Could not get typeface with name: " + font +
                    "\nFonts should be stored in /assets/fonts "
                    + "and full file name has to be passed as argument", e);
        }

        fontMap.put(font, typeface);

        return typeface;
    }

    private static class FontSpan extends MetricAffectingSpan {

        private static final float TEXT_SKEW_X = 0.25f;
        private Typeface typeface;

        /**
         * Constructor for FontSpan.
         *
         * @param typeface Typeface to be used on text drawings.
         */
        public FontSpan(Typeface typeface) {
            this.typeface = typeface;
        }

        @Override
        public void updateDrawState(final TextPaint drawState) {
            apply(drawState);
        }

        @Override
        public void updateMeasureState(final TextPaint paint) {
            apply(paint);
        }

        /**
         * Applies the typeface.
         *
         * @param paint The Paint class holds the style and color information about
         *              how to draw geometries, text and bitmaps.
         */
        private void apply(final Paint paint) {
            final Typeface oldTypeface = paint.getTypeface();
            final int oldStyle = oldTypeface != null ? oldTypeface.getStyle() : 0;
            final int fakeStyle = oldStyle & ~typeface.getStyle();

            if ((fakeStyle & Typeface.BOLD) != 0) {
                paint.setFakeBoldText(true);
            }

            if ((fakeStyle & Typeface.ITALIC) != 0) {
                paint.setTextSkewX(-TEXT_SKEW_X);
            }

            paint.setTypeface(typeface);
        }
    }
}
