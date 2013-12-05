package wcm.towolf.heartstonewr.detail;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import itri.u9lab.towolf.ratiofixer.RatioRelativeLayout;
import wcm.towolf.hearthstonewr.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils.TruncateAt;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DetailView extends RatioRelativeLayout{

	public TextView titleTextView;
	public ImageView mainImageView;
	public TextView winRateTextView;
	public TextView winCounterTextView;
	public TextView loseCounterTextView;
	public TextView totalCounterTextView;

	public Button winButton;
	public Button loseButton;
	public Button undoButton;
//	public Button loseDecreaseButton;
	
	RatioFixer rf;
	
	// data
	float winRate = 0.0f;
	int winCounter = 0;
	int loseCounter = 0;
	int totalCounter = 0;
	
	public ListView mListView;
	public DetailView(Context context) {
		super(context);
		
		this.setBackgroundResource(R.drawable.detail_bg);
		
		rf = this.getRatioFixer();
		
		titleTextView = new TextView(context);
//		titleTextView.setText("TITLE");
		titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 4.45f * 20.0f * rf.getRatio());
		titleTextView.setTextColor(Color.parseColor("#F3E5AB"));
//		titleTextView.setBackgroundColor(Color.parseColor("#FFC1E0"));
		titleTextView.setBackgroundResource(R.drawable.rect_label);
		titleTextView.setGravity(Gravity.CENTER);
		//titleTextView.setSingleLine();
		this.addView(titleTextView, 768, 200, 0, 0);
		//cyy:become marquee textview
		titleTextView.setEllipsize(TruncateAt.MARQUEE);
		titleTextView.setSelected(true);
		titleTextView.setSingleLine(true);
        int wpadding = rf.getRealValue(25);
        titleTextView.setPadding(wpadding, 0, wpadding, 0);
		
		titleTextView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() ==MotionEvent.ACTION_DOWN)
					  v.getBackground().setColorFilter(new LightingColorFilter(0xFF999999, 0xFF000000));
				else if(event.getAction() ==MotionEvent.ACTION_UP)
					v.getBackground().clearColorFilter();
				return false;
			}
		});
		
		mainImageView = new ImageView(context);
//		mainImageView.setBackgroundResource(R.drawable.ic_launcher);
//		mainImageView.setAlpha(0.3f);
//		this.addView(mainImageView, 512, 768, 128, 200);
		this.addView(mainImageView, 471, 675, 148, 200);
		
		winRateTextView = new TextView(context);
//		winRateTextView.setText(Float.toString(winRate * 100) + "%");
		winRateTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 4.45f * 35.0f * rf.getRatio());
		winRateTextView.setTextColor(Color.parseColor("#FFF8C6"));
		winRateTextView.setTypeface(Typeface.SERIF);
//		winRateTextView.setBackgroundColor(Color.parseColor("#96FED1"));
		winRateTextView.setGravity(Gravity.CENTER);
		this.addView(winRateTextView, 768, 568, 0, 200);
		
		winCounterTextView = new TextView(context);
//		winCounterTextView.setText("W: " + Integer.toString(winCounter));
		winCounterTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 4.45f * 10.0f * rf.getRatio());
//		winCounterTextView.setBackgroundColor(Color.parseColor("#CECEFF"));
		winCounterTextView.setGravity(Gravity.CENTER);
		this.addView(winCounterTextView, 256, 200, 0, 768);
		
		loseCounterTextView = new TextView(context);
//		loseCounterTextView.setText("L: " + Integer.toString(loseCounter));
		loseCounterTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 4.45f * 10.0f * rf.getRatio());
//		loseCounterTextView.setBackgroundColor(Color.parseColor("#FFFFB9"));
		loseCounterTextView.setGravity(Gravity.CENTER);
		this.addView(loseCounterTextView, 256, 200, 256, 768);
		
		totalCounterTextView = new TextView(context);
//		totalCounterTextView.setText("Total: " + Integer.toString(totalCounter));
		totalCounterTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 4.45f * 10.0f * rf.getRatio());
//		totalCounterTextView.setBackgroundColor(Color.parseColor("#E2C2DE"));
		totalCounterTextView.setGravity(Gravity.CENTER);
		this.addView(totalCounterTextView, 256, 200, 512, 768);
		
		winButton = new Button(context);
		winButton.setText(getResources().getString(R.string.detail_win_btn));
		winButton.setBackgroundResource(R.drawable.rect_label);
		this.addView(winButton, 363, 140, 10, 968);
		winButton.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() ==MotionEvent.ACTION_DOWN)
					  v.getBackground().setColorFilter(new LightingColorFilter(0xFF999999, 0xFF000000));
				else if(event.getAction() ==MotionEvent.ACTION_UP)
					v.getBackground().clearColorFilter();
				return false;
			}
		});
		
		loseButton = new Button(context);
		loseButton.setText(getResources().getString(R.string.detail_lose_btn));
		loseButton.setBackgroundResource(R.drawable.rect_label);
		this.addView(loseButton, 363, 140, 395, 968);
		loseButton.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() ==MotionEvent.ACTION_DOWN)
					  v.getBackground().setColorFilter(new LightingColorFilter(0xFF999999, 0xFF000000));
				else if(event.getAction() ==MotionEvent.ACTION_UP)
					v.getBackground().clearColorFilter();
				return false;
			}
		});
		
		undoButton = new Button(context);
		undoButton.setText(getResources().getString(R.string.detail_undo_btn));
		undoButton.setBackgroundResource(R.drawable.rect_label);
		this.addView(undoButton, 224, 102, 272, 1118);
		undoButton.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() ==MotionEvent.ACTION_DOWN)
					  v.getBackground().setColorFilter(new LightingColorFilter(0xFF999999, 0xFF000000));
				else if(event.getAction() ==MotionEvent.ACTION_UP)
					v.getBackground().clearColorFilter();
				return false;
			}
		});
	}
}
