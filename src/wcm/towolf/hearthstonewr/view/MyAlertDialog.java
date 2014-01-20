package wcm.towolf.hearthstonewr.view;

import wcm.towolf.hearthstonewr.util.BasicClickEffect;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;

public class MyAlertDialog{



	public static void show(final Context context,int titleID,int contentID)
	{
		 AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		 dialog.setTitle(titleID); //設定dialog 的title顯示內容
		 dialog.setIcon(android.R.drawable.ic_dialog_info);//設定dialog 的ICON
		 dialog.setCancelable(true); //關閉 Android 系統的主要功能鍵(menu,home等...)
		 dialog.setMessage(contentID);
		 dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {  
			    public void onClick(DialogInterface dialog, int which) {
			    	//BasicClickEffect.setClickEffect((Button)view);
			    
			    }  
			}); 
		 dialog.show();
	}
	
}
