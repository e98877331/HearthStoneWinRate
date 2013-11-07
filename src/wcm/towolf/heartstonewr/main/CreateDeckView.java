package wcm.towolf.heartstonewr.main;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import wcm.towolf.hearthstonewr.view.HeroChooseView;
import wcm.towolf.hearthstonewr.view.HeroChooseView.ClickCallBack;
import android.content.Context;
import android.graphics.Color;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethod;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CreateDeckView extends RelativeLayout{

	RatioFixer mRF;
	TextView mTitle;
	EditText mName;
	HeroChooseView mChooseView;
	public CreateDeckView(final Context context,RatioFixer rf) {
		super(context);
		// TODO Auto-generated constructor stub
		this.setBackgroundColor(Color.GRAY);
		
		mRF = rf;
		
		mTitle = new TextView(context);
		mTitle.setGravity(Gravity.CENTER);
		mTitle.setText("Add New Deck");
		mTitle.setTextSize(mRF.getRealValue(30));
		this.addView(mTitle,mRF.getLayoutParam(630, 100, 0, 0));
		
		mName = new EditText(context);
		mName.setId(1);
//		mName.setBackgroundColor(Color.RED);
		mName.setGravity(Gravity.CENTER); 
		mName.setText("dfadff");
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
		this.addView(mName,mRF.getLayoutParam(630, 100, 0, 100));
		
		
		
		mChooseView = new HeroChooseView(context, rf);
		this.addView(mChooseView,mRF.getLayoutParam(630,930,0,200));
		
		
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
