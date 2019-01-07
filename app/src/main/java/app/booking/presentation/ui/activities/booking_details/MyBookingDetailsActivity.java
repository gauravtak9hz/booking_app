package app.booking.presentation.ui.activities.booking_details;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import app.booking.R;
import app.booking.presentation.ui.components.PicassoTrustAll;
import app.booking.presentation.model_gson_classes.Pod;
import app.booking.presentation.model_gson_classes.Vehicle;
import app.booking.presentation.model_gson_classes.VehicleBooking;
import app.booking.utils.CommonUtil;
import app.booking.constants.IntentConstants;

/* this activity will be arrangd in the form of model,view,presenter
when whole functionality will be implemented*/

/**
 * Created by NineHertz-and-team on 5/1/19.
 */

public class MyBookingDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivBack, imageViewVehicle;
    private TextView tv_back, tvPodTitle, tvPodSubTitle, tvVehicleTitle,
            tvSeatingCapacity, tvVehiclePlate, tvStartDate, tvEndDate, tvDuration,
            tvCost, tvFreeKms, tvFuelPin;
    private Vehicle vehicleSelected;
    private Pod podSelected;
    private VehicleBooking vehicleBooking;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_booking_details_activity);
        mContext = this;
        initViews();
        getIntentValues();
        setValues();
        setClickListeners();
    }

    private void setClickListeners() {
        ivBack.setOnClickListener(this);
        tv_back.setOnClickListener(this);
    }

    private void setValues() {
        CommonUtil.setValueIntoTextView(tvPodTitle, podSelected.getName(), true);
        CommonUtil.setValueIntoTextView(tvPodSubTitle, podSelected.getDescription(), true);
        CommonUtil.setValueIntoTextView(tvVehicleTitle, vehicleSelected.getName(), true);
        CommonUtil.setValueIntoTextView(tvVehiclePlate, vehicleSelected.getNumberPlate(), true);
        CommonUtil.setValueIntoTextView(tvSeatingCapacity, vehicleSelected.getCapacity().toString(), false);
        CommonUtil.setValueIntoTextView(tvStartDate, CommonUtil.getDateTimeFromDateValue(vehicleBooking.getStartTime()), false);
        CommonUtil.setValueIntoTextView(tvEndDate, CommonUtil.getDateTimeFromDateValue(vehicleBooking.getEndTime()), false);
        CommonUtil.setValueIntoTextView(tvCost, "$" + CommonUtil.getDoubleValueWithPlaces(vehicleBooking.getEstimatedCost(), 2), false);
        CommonUtil.setValueIntoTextView(tvDuration,CommonUtil.differenceBetweenDates(vehicleBooking.getStartTime(),vehicleBooking.getEndTime()),false);
        if (vehicleBooking.getFreeKmsTotal() == null || vehicleBooking.getFreeKmsTotal().equals("0") || vehicleBooking.getFreeKmsTotal().equals("")) {
            CommonUtil.setValueIntoTextView(tvFreeKms, getString(R.string.label_value_no_free_kms_included), false);
        } else if (vehicleBooking.getFreeKmsTotal().equals("1") || Double.parseDouble(vehicleBooking.getFreeKmsTotal()) <= 1.0) {
            CommonUtil.setValueIntoTextView(tvFreeKms, "(" + vehicleBooking.getFreeKmsTotal() + " " + getString(R.string.label_value_free_km_included), false);
        } else {
            CommonUtil.setValueIntoTextView(tvFreeKms, "(" + vehicleBooking.getFreeKmsTotal() + " " + getString(R.string.label_value_free_kms_included), false);
        }
        if(vehicleSelected.getImage()!=null && !vehicleSelected.getImage().equals("")) {
            PicassoTrustAll.getInstance(mContext)
                    .load(vehicleSelected.getImage()).placeholder(R.drawable.car_place_holder)
                    .into(imageViewVehicle);

        }
        CommonUtil.setValueIntoTextView(tvFuelPin, vehicleBooking.getFuelPin(), false);

    }

    private void getIntentValues() {
        vehicleSelected = (Vehicle) getIntent().getSerializableExtra(IntentConstants.VEHICLE);
        podSelected = (Pod) getIntent().getSerializableExtra(IntentConstants.POD);
        vehicleBooking = (VehicleBooking) getIntent().getSerializableExtra(IntentConstants.BOOKING);
    }

    private void initViews() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        imageViewVehicle = (ImageView) findViewById(R.id.iv_vehicle_img);
        tv_back = (TextView) findViewById(R.id.tv_back);

        tvPodTitle = (TextView) findViewById(R.id.tv_title);
        tvPodSubTitle = (TextView) findViewById(R.id.tv_sub_title);
        tvVehicleTitle = (TextView) findViewById(R.id.tv_vehicle_title);
        tvSeatingCapacity = (TextView) findViewById(R.id.tv_seating_capacity);
        tvVehiclePlate = (TextView) findViewById(R.id.tv_number_plate);
        tvStartDate = (TextView) findViewById(R.id.tv_start_date_time);
        tvEndDate = (TextView) findViewById(R.id.tv_end_date_time);
        tvDuration = (TextView) findViewById(R.id.tv_duration);
        tvCost = (TextView) findViewById(R.id.tv_cost);
        tvFreeKms = (TextView) findViewById(R.id.tv_free_kms);
        tvFuelPin = (TextView) findViewById(R.id.tv_fuel_pin);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
