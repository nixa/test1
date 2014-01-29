import com.squareup.spoon.Spoon;

import android.app.Instrumentation;
import android.content.IntentFilter;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import co.infinum.testci.LoginActivity;
import co.infinum.testci.R;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.typeText;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;

/**
 * Created by nixa on 29.01.2014..
 */
@LargeTest
public class BasicTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    private Instrumentation instrumentation;
    private LoginActivity   activity;

    public BasicTest() {
        super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        activity = getActivity();
        instrumentation = getInstrumentation();
    }

    public void testSimpleClick() {

        IntentFilter filter = new IntentFilter();
        Instrumentation.ActivityMonitor monitor = instrumentation.addMonitor(filter, null, false);

        Spoon.screenshot(getActivity(), "initial_state");

        onView(withId(R.id.buttonLogin)).perform(click());

        assertNotNull(withId(R.id.buttonSend));
        assertNotNull(withId(R.id.buttonMail));

        Spoon.screenshot(monitor.getLastActivity(), "middle_state");

        onView(withId(R.id.buttonSend)).perform(click());

        assertNotNull(matches(withText("NIXA JE COOL")));

        Spoon.screenshot(monitor.getLastActivity(), "end_state");

    }

    public void testEmailPassing() {

        IntentFilter filter = new IntentFilter();
        Instrumentation.ActivityMonitor monitor = instrumentation.addMonitor(filter, null, false);

        Spoon.screenshot(getActivity(), "initial_state");

        onView(withId(R.id.editTextEmail)).perform(typeText("nikola@infinum.hr"));
        onView(withId(R.id.editTextPassword)).perform(typeText("nixa1"));

        Spoon.screenshot(getActivity(), "middle_state");

        onView(withId(R.id.buttonLogin)).perform(click());

        Spoon.screenshot(monitor.getLastActivity(), "home_page");

        onView(withId(R.id.buttonSend)).perform(click());

        assertNotNull(matches(withText("nikola@infinum.hr")));

        assertNotNull(matches(withText("NIXA JE COOL")));

        Spoon.screenshot(monitor.getLastActivity(), "after_click_on_send");

    }
}
