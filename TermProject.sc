import ist597d.PubMedClustering

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path

val configuration = new Configuration()
configuration.addResource(new Path("/usr/hdp/2.3.0.0-2557/hadoop/conf/core-site.xml"))

//val lines = sc.textFile(FileSystem.get(configuration).getUri + "/ist597j/PubMed/pubmed.csv")
val lines = sc.textFile(FileSystem.get(configuration).getUri + "/storage/md1/share/work/classes/ist597d-big-data/term-project/pubmed.csv")

val papers = PubMedClustering.parseData(lines)
val featureVectors = PubMedClustering.constructFeatureVectorsFromPapers(papers)
val clustersOfPapers = PubMedClustering.clusterPapers(featureVectors)
PubMedClustering.summarize(papers, clustersOfPapers)
