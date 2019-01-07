package app.booking.presentation.model_gson_classes;

/**
 * Created by NineHertz-and-team on 5/1/19.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VehicleBooking  implements Serializable  {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("endTime")
    @Expose
    private String endTime;
    @SerializedName("vehicleId")
    @Expose
    private Integer vehicleId;
    @SerializedName("podId")
    @Expose
    private Integer podId;
    @SerializedName("estimatedCost")
    @Expose
    private Double estimatedCost;
    @SerializedName("fuelPin")
    @Expose
    private String fuelPin;
    @SerializedName("freeKmsTotal")
    @Expose
    private String freeKmsTotal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getPodId() {
        return podId;
    }

    public void setPodId(Integer podId) {
        this.podId = podId;
    }

    public Double getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(Double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public String getFuelPin() {
        return fuelPin;
    }

    public void setFuelPin(String fuelPin) {
        this.fuelPin = fuelPin;
    }

    public String getFreeKmsTotal() {
        return freeKmsTotal;
    }

    public void setFreeKmsTotal(String freeKmsTotal) {
        this.freeKmsTotal = freeKmsTotal;
    }

}
