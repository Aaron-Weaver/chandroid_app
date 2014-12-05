package com.chan.weava.chandroidapp;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.text.TextPaint;

/**
 * [Insert Class Name]
 * <p/>
 * [Insert Class Description]
 * <p/>
 * [Insert Class Details]
 * <p/>
 * <Insert Class Derivatives/Dependencies>
 * <p/>
 * <Insert Class's Associated Design Patterns>
 *
 * @author Aaron Weaver         (waaronl@okstate.edu)
 * @version ForeChanApp v0.1A
 * @since 11/19/14
 */
public class LetterTile
{
    private int mNumTileColors;
    private final TextPaint mPaint = new TextPaint();
    private final Canvas mCanvas = new Canvas();
    private final Rect mBounds = new Rect();
    private final char[] mFirstChar = new char[1];
    private final TypedArray mColors;

    public LetterTile(Context context)
    {
        final Resources res = context.getResources();

        mColors = res.obtainTypedArray(R.array.letter_tile_colors);
        mNumTileColors = mColors.length();
    }

    public int pickColor(String key) {
        // String.hashCode() is not supposed to change across java versions, so
        // this should guarantee the same key always maps to the same color
        final int color = Math.abs(key.hashCode()) % mNumTileColors;
        System.out.println();
        return mColors.getColor(color, Color.BLACK);
    }
}
