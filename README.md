# DataMerge
## Application Features
Application is flexible enough to read (and sink) data from (and to) different sources. For this particular assignment, under the hood, data sources and sinks use a single shared workbook. It also employs a simple configuration mechanism. Further, it is flexible enough to employ any new strategy to merge data from _two_ sources. We implement two strategies, named as CrossJoin and HashedNGram.  
### CrossJoin
Cross join is a naive merging method. It picks up a row of the left relation and compares it against all of the records of the right relation, unless it finds a match. The process continues unless all of the rows of the left relation are processed.
### HashedNGram
In this strategy, we first build a hash table of the _model_ column of the left relation (Table#1). Next, we probe it with the n-grams of the _model_ and _description_ columns of the right relation to find the matches.  

## Performance Comparison
- No. of records in left relation (Table# 1):   3880
- No. of records in right relation (Table# 2):  100344
- Both techniques manages to join 30031 records.

#### Latency of CrossJoin strategy:   ~35000 ms
#### Latency of HashedNGram strategy: ~6500 ms

## Instructions
Please update Configuration.java file to set up the source and destination file.
In case of any issue after the first project load, please follow _File > Invalidate Caches / Restart_ option.

## Assumptions

- We match the complete _model name_ string from table#1. We assume that the complete character sequence for _model name_ either exists in the model or description column of the second table or it does not. Eyeballing at the results shows that deviation from this technique may give rise to false positives. Currently, we do not have a way to quantify the false positives.
- We only find the first match for each of the record in the right relation (Table#2).
- Records data start right after the row containing the header of the relations in the excel sheet.
- We only have numeric or string data in the columns.
- Only if a records has a valid (numeric) id, it is a valid record.
- All record ids are integers.

