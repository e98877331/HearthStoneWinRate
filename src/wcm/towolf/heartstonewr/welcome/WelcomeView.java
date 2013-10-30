package wcm.towolf.heartstonewr.welcome;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import itri.u9lab.towolf.ratiofixer.RatioRelativeLayout;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class WelcomeView extends RatioRelativeLayout implements ViewSwitcher.ViewFactory {
	
	public ImageView switchImageView;
	
	public ImageSwitcher switcher;

	public TextView titleTextView;
	public EditText titleEditText;
	
	
	private Context context;
	
	RatioFixer rf;
	
	public WelcomeView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		this.context = context;
		
		this.setBackgroundColor(Color.WHITE);
		
		rf = this.getRatioFixer();
		
		switcher = new ImageSwitcher(context);
		switcher.setFactory(this);
		
		this.addView(switcher, 418, 599, 175, 315);

	}
	


	@Override
	public View makeView() {
		ImageView iv = new ImageView(context);
		// TODO Auto-generated method stub
		return iv;
	}
}
