package wcm.towolf.hearthstonewr.view.open;

import itri.u9lab.towolf.ratiofixer.RatioRelativeLayout;
import wcm.towolf.hearthstonewr.R;
import android.content.Context;
import android.widget.Button;

public class OpenView extends RatioRelativeLayout{

	public Button normalBtn;
	public Button arenaBtn;
    public Button exportBtn;
	
	public OpenView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.setBackgroundResource(R.drawable.main_bg);
		
		normalBtn = new Button(context);
		normalBtn.setBackgroundResource(R.drawable.rect_label);
		normalBtn.setText(R.string.open_normal_game);
		this.addView(normalBtn, 428	, 122, 172, 240);
		
		
		arenaBtn = new Button(context);
		arenaBtn.setBackgroundResource(R.drawable.rect_label);
		arenaBtn.setText(R.string.open_arena_game);
		this.addView(arenaBtn, 428	, 122, 172, 400);
		
		exportBtn = new Button(context);
		exportBtn.setBackgroundResource(R.drawable.rect_label);
		exportBtn.setText("Export csv (Coming SOOOOON)");
		exportBtn.setClickable(false);
		exportBtn.setAlpha(0.5f);
		this.addView(exportBtn,428, 122, 172, 560);
		
	}

}
