JFLAGS = 
JC = javac

CLASSES = Node.java Debt.java Graph.java Main.java  DebtStack.java
OBJECT = $(CLASSES:.java=.class)

all: $(OBJECT)

$(OBJECT):$(CLASSES)
	$(JC) $(JFLAGS) $^

clean:
	$(RM) *.class

