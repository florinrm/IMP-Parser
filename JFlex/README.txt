Tema Limbaje Formale si Automate - IMP Parser
Florin-Razvan Mihalache
336CB

    Pentru aceasta parte a temei, am plecat de la exemplul de JFlex dat de echipa,
preluand clasa Symbol si metoda de adaugare de taburi pentru afisarea AST-ului.

    Parsarea
    Mi-am facut gramatica pentru a face match pe limbajul din input si in functie
de ce am la match fac procesari pe elementele din stiva si operatii in stiva din
automat.

    Interpretarea
    Am facut o clasa de tip Singleton (ca sa am vizibilitate globala), in care 
am un dictionar pentru a retine perechile variabila-valoare, un set pentru a 
tine minte variabilele declarate (pentru cazul cand am o variabila care e nu e 
declarata, dar care apare in cod) si un int pentru linie ca sa afisez mesajul 
de eroare.