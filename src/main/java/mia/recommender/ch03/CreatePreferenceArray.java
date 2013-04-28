package mia.recommender.ch03;

import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;

class CreatePreferenceArray {

    private CreatePreferenceArray() {
    }

    public static void main(String[] args) {
        PreferenceArray user1Prefs = new GenericUserPreferenceArray(3);
        user1Prefs.setUserID(0, 1);
        user1Prefs.setItemID(0, 101L);
        user1Prefs.setValue(0, 2.0f);

        user1Prefs.setItemID(1, 102L);
        user1Prefs.setValue(1, 3.0f);

        user1Prefs.setItemID(2, 103L);
        user1Prefs.setValue(2, 1.0f);

        Preference preference = user1Prefs.get(2);
        System.out.printf(String.format("user %d item %d ", preference.getUserID(),preference.getItemID()));
    }

}
