# Phonegap plugin - Timepicker #

## Folder structure ## 

* assets\fonts - Fonts used by the native timepicker app
* assets\www\js\timepicker - JS method definition for plugin
* res\ - Android resources used by the plugin (drawables, layouts, styles etc)
* res\xml\config.xml - Phonegap config file which must refer to the plugin
* src\com\android\deskclock\.. - Source code for the Timepicker from the Android Opensource project
* src\no\bouvet\phonegap\timepickerplugin - Source code for the Phonegap plugin (instance of CordovaPlugin)

## Usage ##

        <script type="text/javascript" src="js/timepicker.js"></script>
        <script type="text/javascript">
		   var timepicker = cordova.require("cordova/plugin/timepicker");
		   
		   var onTimePickerSuccess  = function(result) { 
		   	   		var elem = document.getElementById("hours");
					elem.value = result.hours + ":"+result.minutes;
		   }
		   	
		   var onTimePickerFail = function(result) { alert('fail!'+result); }
		
		   function doSelectHours(){
		   	timepicker.doTimePicker(onTimePickerSuccess, onTimePickerFail);
		   }
		   
        </script>
        <input type="text" id="hours" placeholder="Click to select hours" onClick="doSelectHours(this)"/>

## Installation ##
1. Copy the folder structure above to the Phonegap project

2. Verify that res\xml\config.xml is as you'd like 
(plugin only requires 

	<plugin name="TimePicker" value="no.bouvet.phonegap.timepickerplugin.TimePickerPlugin" />
	
)

3. Set the theme of your android activities to android:theme="@style/DeskClock". Example

	<activity android:name="MainActivity" android:label="@string/app_name"
    android:theme="@style/DeskClock"
    android:configChanges="orientation|keyboardHidden|keyboard|screenSize|locale">
        
        
 