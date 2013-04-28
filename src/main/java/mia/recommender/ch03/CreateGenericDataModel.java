package mia.recommender.ch03;

import org.apache.cassandra.cli.CliParser.newColumnFamily_return;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.regexp.recompile;

class CreateGenericDataModel {

    private CreateGenericDataModel() {
    }

    public static void main(String[] args) throws Exception {
        FastByIDMap<PreferenceArray> preferences = new FastByIDMap<PreferenceArray>();
        PreferenceArray prefsForU1 = new GenericUserPreferenceArray(10);
        prefsForU1.setUserID(0,101);

        prefsForU1.setItemID(0, 101L);
        prefsForU1.setValue(0, 2.0f);

        prefsForU1.setItemID(1, 102L);
        prefsForU1.setValue(1, 3.0f);

        prefsForU1.setItemID(2, 103L);
        prefsForU1.setValue(2, 1.0f);
        prefsForU1.setItemID(3, 104L);
        prefsForU1.setValue(3, 1.0f);
        prefsForU1.setItemID(4, 105L);
        prefsForU1.setValue(4, 1.0f);
        prefsForU1.setItemID(5, 106L);
        prefsForU1.setValue(5, 1.0f);
        prefsForU1.setItemID(6, 107L);
        prefsForU1.setValue(6, 1.0f);
        prefsForU1.setItemID(7, 108L);
        prefsForU1.setValue(7, 1.0f);
        preferences.put(101L, prefsForU1);

        DataModel model = new GenericDataModel(preferences);

        System.out.println(model.toString());

    }

}
