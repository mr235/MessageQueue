package com.mr235.messagequeue;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.Date;

/**
 * Created by Administrator on 2016/4/7.
 */
public class CustomView extends FrameLayout {

	private StrokeTextView mStvText;

	private IAnimationEnd animationEndListener = null;

	private boolean isAnimating = false;

	public boolean isAnimating() {
		return isAnimating;
	}

	public void setAnimationEndListener(IAnimationEnd animationEndListener) {
		this.animationEndListener = animationEndListener;
	}

	public CustomView(Context context) {
		this(context, null);
	}

	public CustomView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.layout_present_message, this, true);
		mStvText = (StrokeTextView) view.findViewById(R.id.stv_count);
	}

	public void startAnimation(final boolean scale) {
		System.out.println(CustomView.this.hashCode() + " --------------1");
		isAnimating = true;
		clearAnimation();
		Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.translate_in);
		animation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				System.out.println(CustomView.this.hashCode() + " --------------2");
				if (scale) {
					animationText();
				} else {
					animationUp();
				}
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
		startAnimation(animation);
	}

	private void animationUp() {
		System.out.println(CustomView.this.hashCode() + " --------------3");
		clearAnimation();
		Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.translate_up);
		animation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				System.out.println(CustomView.this.hashCode() + " --------------4");
				animationEnd(animation);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
		startAnimation(animation);
	}

	private void animationEnd(Animation animation) {
		System.out.println(CustomView.this.hashCode() + " --------------5");
		isAnimating = false;
		setVisibility(INVISIBLE);
		if (animationEndListener!=null) {
			animationEndListener.onAnimationEnd(animation);
		}
	}

	private void animationText() {
		mStvText.clearAnimation();
		System.out.println(CustomView.this.hashCode() + " --------------6");
		Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.scale);
		animation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				System.out.println(CustomView.this.hashCode() + " --------------7");
				animationUp();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
		mStvText.startAnimation(animation);
	}

	public static interface IAnimationEnd {
		void onAnimationEnd(Animation animation);
	}
}
