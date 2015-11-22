package ist597d

import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.rdd.RDD

object Clustering extends Serializable {
  def clusterPapers(featureVectors: RDD[Vector]): RDD[Int] = {
    val numClusters = 3
    val numIterations = 1000

    val kmModel = KMeans.train(featureVectors, numClusters, numIterations)
    return kmModel.predict(featureVectors)
  }
}
