package com.gianlu.aria2app.ProfilesManager;

import android.content.Context;
import android.support.annotation.Nullable;

import com.gianlu.aria2app.R;

class ConnectivityCondition {
    public final Type type;
    public final String ssid;
    public final boolean isDefault;

    private ConnectivityCondition(Type type, boolean isDefault, @Nullable String ssid) {
        this.type = type;
        this.isDefault = isDefault;
        this.ssid = ssid;
    }

    static ConnectivityCondition newWiFiCondition(String ssid, boolean isDefault) {
        return new ConnectivityCondition(Type.WIFI, isDefault, ssid);
    }

    static ConnectivityCondition newMobileCondition(boolean isDefault) {
        return new ConnectivityCondition(Type.MOBILE, isDefault, null);
    }

    static ConnectivityCondition newBluetoothCondition(boolean isDefault) {
        return new ConnectivityCondition(Type.BLUETOOTH, isDefault, null);
    }

    static ConnectivityCondition newEthernetCondition(boolean isDefault) {
        return new ConnectivityCondition(Type.ETHERNET, isDefault, null);
    }

    String getFormalName(Context context) {
        return type.getFormal(context) + (type == Type.WIFI ? ": " + ssid : "");
    }

    enum Type {
        WIFI,
        MOBILE,
        ETHERNET,
        BLUETOOTH,
        UNKNOWN;

        public String getFormal(Context context) {
            switch (this) {
                case WIFI:
                    return context.getString(R.string.wifi);
                case MOBILE:
                    return context.getString(R.string.mobile);
                case ETHERNET:
                    return context.getString(R.string.ethernet);
                case BLUETOOTH:
                    return context.getString(R.string.bluetooth);
                default:
                case UNKNOWN:
                    return context.getString(R.string.unknown);
            }
        }
    }
}
