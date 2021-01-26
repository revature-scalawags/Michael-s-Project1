# Michael-s-Project1
## Project Description

Project 1's analysis consists of using big data tools to answer questions about datasets from MovieLens. There are a series of basic analysis questions, answered using Hive and MapReduce. The tools used are determined based on the context for each question. The output of the analysis includes MapReduce jar files so that the analysis is a repeatable process that works on a larger dataset, not just an ad hoc calculation. 
## Technologies Used
* Scala - version 2.13.3
* sbt - version 1.4.4
* Apache Hive - version 3.1.2
* HDFS
* Yarn
* MapReduce
* Hadoop
## Features
List of features ready 
* Given the dataset, finds the number movies rated 5 stars. 
* Displays list of movies rated 5 stars. 
* Given the dataset, finds the number of movies with 1 star or less.
* Displays list of movies rated 1 star or less.
## Getting Started
In order to run this program properly, Apache Hive must be installed and running properly on a JVM or local cluster. Once the prerequisite is met, clone this repo using the following command:
```
git clone https://github.com/revature-scalawags/Michael-s-Project1.git
```
## Usage
Create an executable jar file using the following command:
```
sbt assembly
```
The executable jar file is created within the /target/scala-2.13 directory. Copy the .jar file into a JVM or local cluster.
Execute the jar file using the following command:
```
hadoop jar word-count-assembly.jar input output
```
After the .jar file executes, you can read the output using: 
```
hdfs dfs -cat output/part-r-00000
```
