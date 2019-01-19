////////////////////////////////////////////////////////////////////////////////
//
//  TDR - An Android Time Domain Reflectometer written in Java.
//
//  Copyright (C) 2014	Bill Farmer
//
//  This program is free software; you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation; either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
//  Bill Farmer	 william j farmer [at] yahoo [dot] co [dot] uk.
//
///////////////////////////////////////////////////////////////////////////////

package org.billthefarmer.tdr;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.view.View;

// Unit
public class Unit extends View
{
    protected float scale;

    private int width;
    private int height;
    private int textColour;

    private Paint paint;

    // Unit
    @SuppressWarnings("deprecation")
    public Unit(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        Resources resources = getResources();

        final TypedArray typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.TDR, 0, 0);

        textColour =
            typedArray.getColor(R.styleable.TDR_TextColour,
                                resources.getColor(android.R.color.black));
        typedArray.recycle();

        // Create paint
        paint = new Paint();

        // Set initial scale
        scale = 1;
    }

    // onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);

        // Get dinemsions
        width = w;
        height = h;
    }

    // onDraw
    @Override
    protected void onDraw(Canvas canvas)
    {
        paint.setStrokeWidth(2);
        paint.setColor(textColour);

        // Draw half a tick
        canvas.drawLine(width, 0, width, height / 3, paint);

        // Set up paint
        paint.setAntiAlias(true);
        paint.setTextSize(height * 2 / 3);
        paint.setTextAlign(Align.CENTER);

        // Draw half of the units
        canvas.drawText("m", width, height - (height / 6), paint);
    }
}
