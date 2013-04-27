package mia.recommender.ch02;

import java.util.List;

import mia.recommender.Init;

import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.google.common.collect.Lists;

class RecommenderIntro {

    private RecommenderIntro() {
    }

    public static void main(String[] args) throws Exception {
      //  DataModel model = Init.getSimple();
        DataModel model = Init.getUaBase();
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

        UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);

        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
        List<RecommendedItem> recommendations = recommender.recommend(1,2);

        for (int i = 0; i < 100; i++) {
            List<RecommendedItem> items = recommender.recommend(i, 5);
            System.out.printf(String.format("User %s\n", i));
            for (RecommendedItem e : items) {
                System.out.println(e);
            }
        }
        /*for (RecommendedItem e: recommendations) {
            System.out.println(e);
        }*/
    }
}
