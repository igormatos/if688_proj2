# ASTs MiniJava

Este exercício tem o objetivo de exercitar os conceitos de análise sintática vistos em sala de aula. A ideia é implementar um analisador sintático para a linguagem MiniJava, que consiste em um subconjunto de Java, cuja gramática em forma BNF é descrita no link abaixo.

Adicione ações semânticas ao seu parser para produzir sintaxe abstrata para a linguagem MiniJava. Este exercício pode ser feito em dupla.

O código do seu analisador sintático deve estar em um repositório acessível online, como GitHub ou BitBucket. Responda esta tarefa colocando o link para o repositório com a sua resposta. Um membro da equipe deve responder com o link, informando quem é parte da equipe, e o outro apenas marca a tarefa como concluída informando quem postou (ou postará) o link.

Além disso, assim como na tarefa anterior, submeta os arquivos fonte aqui. No caso, só é necessário enviar os arquivos fonte ANTLR. Nomeie os arquivos com os logins dos envolvidos na resolução. No caso de resposta individual, basta ser com seu login, no caso de dupla, login1_login2.


###### Objetivo: Adicionar ações semânticas ao seu parser para produzir sintaxe abstrata para a linguagem MiniJava.

## Passos
https://docs.google.com/document/d/1CnoayBf_6ggDuyo2ZUZRaGBu66NKxHlciqjiADAuOXI/edit

## BNF MiniJava
https://docs.google.com/document/d/1SBARSIKX-4iU5h-g-2lEo7xISRtN9jUFDiI0cdXI4bs/edit


# Type Checking em MiniJava


###### Objetivo: Projetar visitors para checar os tipos de qualquer programa MiniJava.

Passos envolvidos:
Baixe o arquivo symboltable.zip. Este arquivo consiste das classes Java utilizadas para representação da tabela de símbolos (completamente implementado já);
A classe SymbolTable tem a informação de tipo de todas as classes declaradas no programa.
Cada nome de classe é mapeado num objeto Class, que tem toda a informação de tipo dos atributos e declarações de método. O mapa de nomes de classe para objetos Class está contido na classe SymbolTable;
Cada método é mapeado num objeto Method, que tem toda a informação de tipo dos parâmetros, tipo de retorno e variáveis locais. O mapa de nomes de métodos para objetos Method está contido na hashtable methods que está contida na entrada da classe onde o método foi declarado;
Similarmente, cada variável é mapeada a uma instância da classe Variable, armazenando a informação de tipo da variável. Este mapeamento é armazenado nas hashtables locais de um objeto Method, ou dentro da hashtable globals de um objeto Class, dependendo de onde a variável foi declarada (como variável local de um método ou como atributo de classe).
As classes BuildSymbolTableVisitor e TypeCheckVisitor estão incompletas e tem apenas o esqueleto do código que visita a AST de um programa MiniJava. Para este exercício, você preencherá o código destes arquivos
A checagem de tipos de um programa se dá em duas fases. Na primeira, você precisa construir a tabela de símbolos (SymbolTable) com a informação de tipo declarada. A implementação desta fase estará contida na classe BuildSymbolTableVisitor
Estude as classes SymbolTable e BuildSymbolTableVisitor;
Em BuildSymbolTableVisitor você deve adicionar código que processa nós da AST que declaram classes, métodos, variáveis, etc.
Ao visitar nós derivados de ClassDecl, você deve primeiro criar uma entrada na tabela de símbolos para o nó ClassDecl sendo visitado, usando o método addClass(...) da classe SymbolTable. Então, a variável currClass de BuildSymbolTableVisitor deve ser assinalada para a nova entrada (o objeto Class) do nó ClassDecl sendo visitado. Desta forma, ao fazer referência à variável currClass dentro dos métodos visit para os nós de VarDecl e MethodDecl permitirá a adição de variáveis e métodos de uma declaração de classe (ClassDecl) no objeto correto da tabela de símbolos;
De maneira similar, ao visitar nós MethodDecl, a variável currMethod de BuildSymbolTableVisitor deve ser assinalada para o objeto Method correspondente ao nó MethodDecl atual. Assim, dentro de outros métodos visit é possível adicionar variáveis locais e parâmetros formais de um MethodDecl no objeto Method correto.
Uma vez que todas as declarações foram processadas e armazenadas no objeto SymbolTable, podemos fazer a checagem de tipo de instruções e expressões. A implementação desta fase estará contida na classe TypeCheckVisitor
Toda instrução e expressão tem suas próprias regras de tipo. A seguir há uma lista incompleta de tais regras. Outras podem ser derivadas a partir da especificação da linguagem Java (qualquer programa MiniJava válido é um programa Java válido)
Operandos de operações aritméticas devem ter o mesmo tipo, que deve consistir do tipo Integer;
Operandos de operações lógicas devem ter o mesmo tipo, que deve consistir do tipo Boolean;
Expressões condicionais de if e while devem ser do tipo Boolean;
O lado direito de uma instrução de atribuição deve ser do mesmo tipo que o lado esquerdo;
Na classe Main, você pode colocar algo como:
//programa na forma de AST - obter objeto a partir do parser...
Program prog = ...;
BuildSymbolTableVisitor stVis = new BuildSymbolTableVisitor();
//construindo tabela de símbolos
prog.accept(stVis);
//fazendo a checagem de tipos
prog.accept(new TypeCheckVisitor(stVis.getSymbolTable()));
Utilize os arquivos de teste de MiniJava (e os altere) para testar
