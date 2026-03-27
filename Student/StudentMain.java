package Student;

import java.util.List;
import rs.etf.sab.operations.*;
import rs.etf.sab.tests.TestHandler;
import rs.etf.sab.tests.TestRunner;


public class StudentMain {
    
    

    public static void main(String[] args) {
        CityOperations cityOperations = new zd20060_CityOperations(); // Change this to your implementation.
        DistrictOperations districtOperations = new zd200160_DistrictOperations(); // Do it for all classes.
        CourierOperations courierOperations = new zd200160_CourierOperations(); // e.g. = new zd200160_DistrictOperations();
        CourierRequestOperation courierRequestOperation = new zd200160_CourierRequestOperation();
        GeneralOperations generalOperations = new zd200160_GeneralOperations();
        UserOperations userOperations = new zd200160_UserOperations();
        VehicleOperations vehicleOperations = new zd200160_VehicleOperations();
        PackageOperations packageOperations = new zd200160_PackageOperations();

        TestHandler.createInstance(
                cityOperations,
                courierOperations,
                courierRequestOperation,
                districtOperations,
                generalOperations,
                userOperations,
                vehicleOperations,
                packageOperations);
        TestRunner.runTests();
        /*
        generalOperations.eraseAll();
        int idC = cityOperations.insertCity("bobovo", "31eig9");
        List<Integer> ids = cityOperations.getAllCities();
        System.out.println(ids);
        cityOperations.deleteCity("markovac", "plana");
        districtOperations.insertDistrict("lebane", 10, 12, idC);
        ids = cityOperations.getAllCities();
        System.out.println(ids);
        */
    }
}
