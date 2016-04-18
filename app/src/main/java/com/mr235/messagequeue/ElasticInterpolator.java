package com.mr235.messagequeue;

import android.view.animation.Interpolator;


/* compiled from: ElasticInterpolator */
public final class ElasticInterpolator implements Interpolator {
    private EasingType.Type a;
    private float b;
    private float c;

    public ElasticInterpolator(EasingType.Type type) {
        this.a = type;
        this.b = 1.0f;
        this.c = 0.3F;
    }

    public final float getInterpolation(float f) {
        float f2;
        float f3;
        float f4;
        if (this.a == EasingType.Type.IN) {
            f2 = this.b;
            f3 = this.c;
            if (f == 0.0f) {
                return 0.0f;
            }
            if (f >= 1.0f) {
                return 1.0f;
            }
            f4 = f3 == 0.0f ? 0.3f : f3;
            if (f2 == 0.0f || f2 < 1.0f) {
                f2 = 1.0f;
                f3 = f4 / 4.0f;
            } else {
                f3 = (float) ((((double) f4) / (2* Math.PI)) * Math.asin((double) (1.0f / f2)));
            }
            float f5 = f - 1.0f;
            return (float) (-(Math.sin((((double) (f5 - f3)) * (2* Math.PI)) / ((double) f4)) * (((double) f2) * Math.pow(2.0d, (double) (10 * f5)))));
        } else if (this.a == EasingType.Type.OUT) {
            f2 = this.b;
            f3 = this.c;
            if (f == 0.0f) {
                return 0.0f;
            }
            if (f >= 1.0f) {
                return 1.0f;
            }
            f4 = f3 == 0.0f ? 0.3f : f3;
            if (f2 == 0.0f || f2 < 1.0f) {
                f2 = 1.0f;
                f3 = f4 / 4.0f;
            } else {
                f3 = (float) ((((double) f4) / (2* Math.PI)) * Math.asin((double) (1.0f / f2)));
            }
            return (float) ((Math.sin((((double) (f - f3)) * (2* Math.PI)) / ((double) f4)) * (((double) f2) * Math.pow(2.0d, (double) (-10.0f * f)))) + 1.0d);
        } else if (this.a != EasingType.Type.INOUT) {
            return 0.0f;
        } else {
            f2 = this.b;
            f3 = this.c;
            if (f == 0.0f) {
                return 0.0f;
            }
            if (f >= 1.0f) {
                return 1.0f;
            }
            if (f3 == 0.0f) {
                f4 = 0.45000002f;
            } else {
                f4 = f3;
            }
            if (f2 == 0.0f || f2 < 1.0f) {
                f2 = 1.0f;
                f3 = f4 / 4.0f;
            } else {
                f3 = (float) ((((double) f4) / (2* Math.PI)) * Math.asin((double) (1.0f / f2)));
            }
            float f6 = 2.0f * f;
            if (f6 < 1.0f) {
                f6 -= 1.0f;
                return (float) ((Math.sin((((double) (f6 - f3)) * (2* Math.PI)) / ((double) f4)) * (((double) f2) * Math.pow(2.0d, (double) (10.0f * f6)))) * -0.5d);
            }
            f6 -= 1.0f;
            return (float) (((Math.sin((((double) (f6 - f3)) * (2* Math.PI)) / ((double) f4)) * (((double) f2) * Math.pow(2.0d, (double) (-10.0f * f6)))) * 0.5d) + 1.0d);
        }
    }
}
