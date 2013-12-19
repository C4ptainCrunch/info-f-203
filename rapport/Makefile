GRAPH=whyTag.eps resolve.eps whyTagSimpl.eps
all: main.pdf

main.pdf: main.tex $(GRAPH)
	pdflatex main.tex

%.eps:%.ps
	ps2eps $<

%.ps:%.dot
	dot -Tps $< -o $@
