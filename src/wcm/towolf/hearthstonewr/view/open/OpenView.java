package wcm.towolf.hearthstonewr.view.open;

import itri.u9lab.towolf.ratiofixer.RatioRelativeLayout;
import wcm.towolf.hearthstonewr.R;
import wcm.towolf.hearthstonewr.util.BasicClickEffect;
import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

public class OpenView extends RatioRelativeLayout{

	public Button ackBtn;
	public Button normalBtn;
	public Button arenaBtn;
    public Button exportBtn;
    
    public Button showPatchNoteBtn;
	public Button donateBtn;
    
    
	public OpenView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.setBackgroundResource(R.drawable.main_bg);
		
		final int ystand = 100;
		final int ydistance = 160;
		
		ackBtn = new Button(context);
		ackBtn.setBackgroundResource(R.drawable.rect_label);
		ackBtn.setText(R.string.open_acknowledgements);
		BasicClickEffect.setClickEffect(ackBtn);
		
//		   final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
//		    animation.setDuration(500); // duration - half a second
//		    animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
//		    animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
//		    animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
//		    ackBtn.startAnimation(animation);
//		    ackBtn.setOnClickListener(new OnClickListener() {
//		        @Override
//		        public void onClick(final View view) {
//		            view.clearAnimation();
//		        }
//		    });
//		
		this.addView(ackBtn, 428, 122, 172, ystand);
		
		normalBtn = new Button(context);
		normalBtn.setBackgroundResource(R.drawable.rect_label);
		normalBtn.setText(R.string.open_normal_game);
		BasicClickEffect.setClickEffect(normalBtn);
		this.addView(normalBtn, 428	, 122, 172, ystand+ydistance);
		
		
		arenaBtn = new Button(context);
		arenaBtn.setBackgroundResource(R.drawable.rect_label);
		arenaBtn.setText(R.string.open_arena_game);
		BasicClickEffect.setClickEffect(arenaBtn);
		this.addView(arenaBtn, 428	, 122, 172, ystand+2*ydistance);
		
		exportBtn = new Button(context);
		exportBtn.setBackgroundResource(R.drawable.rect_label);
		exportBtn.setText(R.string.open_export_csv);
		exportBtn.setClickable(false);
		exportBtn.setAlpha(0.5f);
		this.addView(exportBtn,428, 122, 172, ystand+3*ydistance);
		
		
		
		showPatchNoteBtn = new Button(context);
		showPatchNoteBtn.setBackgroundResource(R.drawable.rect_label);
		showPatchNoteBtn.setText("Patch Note");
		BasicClickEffect.setClickEffect(showPatchNoteBtn);
		this.addView(showPatchNoteBtn, 250, 100, 500, 1130);
		

		donateBtn = new Button(context);
		donateBtn.setBackgroundResource(R.drawable.rect_label);
		donateBtn.setText(R.string.open_donate);
		
		BasicClickEffect.setClickEffect(donateBtn);
		this.addView(donateBtn, 250, 100, 20, 1130);
		
		
		
	}

}
