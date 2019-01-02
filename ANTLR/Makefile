build:
	java -Xmx500M -cp "/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH" org.antlr.v4.Tool Hello.g4 -visitor
	javac Hello*.java MyVisitor.java Main.java -cp antlr-4.7.1-complete.jar
	export CLASSPATH="$CLASSPATH:/usr/local/lib/antlr-4.7.1-complete.jar"
clean:
	rm *.class