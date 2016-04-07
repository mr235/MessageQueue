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

public class MainActivity extends AppCompatActivity {

	private LinearLayout linearLayout;

	private List<Integer> data = new ArrayList<>();

	private Random random = new Random();

	private Handler handler = new Handler();

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
				findViewById(R.id.tv).startAnimation(animation);
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

//		generateMessage();
//		handleMessage();
	}

	private void handleMessage() {
		if (data.size()>0) {
			System.out.println(data.size() + " ---------------------");
			TextView textView = new TextView(this);
			final Integer integer = data.get(0);
			textView.setText(integer + "");
			textView.setTextColor(Color.RED);
//			if (linearLayout.getChildCount()==2) {
//				linearLayout.removeViewAt(0);
//			}
			linearLayout.addView(textView);
			Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
			animation.setAnimationListener(new Animation.AnimationListener() {
				@Override
				public void onAnimationStart(Animation animation) {

				}

				@Override
				public void onAnimationEnd(Animation animation) {
					data.remove(integer);
					handleMessage();
				}

				@Override
				public void onAnimationRepeat(Animation animation) {

				}
			});
			textView.startAnimation(animation);
		} else {
		}
	}

	private void generateMessage() {
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				data.add(random.nextInt(1000));
				if (data.size()==1) {
					handleMessage();
				}
				generateMessage();
			}
		}, random.nextInt(100));
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
