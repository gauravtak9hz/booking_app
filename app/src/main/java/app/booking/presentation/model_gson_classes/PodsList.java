package app.booking.presentation.model_gson_classes;

/**
 * Created by NineHertz-and-team on 5/1/19.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PodsList {

    @SerializedName("pods")
    @Expose
    private List<Pod> pods = null;

    public List<Pod> getPods() {
        return pods;
    }

    public void setPods(List<Pod> pods) {
        this.pods = pods;
    }

}