package ist597d

import org.apache.spark.mllib.clustering.LDA
import org.apache.spark.mllib.clustering.DistributedLDAModel
import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.rdd.RDD

class LDAClustering(numClusters: Int, numIterations: Int) extends Serializable {
  def clusterPapers(featureVectors: RDD[Vector]): RDD[Int] = {
    val corpus = featureVectors.zipWithIndex.map(_.swap).cache()
    val ldaModel = new LDA().setK(numClusters).run(corpus)
    val distLDAModel = ldaModel.asInstanceOf[DistributedLDAModel]
    val localModel = distLDAModel.toLocal
    val distributions = localModel.topicDistributions(corpus)

    return distributions.map(p => p._2.toArray.zipWithIndex.maxBy(_._1)._2)
  }
}
