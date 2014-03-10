package wcm.towolf.hearthstonewr.view.open;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import wcm.towolf.hearthstonewr.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class DonateDialog{

    
	public static void show(Context context)
	{
		 AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		 dialog.setTitle(R.string.dialog_donate_title); //設定dialog 的title顯示內容
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

		ImageView paypalIcon;
		TextView tv;
		
		public DialogView(final Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			
			RatioFixer rf = RatioFixer.getGlobalRatioFixer();
			this.setLayoutParams(rf.getLayoutParam(700, 800, 0,0));
			
			paypalIcon = new ImageView(context);
			paypalIcon.setBackgroundResource(R.drawable.paypal_logo);
			paypalIcon.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
			          Intent ie = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=QWQWYSVTRYM8Y"));
			          context.startActivity(ie);
				//https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=QWQWYSVTRYM8Y	
				}
			});
			
			this.addView(paypalIcon,rf.getLayoutParam(300, 120, 20, 0));
			
			ScrollView sv = new ScrollView(context);
			
			tv = new TextView(context);
			tv.setText(R.string.dialog_donate_content);
			
			sv.addView(tv);
			
			this.addView(sv,rf.getLayoutParam(660, 580, 20, 120));
			
			
			
			
		}
		
	}

}
