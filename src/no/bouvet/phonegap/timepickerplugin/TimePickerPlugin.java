package no.bouvet.phonegap.timepickerplugin;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;

import com.android.deskclock.TimePickerDialogFragment;

/**
 * TODO
 */
public class TimePickerPlugin extends CordovaPlugin implements TimePickerDialogFragment.TimePickerDialogHandler{
	
	public static final String JS_TIMEPICKER_METHOD = "doTimePicker";
	CallbackContext callbackContext;
	
	@Override
	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
	    Log.v("TimePicker", "JS action " + action);
		this.callbackContext=callbackContext;
		if (JS_TIMEPICKER_METHOD.equals(action)) {
	    	
	        cordova.getActivity().runOnUiThread(new Runnable() {
	            public void run() {
	            	showTimeEditDialog(cordova.getActivity().getFragmentManager()); 
	            }
	        });
	        return true;
	    }
	    return false;
	}

    public void showTimeEditDialog(FragmentManager manager) {
        final FragmentTransaction ft = manager.beginTransaction();
        final Fragment prev = manager.findFragmentByTag("time_dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        final TimePickerDialogFragment fragment = TimePickerDialogFragment.newInstance(this);
        fragment.show(ft, "time_dialog");
    }

	@Override
	public void onTimeSet(int hours, int minutes) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("hours", hours);
			jsonObject.put("minutes", minutes);
			callbackContext.success(jsonObject);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("TimePicker","Could not create JSON object ",e);
			callbackContext.error("TimePicker entries not valid");
		}
	}
}
