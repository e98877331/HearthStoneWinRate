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
		easyTracker.send(MapBuilder.createEvent(category, // Event category
																// (required)
				action, // Event action (required)
				label, // Event label
				value) // Event value
				.build());
		
	}
	

	
}
