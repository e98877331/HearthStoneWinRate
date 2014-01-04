package wcm.towolf.hearthstonewr.view.open;

import itri.u9lab.towolf.ratiofixer.RatioRelativeLayout;
import wcm.towolf.hearthstonewr.R;
import android.content.Context;
import android.widget.Button;

public class OpenView extends RatioRelativeLayout{

	public Button normalBtn;
	public Button arenaBtn;
	
	public OpenView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.setBackgroundResource(R.drawable.main_bg);
		
		normalBtn = new Button(context);
		normalBtn.setBackgroundResource(R.drawable.rect_btn);
		normalBtn.setText("Normal Game");
		this.addView(normalBtn, 388	, 102, 192, 240);
		
		
		arenaBtn = new Button(context);
		arenaBtn.setBackgroundResource(R.drawable.rect_btn);
		arenaBtn.setText("Arena");
		this.addView(arenaBtn, 388	, 102, 192, 400);
		
		
		
	}

}
