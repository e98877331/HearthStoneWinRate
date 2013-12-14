package wcm.towolf.heartstonewr.main;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import wcm.towolf.hearthstonewr.R;
import android.content.Context;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class NewDeckButton extends Button{

	public NewDeckButton(Context context, RatioFixer rf) {
		super(context);
		// TODO Auto-generated constructor stub
		this.setBackgroundResource(R.drawable.rect_btn);
		this.setTextColor(Color.parseColor("#F3E5AB"));
		this.setText(R.string.main_create_btn);
		this.setTypeface(Typeface.SERIF);
		
		this.setTextSize(TypedValue.COMPLEX_UNIT_PX,rf.getRealValue(60));
		
		this.setOnTouchListener(new ClickEffectListener());
	}
	
	class ClickEffectListener implements OnTouchListener
	{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if (event.getAction() == MotionEvent.ACTION_DOWN)
				v.getBackground().setColorFilter(
						new LightingColorFilter(0xFF999999, 0xFF000000));
			else 
				v.getBackground().clearColorFilter();
			return false;
		}
		
	}

}
