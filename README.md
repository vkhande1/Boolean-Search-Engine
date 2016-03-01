# Boolean-Search-Engine
Console Based Search Engine for Movie Reviews

Language - Java

########################## Tokenizaton ##############################

Program reads a input files (HTML documents) and outputs all the terms that occur in it in lexicographic order.

I have Extracted only terms between - 

![image](https://cloud.githubusercontent.com/assets/11687081/11131941/1eb8f40e-895b-11e5-9863-eb4fc06f005d.png)

And Ignoring HTML tags such as - 

![image](https://cloud.githubusercontent.com/assets/11687081/11131713/060fb06a-895a-11e5-897f-e5bc9037f8aa.png)

In general terms will be separated by a space. But there are exceptions - One of them is that
the words are connected by – or by a single -. In such cases I have separated the words.
A challenge was to get rid of punctuation when it is not part of the word. Get rid of “ or ( or [ 
when they are the first character of the token, and “, ), ] when they are the last character of the token.
A comma, a question or an exclamation mark at the end of the token should be deleted (most of the time). 
When the word ends in a period, it was challenge to decide whether it should be kept since it is the last 
character of an abbreviation, or be deleted since it indicates the end of a sentence. 
Stored words in lower case and sorted order. Tokens with Special character are on the Top of the list.

I have attached sample HTML Files and output files for reference.


########################## Indexing, Dictionary, Posting List ##############################

The goal of this part was to read a list of movie reviews, and store on disk an index and a list of (document number, title) pairs.
The indexing program will read the files and create an index.  Since the files of the corpus are HTML files, I have removed tags,
and “noise” as shown in Project part 1. The term “noise” is used for the parts of the pages (some of the header and footer) that 
should not be indexed.
 
The index will include a dictionary of all the indexed terms, and the postings lists for each. Each record of the dictionary will 
contain, the term, the number of documents containing the term (df - document frequency), and the offset to the posting list for the term.
Each record in the posting list will include a docID and tf - term frequency. A record will be generated for term t if it is mentioned
anywhere in the non noise part of the review. I have Used code of part1 to extract index terms.
 
In addition to the index it will also generate a document table with the docIDs and the title of each document, this table will be
used by the retrieval program to display the results.
 
The input parameter to the program is:
A directory path name
 
The output files will be:
Dictionary.txt
Postings.txt
DocsTable.txt
 
EXAMPLE
 
There are 2 documents 1 and 2.
Doc1 title = Titanic
Doc 2 title = Casablanca
Doc 1 body = Titanic movie. Titanic disaster. Titanic boat.
Doc 2 body = German occupation movie.
 
![image](https://cloud.githubusercontent.com/assets/11687081/11132522/578979ea-895e-11e5-8613-52f61394f56b.png) 

I have attached sample HTML File and output file for reference.

########################## Query User Interface ##############################

This program will perform the retrieval.  First, it will read the dictionary, postings file and the document table and store them
in memory. Then it will start to perform the query processing. It will read from the screen many queries, and will terminate
only after you print the command EXIT. For each query, it will compute and write on the screen a list of retrieved documents 
ordered by docID. The query and the results will be written also to an output file.
 
For simplicity the query q can be:
A single word
An AND query that starts with the word AND followed by a list of two or more words. For example (AND action war).
An OR query that starts with the word OR followed by a list of two or more words. For example (OR action romance).
 
It will Change all query words to lower case before performing retrieval.
 
For Example - 
The one word query (thriller) should retrieve all documents that have the word thriller in the “non noise” text (either body or title).
For example the document 0002.HTML should be retrieved since the word thriller is contained in its text.
 
The two word query (AND Jeff Goldblum) should retrieve all movie reviews that mention Jeff and Goldblum.  0003.HTML should be retrieved
since it contains the name of the actor.  Note that even though the name appears in parenthesis in the document it should still be 
retrieved.
 
The queries (AND beyond therapy love story) should retrieve all documents with the all these words.  Document 0003.HTML should be
retrieved.
 
The query (OR mystery thriller action) should retrieve all documents that contain any of these three words in their non noise text.
Both 0002.HTML and 0004.HTML will be retrieved.
 
The output of the program will be a file called output.txt.  Line 1 will contain the query. Lines 2 to line n, where n is the number
of documents retrieved, will contain: The document number, and the title.  The output is in order by non decreasing document
numbers. If the query did not retrieve any documents the program will print NO RESULTs in the line following the query.

I have attached sample HTML File and output file for reference.
