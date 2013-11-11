package wcm.towolf.heartstonewr.main;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import wcm.towolf.hearthstonewr.R;
import wcm.towolf.hearthstonewr.view.HeroChooseView;
import wcm.towolf.hearthstonewr.view.HeroChooseView.ClickCallBack;
import android.content.Context;
import android.text.InputFilter;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CreateDeckView extends RelativeLayout{

	RatioFixer mRF;
ImageView mBGView;	
	
	TextView mTitle;
	EditText mName;
	HeroChooseView mChooseView;
	public CreateDeckView(final Context context,RatioFixer rf) {
		super(context);
		// TODO Auto-generated constructor stub
		//this.setBackgroundResource(R.drawable.main_create_bg);
		
		mRF = rf;
		
		mBGView = new ImageView(context);
		mBGView.setImageResource(R.drawable.main_create_bg);
		mBGView.setScaleType(ScaleType.FIT_XY);
		this.addView(mBGView,mRF.getLayoutParam(750, 1150, 0, 0));
		
		mTitle = new TextView(context);
		mTitle.setGravity(Gravity.CENTER);
		mTitle.setText(R.string.main_create_view_title);
		mTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,mRF.getRealValue(50));
		//let mNmae not to auto focus
		mTitle.setFocusable(true);
		mTitle.setFocusableInTouchMode(true);
		this.addView(mTitle,mRF.getLayoutParam(630, 100, 10, 10));
		
		mName = new EditText(context);
		mName.setId(1);
		mName.setBackgroundResource(R.drawable.rect_leather_label);
		mName.setGravity(Gravity.CENTER); 
		mName.setText(R.string.main_create_view_name);
		mName.setSelectAllOnFocus(true);
		mName.setFilters( new InputFilter[] {
				   new InputFilter.LengthFilter(12)});
		mName.setSingleLine();
		mName.setImeOptions(EditorInfo.IME_ACTION_DONE);
//		mName.setInputType(1);
//		mName.setOnKeyListener(new OnKeyListener() {
//		    public boolean onKey(View v, int keyCode, KeyEvent event) {
//		        // If the event is a key-down event on the "enter" button
//		        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
//		            (keyCode == KeyEvent.KEYCODE_ENTER)) {
//		          // Perform action on key press
//		          Toast.makeText(context, mName.getText(), Toast.LENGTH_SHORT).show();
//		          return true;
//		        }
//		        return false;
//		    }
//		});
		
		
//		mName.setOnEditorActionListener(new OnEditorActionListener() {
//
//		    @Override
//		    public boolean onEditorAction(TextView v, int keyCode,
//		            KeyEvent event) {
//		    	
//		    	Log.e("dfdf",Integer.toString(keyCode));
//		         if ( (event.getAction() == KeyEvent.ACTION_DOWN  ) &&
//		             (keyCode == KeyEvent.KEYCODE_ENTER)   )
//		        {               
//		           // hide virtual keyboard
//		           InputMethodManager imm = 
//		              (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//		           imm.hideSoftInputFromWindow(mName.getWindowToken(), 0);
//		           return true;
//		        }
//		        return false;
//		    }
//		});
		this.addView(mName,mRF.getLayoutParam(630, 100, 10, 110));
		
		
		
		mChooseView = new HeroChooseView(context, rf);
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

}
