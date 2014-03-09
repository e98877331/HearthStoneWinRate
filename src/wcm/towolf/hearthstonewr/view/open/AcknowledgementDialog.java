package wcm.towolf.hearthstonewr.view.open;

import itri.u9lab.towolf.ratiofixer.RatioFixer;

import java.util.ArrayList;

import wcm.towolf.hearthstonewr.ArenaActivity;
import wcm.towolf.hearthstonewr.R;
import wcm.towolf.hearthstonewr.model.datatype.arena.ArenaEventData;
import wcm.towolf.hearthstonewr.view.arena.ArenaViewListItem;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.TextUtils.TruncateAt;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AcknowledgementDialog {

	private final static AcItem[] mData = {new AcItem("Dirty Mike","5.0 USD"),
									new AcItem("Weichi","1.0 USD"),
									new AcItem("Weichi","1.0 USD"),
									new AcItem("Weichi","1.0 USD"),
									new AcItem("Weichi","1.0 USD"),
									new AcItem("Weichi","1.0 USD"),
									new AcItem("Weichi","1.0 USD"),
									new AcItem("Weichi","1.0 USD"),
									new AcItem("Weichi","1.0 USD"),
									new AcItem("Weichi","1.0 USD"),
									new AcItem("Weichi","1.0 USD"),
									new AcItem("Weichi","1.0 USD"),
									new AcItem("Weichi","1.0 USD"),
									new AcItem("Weichi","1.0 USD"),
									new AcItem("Weichi","1.0 USD"),
									new AcItem("Weichi","1.0 USD"),
									new AcItem("Weichi","1.0 USD"),
									new AcItem("Weichi","1.0 USD"),
									};
	
	static RatioFixer mRF = RatioFixer.getGlobalRatioFixer();
	
	public static void show(Context context)
	{
		 AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		 dialog.setTitle(R.string.open_dialog_acknowledgements_title); //設定dialog 的title顯示內容
		 
		 dialog.setIcon(android.R.drawable.ic_dialog_info);//設定dialog 的ICON
		 dialog.setCancelable(true); //關閉 Android 系統的主要功能鍵(menu,home等...)
//		 dialog.setMessage("aggdagdsgdgdgdg");
//		 ImageView iv = new ImageView(context);
//		 iv.setBackgroundResource(R.drawable.paypal_logo);
		 DialogView dv = new DialogView(context);
		 
		 dialog.setView(dv);
		 
		 
		 dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {  
			    public void onClick(DialogInterface dialog, int which) {
			    	//BasicClickEffect.setClickEffect((Button)view);
			    
			    }  
			}); 
		 dialog.show();
	}
	
	private static class DialogView extends RelativeLayout
	{

		ListView mListView;
		public DialogView(final Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			
			RatioFixer rf = RatioFixer.getGlobalRatioFixer();
			this.setLayoutParams(rf.getLayoutParam(700, 800, 0,0));
			
			mListView = new ListView(context);
			mListView.setDivider(null);
			mListView.setAdapter(new MyAdapter(context));
			
			this.addView(mListView);
			
			
			
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
			mValue.setText(item.value);
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
		String value;
		public AcItem(String name,String value)
		{
			this.name = name;
			this.value = value;
		}
		
	}
	
}
