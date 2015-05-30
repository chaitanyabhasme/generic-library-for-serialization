[
  adopted from http://www.cs.rochester.edu/u/www/courses/171/Fall-03/files/readme.txt
  by Deger Cenk Erdil
  for CS654 Distributed Systems
  This is a template README file about how you should form your own README file.
  In general,
        you should remove anything in between square brackets (i.e. [..]), and
        you should replace anything in between <> with a value.
]

CS542 Design Patterns
Spring 2014
PROJECT 4 README FILE

Due Date: Thursday, April 24, 2014
Submission Date:  Thursday, April 24, 2014
Grace Period Used This Project: 0 Days
Grace Period Remaining: 0 Days
Author(s): Chaitanya Bhasme
e-mail(s): cbhasme1@binghamton.edu


PURPOSE:

[
 The purpose of this assignment is to create a generic library for persistent storage of student and employee records. Apply the dynamic proxy pattern.
]

PERCENT COMPLETE:

[
  I believe I have completed 100% of this project.
]

PARTS THAT ARE NOT COMPLETE:

[
 None.
]

BUGS:

[
 None.
]

FILES:

[
  Included with this project are 14 files:
  
  README.txt, the file you are reading
  build.xml, ANT build file
  src/genericCheckpointing/djsonStoreRestore/StoreRestoreHandler.java, class which implements InvocationHandler
  src/genericCheckpointing/driver/Driver.java, main Driver class
  src/genericCheckpointing/server/RestoreI.java, Restore interface extends StoreRestoreI interface
  src/genericCheckpointing/server/SerializableObject.java, base class for all the serializable objects
  src/genericCheckpointing/server/StoreI.java, Restore interface extends StoreRestoreI interface
  src/genericCheckpointing/server/StoreRestoreI.java, StoreRestoreI tag interface
  src/genericCheckpointing/util/DataStore.java, DataStore interface for storing the data
  src/genericCheckpointing/util/DeSerializedDataStore.java, class which stores the Deserialized Objects
  src/genericCheckpointing/util/EmployeeRecord.java, EmployeeRecord extends serializable object
  src/genericCheckpointing/util/ProxyCreator.java, ProxyCreater class which creates the proxy instance
  src/genericCheckpointing/util/SerializedDataStore.java, class which stores the Serialized Objects
  src/genericCheckpointing/util/StudentRecord.java, EmployeeRecord extends serializable object
]

SAMPLE OUTPUT:

[
	sample command used: ant -Darg0=2 -Darg1=in.txt run
	
	jar:
    [mkdir] Created dir: /import/linux/home/cbhasme1/Downloads/sem2/DP/assign4/build/jar
      [jar] Building jar: /import/linux/home/cbhasme1/Downloads/sem2/DP/assign4/build/jar/genericCheckpointing.jar

	run:
     [java] Object Match:genericCheckpointing.util.EmployeeRecord;ii:0;ff:0.863;dd:0.135;ll:-2165;
     [java] Object Match:genericCheckpointing.util.StudentRecord;ii:0;ss:771;bb:true;
     [java] Object Match:genericCheckpointing.util.EmployeeRecord;ii:1;ff:0.924;dd:0.04;ll:2067;
     [java] Object Match:genericCheckpointing.util.StudentRecord;ii:1;ss:150;bb:false;

	BUILD SUCCESSFUL


]

TO COMPILE:

[
  Please extract the tar file, go to the directory Chaitanya_Bhasme_assign4 and do-> ant compile
]

TO RUN:

[
  Please go the the extracted directory Chaitanya_Bhasme_assign4
  run as: ant -Darg0=<NUMS_OF_OBJECTS> -Darg1=<FILE_NAME> run
  For example: ant -Darg0=2 -Darg1=in.txt run
]

EXTRA CREDIT:

[
  N/A
]


BIBLIOGRAPHY:

This serves as evidence that we are in no way intending Academic Dishonesty.
[
  -Java The Complete Reference, Eighth Edition, Author - Herbert Schildt, Tata McGraw-Hill.
]

ACKNOWLEDGEMENT:

[
]
