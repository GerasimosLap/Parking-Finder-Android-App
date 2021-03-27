package com.example.parking.ui.viewOneVehicle;

import com.example.parking.dao.UserDAO;
import com.example.parking.domain.User;
import com.example.parking.domain.Vehicle;
import com.example.parking.util.Colour;

import java.util.ArrayList;
import java.util.List;

public class ViewOneVehiclePresenter {
    private ViewOneVehicleView view;
    private Vehicle vehicle=null;
    private User user;
    private UserDAO dao;

    /**
     * Αρχικοποεί τον Presenter.
     * @param view Ένα instance του view
     * @param dao Ένα instance του user
     */
    public ViewOneVehiclePresenter(ViewOneVehicleView view, UserDAO dao){
        this.view=view;
        this.dao=dao;
        user = dao.find(view.getUserName());
        if(view.getPlate()!=null){
            System.out.println("in edits");
            vehicle = dao.findVehicle(view.getIntentUsername(),view.getIntentPlate());
            showInfo();

        }else{
            view.setInvisibility();
        }
    }


    /**
     * Εμφανίζει τα αποθηκευμένα πεδία του vehicle.
     */

    public void showInfo(){
        view.setBrand(vehicle.getBrand());
        view.setModel(vehicle.getModel());
        view.setPlate(vehicle.getPlate());
        view.setLength(vehicle.getLength());
        view.setText(vehicle.getText());
        view.setColour(vehicle.getColour());
    }

    /**
     * Ελέγχει το vehicle για αποθήκευση ή προσθήκη.
     */
    public void decide(){
        String brand = view.getBrand(),
                model=view.getModel(),
                plate = view.getPlateText(),
                text = view.getText();
        Colour color=view.getColour();
        int length = view.getLength();
        if(!checkPlate(plate)) {
            System.out.println(plate);
            view.showErrorMessage("plates", "Plate must begin with 3 latin letter and then 4 numbers.");
        }else if(brand.length() < 3 || brand.length() > 15){
            System.out.println(2);
            view.showErrorMessage("brand", "Brand must be more than 3 characters and up to 15.");
        }else if(model.length() < 3 || model.length() > 15){
            System.out.println(3);
            view.showErrorMessage("model", "Model must be more than 3 characters and up to 15.");
        }else if(length < 100 || length > 5000){
            System.out.println(4);
            view.showErrorMessage("length", "Length must be more than 100cm(1M) or less than 5000cm(500M).");
        }else if(text.length() < 5 || text.length() > 50){
            System.out.println(5);
            view.showErrorMessage("text", "Text must be more than 5 characters and up to 50.");
        }else {

            System.out.println("in");
            if (vehicle == null) {
                addVehicle();
                view.successfullyFinishActivity("Vehicle with plate " + view.getPlateText() + " added");
            } else {
                updateVehicle(brand, model, plate, length, text,color);
                view.successfullyFinishActivity("Vehicle with plate " + view.getIntentPlate() + " updated");
            }
        }
    }


    /**
     * Ελέγχει την πινακίδα.
     * @param plate Η πινακίδα.
     */
    public boolean checkPlate(String plate){
        if(plate!=null) {
            String letters = plate.substring(0, 3).toUpperCase();

            for (int i = 0; i < letters.length(); i++) {
                char letter = letters.charAt(i);
                if (letter < 65 || letter > 90){return false;}
            }
            String numbers = plate.substring(3);
            for (int i = 0; i < numbers.length(); i++) {
                int number = Integer.valueOf(numbers.charAt(i));
                if (number < 48 || number > 57){return false;}
            }
            return letters.length() + numbers.length() == 7;
        }
        return false;
    }


    /**
     * Προσθέτει ένα vehicle.
     */
    public void addVehicle(){

        dao.updateVehicle(view.getIntentUsername(),new Vehicle(view.getColour(),view.getLength(),view.getText(),view.getPlateText(),view.getModel(),view.getBrand()));
        view.successfullyFinishActivity("Vehicle with plate "+ view.getPlateText() +" added");
    }

    /**
     * Διαγράφει ένα vehicle.
     */
    public void delete(){
        dao.deleteVehicle(view.getIntentUsername(),vehicle);
        view.successfullyFinishActivity("Vehicle with plate "+ view.getPlateText() +" deleted");
    }

    /**
     * Ενημερώνει ένα ήδη υπάρχον vehicle.
     * @param brand Η μάρκα
     * @param model Το μοντέλο
     * @param length Το μήκος
     * @param text  Η περγιγραφή
     */
    public void updateVehicle(String brand,String model,String plate, int length,String text,Colour color){
        Vehicle temp = dao.find(view.getIntentUsername()).getVehicle(plate);
        temp.setBrand(brand);
        temp.setModel(model);
        temp.setLength(length);
        temp.setText(text);
        temp.setColour(color);
        dao.updateVehicle(user.getUsername(),temp);
        view.successfullyFinishActivity("Vehicle with plate "+ vehicle.getPlate() +" updated");
    }
}
