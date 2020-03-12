package com.bendyworks.TextDetector;

import android.net.Uri;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

import java.io.File;

@NativePlugin()
public class TextDetector extends Plugin {

    @PluginMethod()
    public void detectText(PluginCall call) {
        String value = call.getString("value");
        System.out.println("filePath:" + value);
        value = value.substring(7);
        Uri uri = Uri.fromFile(new File(value));
        System.out.println("image exists?:" + new File(value).exists());
        TextTextDetector td = new TextTextDetector();
        td.detectText(this.getContext(), uri, 0);
        JSObject ret = new JSObject();
        ret.put("value", value);
        call.success(ret);
    }
}
