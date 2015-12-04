import ist597d.Helper
import ist597d.FeatureExtraction
import ist597d.GaussianMixtureClustering

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path

val configuration = new Configuration()
configuration.addResource(new Path("/usr/hdp/2.3.0.0-2557/hadoop/conf/core-site.xml"))

//val lines = sc.textFile(FileSystem.get(configuration).getUri + "/ist597j/PubMed/pubmed.csv")
val lines = sc.textFile(FileSystem.get(configuration).getUri + "/storage/md1/share/work/classes/ist597d-big-data/term-project/pubmed.csv")

val papers = Helper.parseData(lines)
val featureVectors = FeatureExtraction.constructFeatureVectorsFromPapers(papers)

val start = System.nanoTime
val clustersOfPapers = new GaussianMixtureClustering(3, 100).clusterPapers(featureVectors)
val end = System.nanoTime
println((end - start) / 1000000 + "ms")

Helper.summarize(papers, clustersOfPapers, 10)
