package app.booking.presentation.ui.activities.booking_list;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import app.booking.R;
import app.booking.presentation.ui.activities.booking_details.MyBookingDetailsActivity;
import app.booking.presentation.ui.holders.MyBookingAdapter;
import app.booking.presentation.ui.listeners.OnBookingClickInterface;
import app.booking.presentation.model_gson_classes.Pod;
import app.booking.presentation.model_gson_classes.PodsList;
import app.booking.presentation.model_gson_classes.Vehicle;
import app.booking.presentation.model_gson_classes.VehicleBooking;
import app.booking.presentation.model_gson_classes.VehicleBookingList;
import app.booking.presentation.model_gson_classes.VehiclesList;
import app.booking.utils.CommonUtil;
import app.booking.constants.IntentConstants;

/* this activity will be arrangd in the form of model,view,presenter
when whole functionality will be implemented*/

/**
 * Created by NineHertz-and-team on 5/1/19.
 */

//It has been assumed that all required json files are available in assets folder
// for processing.
public class BookingListActivity extends AppCompatActivity implements OnBookingClickInterface {
    private RecyclerView mybooking_recycler;
    private MyBookingAdapter myBookingAdapter;
    private PodsList podsList;
    private VehicleBookingList vehicleBookingList;
    private VehiclesList vehiclesList;
    private Vehicle vehicleSelected;
    private Pod podSelected;
    private Context mContext;
    boolean isPodLoaded,isVehiclesLoaded;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_list_activity);
        mContext = this;
        initViews();
        loadVehiclesBookingsDataListFromJson();

    }
    // this async tasks is defined to load booking in the background thread.
    //when implementing in real, it will be related with retrofit api call
    private class AsyncTaskLoadBooking extends AsyncTask<Void, Void, Void> {


        ProgressDialog progressDialog;

        @Override
        protected Void doInBackground(Void... params) {
           String vehiclesBookingData = CommonUtil.loadJSONFromAsset("vehicleBookings.json");
            vehicleBookingList = new Gson().fromJson(vehiclesBookingData, VehicleBookingList.class);

            return null;

        }


        @Override
        protected void onPostExecute(Void result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            setViewWithVehicleBookingsList();
            loadVehiclesDataListFromJson();
            loadPodsDataListFromJson();
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(mContext,
                    getString(R.string.msg_loading),
                    getString(R.string.msg_loading_json));
        }


    }
    // this async tasks is defined to load vehicles in the background thread.
    //when implementing in real, it will be related with retrofit api call
    private class AsyncTaskLoadVehiclesList extends AsyncTask<Void, Void, Void> {



        @Override
        protected Void doInBackground(Void... params) {
             String vehiclesData = CommonUtil.loadJSONFromAsset("vehicles.json");
              vehiclesList = new Gson().fromJson(vehiclesData, VehiclesList.class);

            return null;

        }


        @Override
        protected void onPostExecute(Void result) {
            // execution of result of Long time consuming operation
        isVehiclesLoaded = true;


        }


        @Override
        protected void onPreExecute() {

        }


    }
    // this async tasks is defined to load pods in the background thread.
    //when implementing in real, it will be related with retrofit api call
    private class AsyncTaskLoadPodsList extends AsyncTask<Void, Void, Void> {



        @Override
        protected Void doInBackground(Void... params) {
           String podsData = CommonUtil.loadJSONFromAsset("pods.json");
             podsList = new Gson().fromJson(podsData, PodsList.class);

            return null;

        }


        @Override
        protected void onPostExecute(Void result) {
            // execution of result of Long time consuming operation

            isPodLoaded = true;

        }


        @Override
        protected void onPreExecute() {

        }


    }
    private void loadVehiclesBookingsDataListFromJson() {
        AsyncTaskLoadBooking runner = new AsyncTaskLoadBooking();

        runner.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
    private void loadVehiclesDataListFromJson() {
        AsyncTaskLoadVehiclesList runner = new AsyncTaskLoadVehiclesList();

        runner.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
    private void loadPodsDataListFromJson() {
        AsyncTaskLoadPodsList runner = new AsyncTaskLoadPodsList();

        runner.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
    private void initViews() {
        mybooking_recycler = (RecyclerView) findViewById(R.id.mybooking_recycler);
    }

    private void setViewWithVehicleBookingsList() {
        mybooking_recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        myBookingAdapter = new MyBookingAdapter(mContext,this,this.vehicleBookingList);
        mybooking_recycler.setAdapter(myBookingAdapter);
    }

    @Override
    public void onBookingClick(int position) {
        if(!isPodLoaded || !isVehiclesLoaded)
        {
         CommonUtil.showSnackbar(BookingListActivity.this,getString(R.string.msg_please_wait_vehicles_data));
            //exiting the execution because vechicle or pod details is not loaded yet
            return;
        }
        getVehicleAndPodData(position);

        if(vehicleSelected==null)
        {
            CommonUtil.showSnackbar(BookingListActivity.this,getString(R.string.msg_details_not_available));
             return; //exiting the execution because vechicle or pod details is not available for the clicked booking
        }
        if(podSelected==null)
        {
            CommonUtil.showSnackbar(BookingListActivity.this,getString(R.string.msg_details_not_available1));
            return; //exiting the execution because vechicle or pod details is not available for the clicked booking
        }
        VehicleBooking bookingSelected = vehicleBookingList.getVehicleBookings().get(position);
        Intent intent = new Intent(BookingListActivity.this, MyBookingDetailsActivity.class);
        intent.putExtra(IntentConstants.VEHICLE,vehicleSelected);
        intent.putExtra(IntentConstants.POD,podSelected);
        intent.putExtra(IntentConstants.BOOKING,bookingSelected);
        startActivity(intent);
    }

    private void getVehicleAndPodData(int position) {
         vehicleSelected = null;
         podSelected = null;
        int vehicleId = vehicleBookingList.getVehicleBookings().get(position).getVehicleId();
        int podId = vehicleBookingList.getVehicleBookings().get(position).getPodId();
        for(Vehicle vehicle:vehiclesList.getVehicles())
        {
            if(vehicle.getId().toString().equals(vehicleId+""))
            {
                vehicleSelected = vehicle;
                break;
            }
        }
        for(Pod pod:podsList.getPods())
        {
            if(pod.getId().toString().equals(podId+""))
            {
                podSelected = pod;
                break;
            }
        }
    }
}
