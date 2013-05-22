cordova.define("cordova/plugin/timepicker", function(require, exports, module) {
	var exec = require('cordova/exec');

	var TimePicker = function() {};

	TimePicker.prototype.doTimePicker = function(successCallback,failureCallback) {
	    exec(successCallback, failureCallback, 'TimePicker', 'doTimePicker', []);
	}


	var timePickerPlugin = new TimePicker();
	module.exports = timepicker;
});