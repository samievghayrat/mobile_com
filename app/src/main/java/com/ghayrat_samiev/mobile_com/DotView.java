package com.ghayrat_samiev.mobile_com;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class DotView extends View {

    private Paint paint;
    private float cx, cy;

    public DotView(Context context, int color, float x, float y) {
        super(context);

        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);

        cx = x;
        cy = y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(cx, cy, 10, paint);
    }
}
