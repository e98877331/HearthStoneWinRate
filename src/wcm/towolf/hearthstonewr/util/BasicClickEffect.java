package wcm.towolf.hearthstonewr.util;

import android.graphics.LightingColorFilter;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class BasicClickEffect  implements OnTouchListener{


		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			
			if (event.getAction() == MotionEvent.ACTION_DOWN)
				v.getBackground().setColorFilter(
						new LightingColorFilter(0xFF999999, 0xFF000000));
			else if(event.getAction() ==MotionEvent.ACTION_UP) 
				v.getBackground().clearColorFilter();
			return false;
		}		
		
		
		
		static public void setClickEffect(Button btn)
		{
			btn.setOnTouchListener(new BasicClickEffect());
		}
	
}
