build:
	jflex Lexer.flex
	javac *.java
run:
	java Parser
clean:
	rm *.class