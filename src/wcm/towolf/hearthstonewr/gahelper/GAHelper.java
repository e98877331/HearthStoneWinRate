package wcm.towolf.hearthstonewr.gahelper;

import android.content.Context;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;

public class GAHelper {

	
	public static void event(Context ctx,String category,String action,String label,long value)
	{
		EasyTracker easyTracker = EasyTracker.getInstance(ctx);

		// MapBuilder.createEvent().build() returns a Map of event fields and
		// values
		// that are set and sent with the hit.
		easyTracker.send(MapBuilder.createEvent("ui_action", // Event category
																// (required)
				"button_press", // Event action (required)
				"event_start_button", // Event label
				1l) // Event value
				.build());
		
	}
	

	
}
