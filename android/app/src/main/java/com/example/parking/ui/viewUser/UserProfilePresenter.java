package com.example.parking.ui.viewUser;

import com.example.parking.dao.UserDAO;
import com.example.parking.domain.Address;
import com.example.parking.domain.User;
import com.example.parking.util.Credits;
import com.example.parking.util.ZipCode;

public class UserProfilePresenter {
    private UserProfileView view;
    private UserDAO dao;
    private User currentUser;

    /**
     * Αρχικοποεί τον Presenter.
     * @param view Ένα instance του view
     * @param dao Ένα instance του user
     */

    public UserProfilePresenter(UserProfileView view, UserDAO dao) {
        this.view = view;
        this.dao = dao;
        currentUser = dao.find(view.getUsername());
        setFields();
    }

    /**
     * Εμφανίζει τα αποθηκευμένα πεδία του User.
     */
    public void setFields() {
        view.setFirstName(currentUser.getName());
        view.setLastName(currentUser.getSurname());
        view.setEmail(currentUser.getEmail());
        view.setCredits(currentUser.getCredits().getPoints());
        view.setStreet(currentUser.getAddress().getStreet());
        view.setStreetNo(currentUser.getAddress().getStreetNumber());
        view.setZip(String.valueOf(currentUser.getAddress().getZipCode().getZip()));
        view.setPhone(currentUser.getPhone());
    }

    /**
     * Κάνει update το profile του User.
    */

    public void update() {

        String firstname = view.getFirstName(),
                lastname = view.getLastName(),
                email = view.getEmail(),
                street = view.getStreet(),
                streetno = view.getStreetNo(),
                zipcode = view.getZip(),
                phone = view.getPhone();
        int credits = view.getCredits();

        if (firstname.length() < 3 || firstname.length() > 10) {
            view.showErrorMessage("firstname", "First name must be more than 3 characters and up to 10.");
        } else if (lastname.length() < 3 || lastname.length() > 25) {
            view.showErrorMessage("lastname", "Last name must be more than 3 characters and up to 25.");
        } else if (email.length() < 10 || email.length() > 150) {
            view.showErrorMessage("email", "Email must be more than than 10 characters and up to 150.");
        } else if (street.length() < 3 || street.length() > 15) {
            view.showErrorMessage("street", "Street must be more than 3 characters and up to 15.");
        } else if (streetno.length() < 1 || streetno.length() > 3) {
            view.showErrorMessage("streetno", "Street No. must be more than 3 characters and up to 3.");
        } else if (zipcode.length() != 5) {
            view.showErrorMessage("zipcode", "ZIP Code must be 5 characters.");
        } else if (phone.length() != 10) {
            view.showErrorMessage("phone", "Phone must be more 10 characters.");
        } else if (credits < 0) {
            view.showErrorMessage("credits", "Credits must not be negative.");
        } else {
            Address currentAddress = currentUser.getAddress();
            currentAddress.setStreet(view.getStreet());
            currentAddress.setStreetNumber(view.getStreetNo());
            currentAddress.setZipCode(new ZipCode(Integer.valueOf(view.getZip())));
            User newUser = new User(view.getFirstName(), view.getLastName(), view.getPhone(), view.getEmail(), currentUser.getUsername(), currentUser.getPassword(), new Credits((view.getCredits())), currentAddress, currentUser.getRating(), currentUser.getVehicles());
            dao.update(newUser);
            view.successfullyFinishActivity("User: "+view.getIntentUsername()+ " updated!");
        }
    }
}
