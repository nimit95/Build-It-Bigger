package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;
import android.util.Pair;



import java.util.concurrent.TimeUnit;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() throws Exception {
        super(Application.class);
        AsyncTaskTest();
    }
    EndpointsAsyncTask asyncTask = new EndpointsAsyncTask(null);
    public void AsyncTaskTest() throws Exception {
        String result = asyncTask.execute(getContext()).get(60, TimeUnit.SECONDS);
        assertNotNull(result);
        assertTrue("Test Failed",result.length() > 0);
    }

}