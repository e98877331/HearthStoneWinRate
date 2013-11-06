package wcm.towolf.heartstonewr.detail;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import itri.u9lab.towolf.ratiofixer.RatioRelativeLayout;
import wcm.towolf.hearthstonewr.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DetailView extends RatioRelativeLayout{

	public TextView titleTextView;
//	public EditText titleEditText;
	
	
	public ImageView mainImageView;
	public TextView winRateTextView;
	public TextView winCounterTextView;
	public TextView loseCounterTextView;
	public TextView totalCounterTextView;
	public Button winButton;
	public Button loseButton;
	public Button winDecreaseButton;
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
		// TODO Auto-generated constructor stub
		
//		this.setBackgroundColor(Color.WHITE);
		this.setBackgroundResource(R.drawable.main_bg);
		
		rf = this.getRatioFixer();
		
		titleTextView = new TextView(context);
		titleTextView.setText("TITLE");
		titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 4.45f * 30.0f * rf.getRatio());
		titleTextView.setBackgroundColor(Color.parseColor("#FFC1E0"));
		titleTextView.setGravity(Gravity.CENTER);
		this.addView(titleTextView, 768, 200, 0, 0);
		
//		titleEditText = new EditText(context);
//		titleEditText.setText("TITLE");
//		titleEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, 4.45f * 40.0f * rf.getRatio());
//		titleEditText.setBackgroundColor(Color.parseColor("#FFC1E0"));
//		titleEditText.setGravity(Gravity.CENTER);
//		titleEditText.setVisibility(View.INVISIBLE);
//		this.addView(titleEditText, 768, 200, 0, 0);
		
		mainImageView = new ImageView(context);
//		mainImageView.setBackgroundResource(R.drawable.ic_launcher);
//		mainImageView.setAlpha(0.3f);
		this.addView(mainImageView, 768, 768, 0, 200);
		
		winRateTextView = new TextView(context);
//		winRateTextView.setText(Float.toString(winRate * 100) + "%");
		winRateTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 4.45f * 30.0f * rf.getRatio());
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
		winButton.setText("win");
		winButton.setBackgroundResource(R.drawable.detail_button);
		this.addView(winButton, 383, 150, 0, 968);
		
		loseButton = new Button(context);
		loseButton.setText("lose");
		loseButton.setBackgroundResource(R.drawable.detail_button);
		this.addView(loseButton, 383, 150, 385, 968);
		
		winDecreaseButton = new Button(context);
//		winDecreaseButton.setText("undo");
		winDecreaseButton.setBackgroundResource(R.drawable.detail_back);
		this.addView(winDecreaseButton, 112, 112, 328, 1118);
		
//		loseDecreaseButton = new Button(context);
//		loseDecreaseButton.setText("-");
//		this.addView(loseDecreaseButton, 384, 112, 384, 1118);
		
		// controller
		
//		winButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				winCounter++;
//				totalCounter++;
//				winRate = (float) winCounter / (float) totalCounter;
//				
//				refresh();
//			}
//		});
		
//		loseButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				loseCounter++;
//				totalCounter++;
//				
//				refresh();
//			}
//		});
		
//		winDecreaseButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				if (winCounter > 0) {
//					winCounter--;
//					totalCounter--;
//				
//					refresh();
//				}
//			}
//		});
		
//		loseDecreaseButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				if (loseCounter > 0) {
//					loseCounter--;
//					totalCounter--;
//				
//					refresh();
//				}
//			}
//		});
		
//		refresh();
	}
	
//	private void refresh() {
//		if (totalCounter == 0) {
//			winRate = 0;
//		} else {
//			winRate = (float) winCounter / (float) totalCounter;
//		}
//		DecimalFormat fnum = new DecimalFormat("##0.00");
//		String dd = fnum.format(winRate * 100);
//		
//		winRateTextView.setText(dd + "%");
//		winCounterTextView.setText("W: " + Integer.toString(winCounter));
//		loseCounterTextView.setText("L: " + Integer.toString(loseCounter));
//		totalCounterTextView.setText("Total: " + Integer.toString(totalCounter));
//	}

}
