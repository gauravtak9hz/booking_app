package app.booking;

import android.app.Application;
import android.content.Context;


/**
 * Created by NineHertz-and-team on 5/1/19.
 */

public class BookingApplication extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }


    @Override

    public void onCreate() {
        super.onCreate();
        this.context = this;

    }

}
