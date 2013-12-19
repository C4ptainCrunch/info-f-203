GRAPH=whyTag.pdf resolve.pdf whyTagSimpl.pdf
all: main.pdf

main.pdf: main.tex $(GRAPH)
	pdflatex main.tex


%.pdf:%.dot
	dot -Tpdf $< -o $@
