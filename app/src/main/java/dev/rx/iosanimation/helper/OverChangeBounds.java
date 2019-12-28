package dev.rx.iosanimation.helper;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.constraintlayout.solver.widgets.Rectangle;
import androidx.core.view.ViewCompat;
import androidx.transition.ChangeBounds;
import androidx.transition.TransitionValues;

import java.lang.reflect.Field;

public class OverChangeBounds extends ChangeBounds {


    private static final String PROPNAME_BOUNDS = "android:changeBounds:bounds";

    public OverChangeBounds() {
        keke();
    }

    public OverChangeBounds(Context context, AttributeSet attrs) {
        super(context, attrs);
        keke();
    }

    private void keke() {
        try {
            Field xField = this.getClass().getSuperclass().getDeclaredField("mReparent");
            xField.setAccessible(true);
            xField.setBoolean(this, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        super.captureEndValues(transitionValues);
        captureValues(transitionValues);
    }

    private void captureValues(TransitionValues values) {
        View view = values.view;

        if (ViewCompat.isLaidOut(view) || view.getWidth() != 0 || view.getHeight() != 0) {
            int[] mTempLocation = new int[2];
            values.view.getLocationInWindow(mTempLocation);
            int x = mTempLocation[0];
            int y = mTempLocation[1];

            int left = view.getLeft();
            int right = view.getRight();
            int top = view.getTop();
            int bottom = view.getBottom();

            /*left -= x;
            right -= x;
            top -= y;
            bottom -= y;*/

            left = x;
            right = x + view.getWidth();
            top = y;
            bottom = y+view.getHeight();

            values.values.put(PROPNAME_BOUNDS, new Rect(left, top, right, bottom));
        }
    }


}
