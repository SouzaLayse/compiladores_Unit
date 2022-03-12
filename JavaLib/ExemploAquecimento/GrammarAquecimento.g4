grammar GrammarAquecimento;

import LexerAquecimento;

expr: expr SUM expr
    | INT
    ;
