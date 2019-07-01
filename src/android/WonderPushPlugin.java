package com.wonderpush.sdk.cordova;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.wonderpush.sdk.WonderPush;

public class WonderPushPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        // Initialization
        if (action.equals("setUserId")) {

            String userId = args.isNull(0) ? null : args.getString(0);
            WonderPush.setUserId(userId);
            callbackContext.success();

        } else if (action.equals("isReady")) {

            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, WonderPush.isReady()));

        } else if (action.equals("setLogging")) {

            boolean enabled = args.getBoolean(0);
            WonderPush.setLogging(enabled);
            callbackContext.success();

        // Core information
        } else if (action.equals("getUserId")) {

            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, WonderPush.getUserId()));

        } else if (action.equals("getInstallationId")) {

            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, WonderPush.getInstallationId()));

        } else if (action.equals("getDeviceId")) {

            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, WonderPush.getDeviceId()));

        } else if (action.equals("getPushToken")) {

            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, WonderPush.getPushToken()));

        } else if (action.equals("getAccessToken")) {

            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, WonderPush.getAccessToken()));

        // Installation data and events
        } else if (action.equals("trackEvent")) {

            String type = args.getString(0);

            if (args.length() == 2) {
                JSONObject customData = args.getJSONObject(1);
                WonderPush.trackEvent(type, customData);
            } else {
                WonderPush.trackEvent(type);
            }
            callbackContext.success();

        } else if (action.equals("getInstallationCustomProperties")) {

            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, WonderPush.getInstallationCustomProperties()));

        } else if (action.equals("putInstallationCustomProperties")) {

            JSONObject custom = args.getJSONObject(0);
            WonderPush.putInstallationCustomProperties(custom);
            callbackContext.success();

        // Push notification handling
        } else if (action.equals("subscribeToNotifications")) {

            WonderPush.subscribeToNotifications();
            callbackContext.success();

        } else if (action.equals("isSubscribedToNotifications")) {

            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, WonderPush.isSubscribedToNotifications()));

        } else if (action.equals("unsubscribeFromNotifications")) {

            WonderPush.unsubscribeFromNotifications();
            callbackContext.success();

        } else if (action.equals("getNotificationEnabled")) {

            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, WonderPush.getNotificationEnabled()));

        } else if (action.equals("setNotificationEnabled")) {

            boolean enabled = args.getBoolean(0);
            WonderPush.setNotificationEnabled(enabled);
            callbackContext.success();

        } else {
            return false;
        }

        return true;
    }

}
