package wcm.towolf.hearthstonewr.view.open;

import itri.u9lab.towolf.ratiofixer.RatioRelativeLayout;
import wcm.towolf.hearthstonewr.R;
import wcm.towolf.hearthstonewr.util.BasicClickEffect;
import android.content.Context;
import android.widget.Button;

public class OpenView extends RatioRelativeLayout{

	public Button normalBtn;
	public Button arenaBtn;
    public Button exportBtn;
    
    public Button showPatchNoteBtn;
	public Button donateBtn;
    
    
	public OpenView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.setBackgroundResource(R.drawable.main_bg);
		
		normalBtn = new Button(context);
		normalBtn.setBackgroundResource(R.drawable.rect_label);
		normalBtn.setText(R.string.open_normal_game);
		BasicClickEffect.setClickEffect(normalBtn);
		this.addView(normalBtn, 428	, 122, 172, 240);
		
		
		arenaBtn = new Button(context);
		arenaBtn.setBackgroundResource(R.drawable.rect_label);
		arenaBtn.setText(R.string.open_arena_game);
		BasicClickEffect.setClickEffect(arenaBtn);
		this.addView(arenaBtn, 428	, 122, 172, 400);
		
		exportBtn = new Button(context);
		exportBtn.setBackgroundResource(R.drawable.rect_label);
		exportBtn.setText(R.string.open_export_csv);
		exportBtn.setClickable(false);
		exportBtn.setAlpha(0.5f);
		this.addView(exportBtn,428, 122, 172, 560);
		
		
		
		showPatchNoteBtn = new Button(context);
		showPatchNoteBtn.setBackgroundResource(R.drawable.rect_label);
		showPatchNoteBtn.setText("Patch Note");
		BasicClickEffect.setClickEffect(showPatchNoteBtn);
		this.addView(showPatchNoteBtn, 250, 100, 500, 1130);
		

		donateBtn = new Button(context);
		donateBtn.setBackgroundResource(R.drawable.rect_label);
		donateBtn.setText(R.string.open_donate);
		
		BasicClickEffect.setClickEffect(donateBtn);
		//this.addView(donateBtn, 250, 100, 20, 1130);
		
		
		
	}

}
