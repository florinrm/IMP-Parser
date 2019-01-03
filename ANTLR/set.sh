#!/bin/bash

if [ $1 = "0" ]; then

	# Transformam gramatica Hello.g4 in fisiere Java folosind ANTLR
	java -jar antlr-4.7.1-complete.jar Hello.g4 -visitor
	javac Hello*.java MyVisitor.java Main.java -cp antlr-4.7.1-complete.jar

else
	DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
	ANTLR_FILE="/antlr-4.7.1-complete.jar"
	FILE_PATH="$DIR$ANTLR_FILE"
	export CLASSPATH="$CLASSPATH:$FILE_PATH"
	java  Main > fack
    cat fack
    rm fack
fi