package com.mr235.messagequeue;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

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


		findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final TextView textView = new TextView(getApplicationContext());
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				lp.setMargins(30, 30, 30, 30);
				textView.setLayoutParams(lp);
				textView.setText("2");
				textView.setTextColor(Color.RED);
//			if (linearLayout.getChildCount()==2) {
//				linearLayout.removeViewAt(0);
//			}
				linearLayout.addView(textView);
				final Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
				animation2.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationStart(Animation animation) {

					}

					@Override
					public void onAnimationEnd(Animation animation) {
					}

					@Override
					public void onAnimationRepeat(Animation animation) {

					}
				});
				textView.startAnimation(animation2);
			}
		});

		generateMessage();
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
				final TextView textView = new TextView(getApplicationContext());
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				lp.setMargins(30, 30, 30, 30);
				textView.setLayoutParams(lp);
				textView.setText("2");
				textView.setTextColor(Color.RED);
//			if (linearLayout.getChildCount()==2) {
//				linearLayout.removeViewAt(0);
//			}
				linearLayout.addView(textView);
				final Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
				animation2.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationStart(Animation animation) {

					}

					@Override
					public void onAnimationEnd(Animation animation) {
					}

					@Override
					public void onAnimationRepeat(Animation animation) {

					}
				});
				textView.startAnimation(animation2);
				break;
		}
	}
}
