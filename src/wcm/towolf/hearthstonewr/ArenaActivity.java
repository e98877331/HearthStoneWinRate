package wcm.towolf.hearthstonewr;

import java.util.ArrayList;
import java.util.Random;

import wcm.towolf.hearthstonewr.model.ArenaEventDataProvider;
import wcm.towolf.hearthstonewr.model.datatype.arena.ArenaEventData;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class ArenaActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		final ArenaEventDataProvider provider = new ArenaEventDataProvider();
		
		final Random rd = new Random();
		
		LinearLayout rl = new LinearLayout(this);
		
		
	    Button testBtn = new Button(this);
	    testBtn.setText("testbun");
	    testBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			
			   
			   provider.addEvent(rd.nextInt(7));
			   provider.addEvent(rd.nextInt(7));
			   provider.addEvent(rd.nextInt(7));
			   provider.addEvent(rd.nextInt(7));
			   
			}
		});
	    
	    Button testBtn2 = new Button(this);
	    testBtn2.setText("testbtn2");
	    testBtn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			   provider.addGame(rd.nextInt(4), rd.nextInt(7), rd.nextBoolean());
			}
		});
	    
	    
	    Button testBtn3 = new Button(this);
	    testBtn3.setText("testbtn3");
	    testBtn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ArrayList<ArenaEventData> data =  provider.getAllArenaEventData();
				int a = 5;
				a = a+1;
			}
		});
	    
	    rl.addView(testBtn);
	    rl.addView(testBtn2);
	    rl.addView(testBtn3);
	    setContentView(rl);
	}
}
