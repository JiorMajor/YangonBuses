package com.theinhtikeaung.yangonbuses.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.TextPaint;


public class BusNameDrawable extends ShapeDrawable{

    private final Paint textPaint;
    private final int width;
    private final int height;
    private final String text;
    private final int fontSize;
    private final int color;


    public BusNameDrawable (Builder builder) {
        super(builder.shape);

        width = builder.width;
        height = builder.height;
        text = builder.toUpperCase ? builder.text.toUpperCase() : builder.text;
        color = builder.color;
        fontSize = builder.fontSize;

        textPaint = new Paint();
        textPaint.setColor(builder.textColor);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTypeface(builder.typeface);
        textPaint.setTextAlign(Paint.Align.CENTER);

        Paint paint = getPaint();
        paint.setColor(color);
    }


    @Override
    public int getIntrinsicHeight() {
        return height;
    }

    @Override
    public int getIntrinsicWidth() {
        return width;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Rect r = getBounds();

        int count = canvas.save();
        canvas.translate(r.left, r.top);

        int width = this.width < 0 ? r.width() : this.width;
        int height = this.height < 0 ? r.height() : this.height;

        //font size based on width and height
        int fontSize = this.fontSize < 0 ? (Math.min(width, height)/2) : this.fontSize;
        textPaint.setTextSize(fontSize);

        //draw text
        canvas.drawText(text, width/2, height/2 - ((textPaint.descent() + textPaint.ascent()) /2), textPaint);
        canvas.restoreToCount(count);
    }

    public static class Builder {

        private final String text;
        private final int color;
        private int width = -1;
        private int height = -1;
        private int textColor =Color.WHITE;
        private int fontSize = -1;
        private boolean toUpperCase = false;
        private Typeface typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL);
        private RectShape shape = new RectShape();

        public Builder (String text, int color) {
            this.text = text;
            this.color = color;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder textColor(int textColor) {
            this.textColor = textColor;
            return this;
        }

        public Builder fontSize(int fontSize) {
            this.fontSize = fontSize;
            return this;
        }

        public Builder toUppperCase() {
            this.toUpperCase = true;
            return this;
        }

        public BusNameDrawable buildRect() {
            this.shape = new RectShape();
            return  new BusNameDrawable(this);
        }

        public BusNameDrawable build() {
            return new BusNameDrawable(this);
        }

    }

}
