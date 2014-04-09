package wcm.towolf.hearthstonewr.view.worlddata;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import itri.u9lab.towolf.ratiofixer.RatioRelativeLayout;
import wcm.towolf.hearthstonewr.R;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class PicsView extends RatioRelativeLayout {
	
	TextView textView;
	CheckBox checkBox;
	public Button button;
	
	RatioFixer mRf;
	Context mContext;

	public PicsView(Context context) {
		super(context);
		
		mRf = getRatioFixer();
		
		setBackgroundResource(R.drawable.main_bg);
		
		textView = new TextView(context);
		textView.setText(getResources().getString(R.string.world_data_pics));
		textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 4.45f * 10.0f * mRf.getRatio());
		textView.setTextColor(Color.WHITE);
		textView.setGravity(Gravity.CENTER);
		addView(textView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0);
		
		checkBox = new CheckBox(context);
		checkBox.setText(getResources().getString(R.string.world_data_pics_agree));
		checkBox.setTextSize(TypedValue.COMPLEX_UNIT_PX, 4.45f * 10.0f * mRf.getRatio());
		checkBox.setTextColor(Color.WHITE);
		addView(checkBox, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 300, 500);
		
		button = new Button(context);
		button.setText(getResources().getString(R.string.world_data_pics_confirm));
		button.setTextSize(TypedValue.COMPLEX_UNIT_PX, 4.45f * 10.0f * mRf.getRatio());
		button.setTextColor(Color.BLACK);
		addView(button, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 300, 700);
	}
	
	public boolean isChecked() {
		return checkBox.isChecked();
	}

}
