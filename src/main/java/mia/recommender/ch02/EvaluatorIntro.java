package mia.recommender.ch02;

import java.util.List;

import mia.recommender.Init;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.slopeone.SlopeOneRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;

class EvaluatorIntro {

    private EvaluatorIntro() {
    }

    public static void main(String[] args) throws Exception {
        // RandomUtils.useTestSeed();
        RandomUtils.getRandom();
        // DataModel model = Init.getSimple();
        DataModel model = Init.getUaBase();

        RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();

        RecommenderBuilder builder = new RecommenderBuilder() {
            public Recommender buildRecommender(DataModel dataModel) throws TasteException {

                UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
                UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, dataModel);
                return new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
                // return new SlopeOneRecommender(dataModel);
            }
        };

        double score = evaluator.evaluate(builder, null, model, 0.7, 1.0);
        System.out.println(score);

        Recommender recommender = builder.buildRecommender(model);

        long startLong =System.currentTimeMillis();
   /*     List<RecommendedItem> items = recommender.recommend(1, 5);
        //System.out.printf(String.format("User %s\n", i));
        for (RecommendedItem e : items) {
            System.out.println(e);
        }
        System.out.println("end" + (System.currentTimeMillis() - startLong));*/

        //test recommend with 100 users
        for (int i = 0; i < 100; i++) {
            List<RecommendedItem> items = recommender.recommend(i, 5);
            System.out.printf(String.format("User %s\n", i));
            for (RecommendedItem e : items) {
                System.out.println(e);
            }
        }
        System.out.println("end" + (System.currentTimeMillis() - startLong));
    }
}
