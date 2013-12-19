GRAPH=whyTag.pdf resolve.pdf whyTagSimpl.pdf

all: main.pdf

main.pdf: main.tex $(GRAPH)
	pdflatex main.tex
	pdflatex main.tex


%.pdf:%.dot
	dot -Tpdf $< -o $@

graphs: $(GRAPH)

cleanall: cleang cleantex
cleantex:
	$(RM) main.aux main.log

cleang:
	$(RM) $(GRAPH)

clean: cleanall
	$(RM) main.pdf


