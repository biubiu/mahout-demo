package mia.recommender.ch02;

import mia.recommender.Init;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;


class EvaluatorIntro {

    private EvaluatorIntro() {
    }

    public static void main(String[] args) throws Exception {
        RandomUtils.useTestSeed();
        DataModel model = Init.getSimple();
        RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
        RecommenderBuilder builder = new RecommenderBuilder() {
            public Recommender buildRecommender(DataModel dataModel) throws TasteException {
                UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
                UserNeighborhood neighborhood = new NearestNUserNeighborhood(2,similarity, dataModel);
                return  new GenericUserBasedRecommender(dataModel,neighborhood,similarity);
            }
        };

        double score = evaluator.evaluate(builder, null, model, 0.7, 1.0);
        System.out.println(score);
    }
}
