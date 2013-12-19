GRAPH=whyTag.pdf resolve.pdf whyTagSimpl.pdf
all: main.pdf

main.pdf: main.tex $(GRAPH)
	pdflatex main.tex

%.pdf:%.ps
	ps2pdf $<

%.ps:%.dot
	dot -Tps $< -o $@
