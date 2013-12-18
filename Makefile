JFLAGS = 
JC = javac

CLASSES = Node.java Debt.java Graph.java Main.java 

.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
		$(RM) *.class

graph: classes
		java Main exemples/1 | dot -Tpng > graph.png