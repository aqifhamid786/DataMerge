# DataMerge
## Application Features
Application is flexible enough to read (and sink) data from (and to) different sources. For this particular assignment, under the hood, data sources and sinks use a single shared workbook. It also employs a simple configuration mechanism. Further, it is flexible enough to employ any new strategy to merge data from _two_ sources. We implement two strategies, named as CrossJoin and HashedNGram.  
### CrossJoin
Cross join is a naive merging method. It picks up a row of the left relation and compares it against all of the records of the right relation, unless it finds a match. The process continues unless all of the rows of the left relation are processed.
### HashedNGram
In this strategy, we first build a hash table of the _model_ column of the left relation (Table#1). Next, we probe it with the n-grams of the _model_ and _description_ column to find the matches.  

## Performance Comparison
- No. of Left Records:   3880
- No. of Right records:  100344
- Both techniques manage to join 30031 records.

#### Latency of CrossJoin strategy:   ~35000 ms
#### Latency of HashedNGram strategy: ~6500 ms

## Instructions
Please update Configuration.java file to set up the source and destination file.
In case of any issue after the first project load, please follow _File > Invalidate Caches / Restart_ option.

## Assumptions

- We match the whole _model name_ from table#1 into table#2. We assume that either the _model name_ exists in the model or description column of the second table or it does not. Eyeballing at the results shows that deviation from this technique may give rise to false positives. Currently, we do not have a way to quantify _false positives_.
- We find a first single match for each of the record in the right relation (Table#2).
- Records start right after the row containing the header of the relation in the excel sheet.
- We only have number or string data in the columns.
- A record with a valid (numeric) id value is a valid record else an invalid record.
- All record ids are integers.

