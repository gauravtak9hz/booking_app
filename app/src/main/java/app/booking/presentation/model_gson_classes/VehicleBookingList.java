package app.booking.presentation.model_gson_classes;

/**
 * Created by NineHertz-and-team on 5/1/19.
 */


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleBookingList  {

    @SerializedName("vehicleBookings")
    @Expose
    private List<VehicleBooking> vehicleBookings = null;

    public List<VehicleBooking> getVehicleBookings() {
        return vehicleBookings;
    }

    public void setVehicleBookings(List<VehicleBooking> vehicleBookings) {
        this.vehicleBookings = vehicleBookings;
    }

}