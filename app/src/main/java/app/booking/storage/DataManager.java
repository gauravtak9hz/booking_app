package app.booking.storage;

//Singlton Design Pattern Class to optimize the storage and data performance
public class DataManager {

      private static DataManager dataManager = null;

    public static DataManager getInstance() {
        if (dataManager == null)
            dataManager = new DataManager();
        return dataManager;
    }
    private DataManager(){}
}

