package app.booking.presentation.ui.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import app.booking.R;
import app.booking.presentation.ui.listeners.OnBookingClickInterface;
import app.booking.presentation.model_gson_classes.VehicleBooking;
import app.booking.presentation.model_gson_classes.VehicleBookingList;
import app.booking.utils.CommonUtil;


/* this Adapter will be arranged in the form of model,view,presenter
when whole functionality will be implemented*/

/**
 * Created by NineHertz-and-team on 5/1/19.
 */

public class MyBookingAdapter extends RecyclerView.Adapter<MyBookingAdapter.MyViewHolder> {
    private Context context;
    private OnBookingClickInterface onBookingClickInterface;
    private VehicleBookingList vehicleBookings;
    private List<VehicleBooking> vehicleBookingArrayList;

    public MyBookingAdapter(Context context,OnBookingClickInterface onBookingClickInterface,VehicleBookingList vehicleBookings) {
        this.context = context;
        this.onBookingClickInterface = onBookingClickInterface;
        this.vehicleBookings = vehicleBookings;
        this.vehicleBookingArrayList = vehicleBookings.getVehicleBookings();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xml_mybookinglayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // set the data in itemst
        VehicleBooking vehicleBooking = vehicleBookingArrayList.get(position);

        holder.list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBookingClickInterface.onBookingClick(position);
            }
        });
        String startDate = CommonUtil.getDateFromDateValue(vehicleBooking.getStartTime());
        String endDate = CommonUtil.getDateFromDateValue(vehicleBooking.getEndTime());
        if(startDate.equals(endDate))
            holder.tvDateFromTo.setText(startDate);
        else
            holder.tvDateFromTo.setText(startDate+" - "+endDate);
        String startTime = CommonUtil.getTimeFromDateValue(vehicleBooking.getStartTime());
        String endTime = CommonUtil.getTimeFromDateValue(vehicleBooking.getEndTime());
        if(startTime.equals(endTime))
            holder.tvTimeFromTo.setText(startTime);
        else
            holder.tvTimeFromTo.setText((startTime+" - "+endTime).toLowerCase());


    }


    @Override
    public int getItemCount() {
        if(vehicleBookingArrayList!=null)
            return vehicleBookingArrayList.size();
        else
            return 0;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout list_item;
        TextView tvDateFromTo,tvTimeFromTo;


        public MyViewHolder(View itemView) {
            super(itemView);
            list_item = (LinearLayout) itemView.findViewById(R.id.list_item);
            tvDateFromTo = (TextView)itemView.findViewById(R.id.tv_from_to_date);
            tvTimeFromTo = (TextView)itemView.findViewById(R.id.tv_from_to_time);


        }
    }


}


