Tema Limbaje Formale si Automate - IMP Parser - Bonus
Florin-Razvan Mihalache
336CB

Pentru aceasta parte a temei, am plecat de la exemplul de ANTLR dat de echipa.

Gramatica - Hello.g4
    La aceasta parte, am preluat gramatica din cerinta, observand ca in ANTLR se
muleaza bine tipul de gramatica din enunt aici, cu mici modificari.

Visitor
    Pentru fiecare regula / token din Hello.g4 exista o clasa (ex: MainContext),
asadar am facut cate o metoda visit pentru fiecare clasa. Am un String in clasa
MyVisitor, care va reprezinta arborele AST. In fiecare clasa care face parte din
AST concatenez reprezentarea acesteia la String si daca nu este nod-frunza vad
ce fel de copii are el si apelez visit pe fiecare copil al acestuia, continuand
astfel actiunea de la nodul-parinte.