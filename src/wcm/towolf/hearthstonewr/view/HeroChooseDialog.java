package wcm.towolf.hearthstonewr.view;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import wcm.towolf.hearthstonewr.R;
import wcm.towolf.hearthstonewr.view.HeroChooseView.ClickCallBack;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.text.InputFilter;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HeroChooseDialog extends Dialog {
     
	
	RatioFixer mRF;
	InnerView mView;
	
	public HeroChooseDialog(Context context,RatioFixer rf) {
		super(context);
		// TODO Auto-generated constructor stub
		mRF = rf;
		
		mView = new InnerView(context);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(mView);
//		this.setOnDismissListener(new OnDismissListener() {
//
//			@Override
//			public void onDismiss(DialogInterface dialog) {
//				// TODO Auto-generated method stub
//				isCdvOpen = false;
//			}
//		});

		
	}
	
	public void setInnerTitle(int title)
	{
		mView.mTitle.setText(title);
	}
	
	public void setInnerTitle(String title)
	{
	   	mView.setTitle(title);
	}
	
	public void setItemClickListener(ClickCallBack callBack)
	{
		mView.setItemClickListener(callBack);
	}
	
	public void showAndAdjustWindow()
	{
		this.show();

		Window window = this.getWindow();

		window.setLayout(mRF.getRealValue(710), mRF.getRealValue(1200));

	}
	
	public class InnerView extends RelativeLayout{

		
	ImageView mBGView;	
		
		public TextView mTitle;
		EditText mName;
		HeroChooseView mChooseView;
		public InnerView(final Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			//this.setBackgroundResource(R.drawable.main_create_bg);
			
		
			
			mBGView = new ImageView(context);
			mBGView.setImageResource(R.drawable.main_create_bg);
			mBGView.setScaleType(ScaleType.FIT_XY);
			this.addView(mBGView,mRF.getLayoutParam(750, 1150, 0, 0));
			
			mTitle = new TextView(context);
			mTitle.setGravity(Gravity.CENTER);
			mTitle.setText(R.string.main_create_view_title);
			mTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,mRF.getRealValue(50));
			this.addView(mTitle,mRF.getLayoutParam(630, 100, 10, 10));
			
			mName = new EditText(context);
			mName.setId(1);
//			mName.setBackgroundColor(Color.RED);
			mName.setBackgroundResource(R.drawable.rect_leather_label);
			mName.setGravity(Gravity.CENTER); 
			mName.setText(R.string.main_create_view_name);
			mName.setFilters( new InputFilter[] {
					   new InputFilter.LengthFilter(12)});
			mName.setSingleLine();
			mName.setImeOptions(EditorInfo.IME_ACTION_DONE);

//			this.addView(mName,mRF.getLayoutParam(630, 100, 10, 110));
			
			
			
			mChooseView = new HeroChooseView(context, mRF);
			this.addView(mChooseView,mRF.getLayoutParam(630,930,10,210));
			
			
		}
		
		public void setItemClickListener(ClickCallBack callBack)
		{
			mChooseView.setOnClickItemCallBack(callBack);
		}
		
		
		public String getDeckName()
		{
			return mName.getText().toString();
		}
       
		public void setTitle(String title)
		{
			mTitle.setText(title);
		}
		
	}

}
