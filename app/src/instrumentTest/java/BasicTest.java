import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import co.infinum.testci.LoginActivity;
import co.infinum.testci.R;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onData;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.openContextualActionModeOverflowMenu;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by nixa on 29.01.2014..
 */
@LargeTest
public class BasicTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    public BasicTest() {
        super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testSimpleClick() {

        onView(withId(R.id.buttonLogin)).perform(click());


        assertNotNull(withId(R.id.buttonSend));
        assertNotNull(withId(R.id.buttonMail));

        onView(withId(R.id.buttonSend)).perform(click()).check(matches(withText("Send")));

    }
}
