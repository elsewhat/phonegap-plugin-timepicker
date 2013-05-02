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
 * Phonegap plugin for the a native TimePicker.
 * The timepicker is based on the Android alarm clock from the Android Open Source Project
 * https://android.googlesource.com/platform/packages/apps/DeskClock/
 * 
 * 
 * 
 */
public class TimePickerPlugin extends CordovaPlugin implements TimePickerDialogFragment.TimePickerDialogHandler{
	
	public static final String JS_TIMEPICKER_METHOD = "doTimePicker";
	
	//we assume we only have one instance at a time
	CallbackContext callbackContext;
	
	@Override
	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
	    Log.v("TimePicker", "JS action " + action);
		this.callbackContext=callbackContext;
		
		//only react to the doTimePicker method (from .js this is defined in timepicker.js)
		if (JS_TIMEPICKER_METHOD.equals(action)) {
	    	
	        cordova.getActivity().runOnUiThread(new Runnable() {
	            public void run() {
	            	showTimeEditDialog(cordova.getActivity().getFragmentManager()); 
	            	//Unsure if we should have return a NO_RESULT to the callbackContext here
	            }
	        });
	        return true;
	    }
	    return false;
	}

	/**
	 * Display the TimePicker native component.
	 * 
	 * Uses fragments
	 * 
	 */
    public void showTimeEditDialog(FragmentManager manager) {
        final FragmentTransaction ft = manager.beginTransaction();
        final Fragment prev = manager.findFragmentByTag("time_dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        final TimePickerDialogFragment fragment = TimePickerDialogFragment.newInstance(this);
        fragment.show(ft, "time_dialog");
    }

	
	/**
	 * Called from when the user has selected the number of hours and minutes in the native component.
	 * 
	 * Will send the result to the Phonegap Callback Context
	 * 
	 */
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
