JFLAGS = 
JC = javac

CLASSES = Node.java Debt.java Graph.java Main.java 

all: run

.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
		$(RM) *.class

run: classes
		java Main exemples/1

graph: classes run
		cat dettesNoCycles.gv | dot -Tpng > nocycle.png
		cat start.gv | dot -Tpng > start.png