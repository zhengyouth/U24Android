package com.example.wb_cg195042.u2demo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.provider.Settings;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private UiDevice mDevice;
    private Context context;
    String app = "com.android.calculator2";

    @Before
    public void setup() {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());//获取devices的对象
        context = InstrumentationRegistry.getContext();
        mDevice.pressHome();
        System.out.print("--点击home键，退出到桌面");

    }
    @Test
    public void calculatorTest() {
//        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());//获取devices的对象
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(app);
        context.startActivity(intent);
        mDevice.wait(Until.hasObject(By.pkg("com.android.calculator2")),5000);
        System.out.print("启动计算器");
        UiObject2  btn = mDevice.findObject(By.res("com.android.calculator2","digit_9"));
        UiObject2 button7 = mDevice.wait(Until.findObject(By.res("com.android.calculator2", "digit_7")), 500);
        UiObject2 buttonX = mDevice.wait(Until.findObject(By.res("com.android.calculator2", "op_mul")), 500);
        UiObject2 button6 = mDevice.wait(Until.findObject(By.res("com.android.calculator2", "digit_6")), 500);
        UiObject2 buttonEqual = mDevice.wait(Until.findObject(By.res("com.android.calculator2", "eq")), 500);
        UiObject2 output = mDevice.wait(Until.findObject(By.res("com.android.calculator2", "formula")), 500);

        button7.click();
        buttonX.click();
        button6.click();
        buttonEqual.click();
        assertEquals(output.getText(), "42");

    }

}
