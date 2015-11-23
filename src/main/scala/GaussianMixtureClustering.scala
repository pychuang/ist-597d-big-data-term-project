package ist597d

import org.apache.spark.mllib.clustering.GaussianMixture
import org.apache.spark.mllib.clustering.GaussianMixtureModel
import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.rdd.RDD

class GaussianMixtureClustering(numClusters: Int) extends Serializable {
  def clusterPapers(featureVectors: RDD[Vector]): RDD[Int] = {
    val gmm = new GaussianMixture().setK(numClusters).run(featureVectors)
    return gmm.predict(featureVectors)
  }
}
