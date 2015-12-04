# ist-597d-big-data-term-project

# Build

```sh
$ sbt package
```

# Run

```sh
$ spark-shell --jars target/scala-2.10/ist597d_2.10-1.0.jar --master yarn-client --driver-memory 2g --executor-memory 2g

scala> :load kmeans.sc
```
