package wcm.towolf.heartstonewr.main;

import wcm.towolf.hearthstonewr.view.HeroChooseView.ClickCallBack;
import itri.u9lab.towolf.ratiofixer.RatioFixer;
import android.app.Dialog;
import android.content.Context;
import android.view.Window;

public class CreateDeckDialog extends Dialog {

	CreateDeckView mView;
	RatioFixer mRF;
	
	public CreateDeckDialog(Context context,RatioFixer rf) {
		super(context);
		// TODO Auto-generated constructor stub
		mRF = rf;
		mView = new CreateDeckView(context,mRF);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(mView);
	}

	public void showAndAdjustWindow()
	{
		this.show();
		Window window = this.getWindow();

		window.setLayout(mRF.getRealValue(710), mRF.getRealValue(1200));
	}
	
	public String getDeckName()
	{
	   return mView.getDeckName();
	}
	
	public void setItemClickListener(ClickCallBack callback)
	{
		mView.setItemClickListener(callback);
	}
	
	
	
}
