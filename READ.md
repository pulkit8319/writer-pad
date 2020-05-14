# Writer Pad

Steps(Using eclipse inbuild tomcat):
1. Build the project using command:
 mvn clean install
2. If you are using eclipse inbuild tomcat. Just write click on file WriterpadApplication.java and click on "Run as">"Java Application".


DB used: MongoDB
DB name: writerpad
Collections:
articles - For storing article JSON
customsequences - For storing last used slug_uuid number as mongoDB does not has the feature of auto sequencer.



