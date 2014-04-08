package wcm.towolf.hearthstonewr.view.open;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import itri.u9lab.towolf.ratiofixer.RatioRelativeLayout;
import wcm.towolf.hearthstonewr.R;
import wcm.towolf.hearthstonewr.util.BasicClickEffect;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class OpenView extends RatioRelativeLayout {

	public ImageView ackAnimation;

	
	public Button normalBtn;
	public Button arenaBtn;
	public Button exportBtn;
	public Button worldDataBtn;
	public Button ackBtn;
	
	
	public Button showPatchNoteBtn;
	public Button donateBtn;

	private AnimatorSet mAckAnimSet;
	public OpenView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.setBackgroundResource(R.drawable.main_bg);

		final int ystand = 150;
		final int ydistance = 160;



		normalBtn = new Button(context);
		normalBtn.setBackgroundResource(R.drawable.rect_label);
		normalBtn.setText(R.string.open_normal_game);
		BasicClickEffect.setClickEffect(normalBtn);
		this.addView(normalBtn, 428, 122, 172, ystand);

		arenaBtn = new Button(context);
		arenaBtn.setBackgroundResource(R.drawable.rect_label);
		arenaBtn.setText(R.string.open_arena_game);
		BasicClickEffect.setClickEffect(arenaBtn);
		this.addView(arenaBtn, 428, 122, 172, ystand + ydistance);

		exportBtn = new Button(context);
		exportBtn.setBackgroundResource(R.drawable.rect_label);
		exportBtn.setText(R.string.open_export_csv);
		exportBtn.setClickable(false);
		exportBtn.setAlpha(0.5f);
		this.addView(exportBtn, 428, 122, 172, ystand + 2 * ydistance);

		worldDataBtn = new Button(context);
		worldDataBtn.setBackgroundResource(R.drawable.rect_label);
		worldDataBtn.setText(R.string.open_world_data);
		worldDataBtn.setClickable(false);
		this.addView(worldDataBtn, 428, 122, 172, ystand + 3 * ydistance);
		
		ackBtn = new Button(context);
		ackBtn.setBackgroundResource(R.drawable.rect_label);
		ackBtn.setText(R.string.open_acknowledgements);
		BasicClickEffect.setClickEffect(ackBtn);
		this.addView(ackBtn, 428, 122, 172, ystand + 4 * ydistance);
		addAckAnimation(100, 110, 172, ystand + 6 + 4 * ydistance);
		
		
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

	private void addAckAnimation(int width, int height, int x, int y) {
		ackAnimation = new ImageView(this.getContext());
		ackAnimation.setImageResource(R.drawable.mask);
		ackAnimation.setAlpha(0f);
		this.addView(ackAnimation, 100, 110, x, y);
		RatioFixer rf = this.getRatioFixer();

		ValueAnimator squashAnim1 = ObjectAnimator.ofFloat(ackAnimation, "x",
				rf.getRealValue(172), rf.getRealValue(500));
		squashAnim1.setDuration(1000);
		// squashAnim1.setRepeatCount(1);
		squashAnim1.setRepeatMode(ValueAnimator.RESTART);
		squashAnim1.setInterpolator(new AccelerateDecelerateInterpolator());
		// squashAnim1.start();

		ObjectAnimator animAlpha1 = ObjectAnimator.ofFloat(ackAnimation, "alpha", 0f, 1f);
		animAlpha1.setDuration(300);
		animAlpha1.setRepeatMode(ValueAnimator.RESTART);

		ObjectAnimator animAlpha2 = ObjectAnimator.ofFloat(ackAnimation, "alpha", 1f, 0f);
		animAlpha2.setDuration(300);
		animAlpha2.setStartDelay(700);
		animAlpha2.setRepeatMode(ValueAnimator.RESTART);

		mAckAnimSet = new AnimatorSet();
		mAckAnimSet.playTogether(squashAnim1, animAlpha1, animAlpha2);
		mAckAnimSet.setStartDelay(2000);

		mAckAnimSet.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				mAckAnimSet.start();
			}

		});
		//mAckAnimSet.start();
		
		// gd.startAnimation(animationSet);
	}
	
	public void stopAnimation()
	{
		mAckAnimSet.cancel();
		ackAnimation.clearAnimation();
	}
	
	public void startAnimation()
	{
		mAckAnimSet.start();
	}
}
