package com.xiaomi.web.mp_android_chart;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewManager;

import java.util.Collections;
import java.util.List;

/**
 * author:       joddiy <joddiy@qq.com>
 * time:         2016/6/6 16:28
 */
public class MPAnroidChartPackage implements ReactPackage {
    /**
     * @param reactContext react application context that can be used to create modules
     * @return list of native modules to register with the newly created catalyst instance
     */
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

    /**
     * @return list of JS modules to register with the newly created catalyst instance.
     * <p>
     * IMPORTANT: Note that only modules that needs to be accessible from the native code should be
     * listed here. Also listing a native module here doesn't imply that the JS implementation of it
     * will be automatically included in the JS bundle.
     */
    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    /**
     * @param reactContext
     * @return a list of view managers that should be registered with {@link UIManagerModule}
     */
    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}
