#!/bin/bash
# Descarcam antlr4 -> Rulati comanda de mana mai bine
# sudo curl -o /usr/local/lib -O https://www.antlr.org/download/antlr-4.7.1-complete.jar

# Setam alias-uri pentur antlr4 si grun
 antlr4='java -Xmx500M -cp "/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH" org.antlr.v4.Tool'
 grun='java -Xmx500M -cp "/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH" org.antlr.v4.gui.TestRig'

# Transformam gramatica Hello.g4 in fisiere Java folosind ANTLR
export CLASSPATH="$CLASSPATH:/usr/local/lib/antlr-4.7.1-complete.jar"
java -Xmx500M -cp "/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH" org.antlr.v4.Tool Hello.g4 -visitor
javac Hello*.java MyVisitor.java Main.java -cp antlr-4.7.1-complete.jar


if [ -z "$1" ]
then
    for i in {1..3}
    do
        cat input$i
    	java Main input$i
    done
else
    cat input$1
    java Main input$1
    cat input$1 | java -Xmx500M -cp "/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH" org.antlr.v4.gui.TestRig Hello main -gui
fi

rm .class .interp *.tokens Hello*.java
