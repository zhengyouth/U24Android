package com.example.wb_cg195042.u2demo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
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
    private static  final int LAUNCH_TIMEOUT = 5000;
    private  final String BASIC_SAMPLE_PACKAGE = "com.android.calculator2";

    @Before
    public void setup(){
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        mDevice.pressHome();

        final String launcherPackage = getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);
    }


    @Test
    public void calculatorTest() {
        mDevice.findObject(By.desc("应用")).click();
        mDevice.wait(Until.hasObject(By.desc("计算器")), LAUNCH_TIMEOUT);
        mDevice.findObject(By.desc("计算器")).click();

        UiObject2 button7 = mDevice.wait(Until.findObject(By.res("com.android.calculator2", "digit_7")), 500);
        UiObject2 buttonX = mDevice.wait(Until.findObject(By.res("com.android.calculator2", "op_mul")), 500);
        UiObject2 button6 = mDevice.wait(Until.findObject(By.res("com.android.calculator2", "digit_6")), 500);
        UiObject2 buttonEqual = mDevice.wait(Until.findObject(By.res("com.android.calculator2", "eq")), 500);
        UiObject2 output = mDevice.wait(Until.findObject(By.res("com.android.calculator2", "result")), 500);

        button7.click();
        buttonX.click();
        button6.click();
        buttonEqual.click();
        assertEquals(output.getText(), "42");

    }

    private String getLauncherPackageName() {
        // Create launcher Intent
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        // Use PackageManager to get the launcher package name
        PackageManager pm = InstrumentationRegistry.getContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }
}
