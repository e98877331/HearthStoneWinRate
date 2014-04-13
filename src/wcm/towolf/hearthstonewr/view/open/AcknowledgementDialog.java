package wcm.towolf.hearthstonewr.view.open;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import wcm.towolf.hearthstonewr.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils.TruncateAt;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AcknowledgementDialog {

	private final static AcItem[] mData = {new AcItem("Dirty Mike","5.0 USD",5.0f),
									new AcItem("Weichi","1.0 USD",1.0f),
//									new AcItem("chinyang","1.0 USD",1.0f),
//									new AcItem("Lu","1.0 USD",1.0f),
//									new AcItem("Ethan","1.0 USD",1.0f),
//									new AcItem("Sue-Ming Yeh","2.0 USD",2.0f),
//									new AcItem("Monika","1.0 USD",1.0f),
//									new AcItem("Freedom","1.0 USD",1.0f),
//									new AcItem("Steve","1.0 USD",1.0f),
//									new AcItem("Roger","1.0 USD",1.0f),
//									new AcItem("Towolf","5.0 USD",5.0f),
									};
	static
	{
		float sum = 0;
		for(int i = 0; i< mData.length; i++)
		{
			sum += mData[i].value;
		}
		for(int i = 0; i< mData.length; i++)
		{
			double v = Math.sqrt(100*(mData[i].value/sum)) *10;
			mData[i].valuePercentageString =   String.format("%.1f",v) ;
		}
		
	}
	
	
	static RatioFixer mRF = RatioFixer.getGlobalRatioFixer();
	
	public static void show(Context context)
	{
//		 AlertDialog.Builder dialog = new AlertDialog.Builder(context);
//		 dialog.setTitle(R.string.open_dialog_acknowledgements_title); //設定dialog 的title顯示內容
//		 
//		 dialog.setIcon(android.R.drawable.ic_dialog_info);//設定dialog 的ICON
//		 dialog.setCancelable(true); //關閉 Android 系統的主要功能鍵(menu,home等...)
		 Dialog dialog = new Dialog(context);
		 
		 
		 DialogView dv = new DialogView(context);
		 dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		 dialog.setContentView(dv,mRF.getLayoutParam(700, 900, 0,0));
		 
		 //dialog.setView(dv);
		 
		 
//		 dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {  
//			    public void onClick(DialogInterface dialog, int which) {
//			    	//BasicClickEffect.setClickEffect((Button)view);
//			    
//			    }  
//			}); 
		 //dialog.
		 dialog.show();
			Window window = dialog.getWindow();

			window.setLayout(mRF.getRealValue(750), mRF.getRealValue(950));
	}
	
	private static class DialogView extends RelativeLayout
	{

		TextView mTitle;
		TextView mNameTitle,mValueTitle;
		ListView mListView;
		
		public DialogView(final Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			
			//this.setLayoutParams(rf.getLayoutParam(700, 800, 0,0));
			this.setBackgroundResource(R.drawable.detail_bg);
			
			
			mTitle = new TextView(context);
			mTitle.setText(R.string.open_dialog_acknowledgements_title);
			mTitle.setGravity(Gravity.CENTER);
			mTitle.setBackgroundResource(R.drawable.rect_label);
			mTitle.setTextColor(Color.parseColor("#000000"));
			mTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,mRF.getRealValue(50));
			
			
			mNameTitle = new TextView(context);
			mNameTitle.setText("Name");
			mNameTitle.setGravity(Gravity.CENTER);
			mNameTitle.setBackgroundResource(R.drawable.rect_leather_label);
			mNameTitle.setTextColor(Color.parseColor("#000000"));
			mNameTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,mRF.getRealValue(30));
			mNameTitle.setEllipsize(TruncateAt.MARQUEE);
	        mNameTitle.setSelected(true);
	        mNameTitle.setSingleLine(true);
	        int wpadding = mRF.getRealValue(15);
	        mNameTitle.setPadding(wpadding, 0, wpadding, 0);
			
			mValueTitle = new TextView(context);
			mValueTitle.setText("Donation");
			mValueTitle.setGravity(Gravity.CENTER);
			mValueTitle.setBackgroundResource(R.drawable.rect_leather_label);
			mValueTitle.setTextColor(Color.parseColor("#000000"));
			mValueTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,mRF.getRealValue(30));
			
			
			this.addView(mNameTitle,mRF.getLayoutParam(345, 100, 8, 155));
			this.addView(mValueTitle,mRF.getLayoutParam(345, 100, 347, 155));
			
			
			
			
			mListView = new ListView(context);
			mListView.setVerticalScrollBarEnabled(false);
			mListView.setDivider(null);
			mListView.setAdapter(new MyAdapter(context));
			
			
			
			
			
			this.addView(mListView,mRF.getLayoutParam(700, 650, 0, 250));
			//overlapping
			this.addView(mTitle,mRF.getLayoutParam(700, 165, 2, 0));
			
			
		}
		
		
		
	}
	
	private static class MyListItem extends RelativeLayout
	{

		TextView mName, mValue;
		public MyListItem(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			
//			this.setLayoutParams(mRF.getLayoutParam(700, 150, 0, 0));
			RelativeLayout rl = new RelativeLayout(context);
			rl.setBackgroundResource(R.drawable.main_list_item_bg);
			
			
			
			mName = new TextView(context);
			mName.setText("unsetName");
			mName.setGravity(Gravity.CENTER);
			mName.setBackgroundResource(R.drawable.rect_label);
			mName.setText("roleName");
			mName.setTextColor(Color.parseColor("#F3E5AB"));
			mName.setTextSize(TypedValue.COMPLEX_UNIT_PX,mRF.getRealValue(30));
			mName.setEllipsize(TruncateAt.MARQUEE);
	        mName.setSelected(true);
	        mName.setSingleLine(true);
	        int wpadding = mRF.getRealValue(15);
	        mName.setPadding(wpadding, 0, wpadding, 0);
			
			mValue = new TextView(context);
			mValue.setText("unsetValue");
			mValue.setGravity(Gravity.CENTER);
			mValue.setBackgroundResource(R.drawable.rect_label);
			mValue.setText("roleName");
			mValue.setTextColor(Color.parseColor("#F3E5AB"));
			mValue.setTextSize(TypedValue.COMPLEX_UNIT_PX,mRF.getRealValue(30));
			
			rl.addView(mName,mRF.getLayoutParam(345, 100, 5, 5));
			rl.addView(mValue,mRF.getLayoutParam(345, 100, 350, 5));
//			rl.addView(mName,mRF.getLayoutParam(690, 100, 5, 5));
			
			this.addView(rl, mRF.getLayoutParam(700, 110, 0, 0));
			
		}
		public void setData(String name, String value)
		{
			mName.setText(name);
			mValue.setText(value);
		}
		
		public void setData(AcItem item)
		{
			mName.setText(item.name);
			mValue.setText(item.valueString);
		}
		
		
		
	}
	
	private static class MyAdapter extends BaseAdapter
	{

		Context mContext;
		public MyAdapter(Context context) {
			// TODO Auto-generated constructor stub
			mContext = context;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return mData[arg0];
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int index, View convertView, ViewGroup arg2) {
			// TODO Auto-generated method stub
			
			

			if (convertView == null) {
				convertView = new MyListItem(mContext);
			}

			AcItem item = mData[index];

			((MyListItem)convertView).setData(item);
			
			return convertView;
			
			
		}
		
	}
	
	private static class AcItem 
	{
		String name;
		String valueString;
		float value;
		String valuePercentageString;
		public AcItem(String name,String valueString,float value)
		{
			this.name = name;
			this.valueString = valueString;
			this.value = value;
		}
		
		
		
	}
	
}
