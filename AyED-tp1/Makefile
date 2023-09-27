pdf:
	pdflatex tp_grupal.tex

ignorando-errores:
	pdflatex -interaction=nonstopmode tp_grupal.tex

bibliografia:
	pdflatex tp_grupal.tex
	bibtex tp_grupal.aux
	pdflatex tp_grupal.tex
	pdflatex tp_grupal.tex

clean:
	- rm -f *.log *.soc *.toc *.aux *.out  main.idx *.bbl *.bbg *.dvi *.blg *.lof *.nav *.snm *~

