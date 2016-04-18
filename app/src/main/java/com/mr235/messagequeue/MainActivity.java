package com.mr235.messagequeue;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private LinearLayout linearLayout;

	private List<String> data = new ArrayList<>();

	private Random random = new Random();

	private Handler handler = new Handler();

	private final static int DEFAULT_COUNT = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		linearLayout = (LinearLayout) findViewById(R.id.ll);
		findViewById(R.id.tv);
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
		animation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// findViewById(R.id.tv).startAnimation(animation);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
		findViewById(R.id.tv).startAnimation(animation);


		findViewById(R.id.bt).setOnClickListener(this);

		//generateMessage();
		handleMessage();
	}

	private void handleMessage() {
		int childCount = linearLayout.getChildCount();
		if (handleValid(childCount)) {
			View view = null;
			for (int i = 0; i < linearLayout.getChildCount(); i++) {
				View child = linearLayout.getChildAt(i);
				if (child instanceof CustomView) {
					if (!((CustomView) child).isAnimating()) {
						view = child;
					}
				}
			}
			if (view == null) {
				view = new CustomView(this);
				linearLayout.addView(view);
			}

			if (view instanceof CustomView) {
				((CustomView) view).setAnimationEndListener(new CustomView.IAnimationEnd() {
					@Override
					public void onAnimationEnd(Animation animation) {

					}
				});
			}

			if (view instanceof CustomView) {
				((CustomView) view).setAnimationEndListener(new CustomView.IAnimationEnd() {
					@Override
					public void onAnimationEnd(Animation animation) {
						handleMessage();
					}
				});
				((CustomView) view).startAnimation(true);
			}
			view.setVisibility(View.VISIBLE);
			data.remove(0);
		}
	}

	private boolean handleValid(int childCount) {
		boolean valid = false;
		if (data.size() > 0) {
			if (childCount < DEFAULT_COUNT) {
				valid = true;
			} else if (childCount > DEFAULT_COUNT) {
				valid = false;
			} else {
				for (int i = 0; i < childCount; i++) {
					View child = linearLayout.getChildAt(i);
					if (child instanceof CustomView) {
						valid = !((CustomView) child).isAnimating();
						if (valid) {
							break;
						}
					}
				}
			}
		}
		return valid;
	}


	private void generateMessage() {
		boolean l = false;
		int delay = 5000;
		int ext = 2000;
		if (!l) {
			delay = 100;
			ext = 0;
		}
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				data.add(random.nextInt(1000) + " ------");
				handleMessage();
				generateMessage();
			}
		}, random.nextInt(delay) + ext);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.bt:
//				Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
//				findViewById(R.id.tv).startAnimation(animation);


				continueSendAnim = new AnimationSet(true);
				scaleAnimation = new ScaleAnimation(3.0f, 1.0f, 3.0f, 1.0f, 1, 0.5f, 1, 0.5f);
				continueSendAnim.addAnimation(scaleAnimation);
				continueSendAnim.addAnimation(new AlphaAnimation(0.0f, 1.0f));
				continueSendAnim.setInterpolator(new ElasticInterpolator(EasingType.Type.OUT));
				continueSendAnim.setDuration(900);
				continueSendAnim.setFillAfter(true);

				findViewById(R.id.tv).startAnimation(continueSendAnim);

				break;
		}
	}


	private ScaleAnimation scaleAnimation;
	private AnimationSet continueSendAnim;// continueSendAnim
}
