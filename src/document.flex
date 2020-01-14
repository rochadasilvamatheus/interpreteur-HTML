%%
%public
%class Lexer
%unicode
%type Token
%line
%column

%{
   public String getPosition(){
      return "Reading at line "+yyline+", column "+yycolumn;
   }

%}

%yylexthrow Exception
%state SPECIAL

blank = [\n\r\t] 
mot = [^\n\r\t\\{}]+
%%
<YYINITIAL> {
 {mot}			{return new StringToken(Sym.MOT, yytext());}
 "\\"			{yybegin(SPECIAL);}
 "{"			{return new Token(Sym.LACC);}
 "}"			{return new Token(Sym.RACC);}
 <<EOF>>		{return new Token(Sym.EOF);}
 {blank}		{}
}

<SPECIAL> {
 "begindoc"		{yybegin(YYINITIAL);return new Token(Sym.BEGINDOC);}
 "enddoc"	 	{yybegin(YYINITIAL);return new Token(Sym.ENDDOC);}
 "linebreak"		{yybegin(YYINITIAL);return new Token(Sym.LINEBREAK);}
 "bf"			{yybegin(YYINITIAL);return new Token(Sym.BF);}
 "it"			{yybegin(YYINITIAL);return new Token(Sym.IT);}
 "beginenum"		{yybegin(YYINITIAL);return new Token(Sym.BEGINENUM);}
 "endenum"		{yybegin(YYINITIAL);return new Token(Sym.ENDENUM);}
 "item"			{yybegin(YYINITIAL);return new Token(Sym.ITEM);}
 [^]			{throw new Exception("Lexer error at line "+ yyline + " column " + yycolumn);}
}