**List Implementations**

**List** usamos quando queremos acessar os elementos dessa collection através do seu índice e quando queremos ter a possibilidade de ordena-los, permitindo ou não elementos repetidos.

- **ArrayList**: Funciona como um array de tamanho dinamico. Você pode consultar elementos em tempo constante O(1) através do indíce, porém adicionar/remover é mais lento do que consultar.
- **LinkdedList** É um mix de Array com Queue. Tem todos os métodos de uma List, além dos métodos adicionais para remover/adicionar do final e do começo de uma List (Que nesse caso funciona como uma Fila). A **vantagem** é que você pode remover/adicionar do começo/final da collection em O(1), **mas em troca** você tem um tempo linear O(n) para consultar outros indices.



**Set Implementations**

**Set** usamos quando não queremos ter elementos repetidos dentro de uma collection e também quando não nos importamos com a ordenação dos elementos.

- **HashSet** Armazena os elementos em uma tabela hash e para isso o `hashCode()` da classe. Adicionar/consultar elementos na collection tem um tempo O(1), porém vc acaba perdendo a possibilidade de ordenação.
- **TreeSet** Armarzena os elementos em uma arvore mantendo os elementos ordenados pela chave. O lado negativo é que adicionar/consultar leva um tempo de O(log n).



**Queue Implementations**

**Queue** usamos fila quando os elementos são removidos/adicionados em uma ordem especifica. A ordenação dos elementos é mais importante que o processamento dos mesmos.

- **LinkdedList** : É uma mistura de lista com Fila. Essa Fila é uma DEQUEUE (Double Endend Queue - Fila Duplamente Terminada) que permite a remoção/adição de elementos tanto da cabeça quanto da cauda.
  - Vantagem: Implementa uma lista e uma fila;
  - Desvantagem: Não de fato uma fila pura;
- **ArrayDeque**: É uma DEQUEUE pura. É mais eficiente que a LinkdedList.



**MAP Implementation**

**MAP** Usado quando se tem a necessidade de trabalhar com o par chave/valor.

- **HashMap**: armazena as chaves em uma tabela hash. A vantagem é que para recuperar elementos tem tempo constante O(1) e a desvantagem é que não é possível manter a ordem de inserção.
-  **TreeMap** Armazena as chaves em uma arvore ordenada. A vantagem é que a ordem se mantém conforme os itens são inseridos, porém para recuperar/inserir um elemento o tempo é de O(log n).



**JVM Memory Management**

A JVM tem um espaço de memória que é divido em várias regiões, sendo algumas dessas regiões compartilhadas.  O esquema a seguir mostra como é  feita essa organização

![jvmMemory](https://github.com/systane/courses/blob/master/javaFundamentals/jvmMemory.png)

**Method Area** e Heap são regiões compartilhadas. Na Method Area, são armazenadas informações a nível de classe como nome de classes, métodos, informações de variáveis, incluindo variáveis estáticas. Já o **Heap** armazena informações sobre todos os objetos e suas instancias de variavies e arrays correspondentes em tempo de execução da aplicação. Novos objetos são criados no heap, e referencias para estes objesto são armazenados na stack. o Heap usa o garbage collector para poder limpar sua memória. A **Stack** memory contém valores primitivos e referencias para objetos que estão no heap. Todos esses objetos na stack são referenciados a partir de métodos, então logo após o método finalizar, eles são removidos da memória automaticamente. Diferentemente do que acontece no Heap que tem um sistema que limpa a memória, o Stack realiza a limpeza de modo automático.



**Garbage Collector**

É um módulo da JVM que é responsável por realizar a remoção de objetos sem referencias (objetos inalcançáveis) do Heap. Existe uma função **System.gc()** que sugere ao Java para executar o garbage collector, porém não tem garantia de que isso realmente vai acontecer, pois a JVM escolhe o melhor momento para executar essa limpeza.  Existem também diversos algoritmos para o GC, nas versões mais recentes do Java (11 e 12) foram realizadas algumas otimizações nesse módulo.

Apenas objetos, e não referencias, podem ser removidos do Heap. **Referencias** são variaveis que tem nome e são normalmente acessão o conteúdo de um objeto. Uma referencia pode ser apontada para outra referencia, passada para um método ou retornada do mesmo. Todas as referencias tem o mesmo tamanho, independente do seu tipo, além disso as referencias podem ser criadas tanto na stack quanto no heap. Já **objetos** são criados apenas no HEAP e não recebem nomes, além de poderem ser acessados somente via uma referencia, não podendo ser passados como parametros para métodos, nem retornados e muito menos atribuidos. Objetos tem tamanhos diferente e isso vária de acordo com a definição da suas respectivas classes.

O GC (Garbage Collector) remove apenas objetos que são elígiveis, ou seja objetos que são inalcançaveis. Essa situação pode acontecer em dois casos:

- Objetos sem referencias (Por exemplo ao atribuir null a uma referencia ou apontando a referencia para um outro objeto, ou até mesmo criar um objeto anonimo - `new MeuObjeto("objeto anonimo")`).
- Todas as referencias de um objeto que saiu do escopo (Por exemplo os objetos criados dentro de um método que terminou sua execução. Todos eles serão eligiveis para o GC a menos que alguma referencia de um obj especifico seja retornada.).

![objetoInalcancavel](https://github.com/systane/courses/blob/master/javaFundamentals/objetoInalcancavel.png)

No exemplo acima, o **objeto 4 do tipo inteiro** tornou inalcançavel pois perdeu sua referencia.



**String Pool** 

Pool de strings é um lugar dentro do HEAP que é responsável por armazenar todos os valores literais de Strings para que estes possam ser reutilizados na aplicação mais tarde. Quando criamos uma string, primeiro o java procura no pool de string de já existe esse literal, caso exista uma referencia para este espaço no heap é retornada. Senão, ele cria um literal no pool e retorna a referencia. Ao criar uma string usando a keyword `new` o Java cria uma nova String fora do pool mas ainda dentro do Heap, tornando o objeto suscetível de ser elegível pelo GC. O GC não consegue limpar os objetos criados dentro do pool de strings, somente os que foram criados fora dessa área (demais regiões do Heap).

É necessário tomar cuidado ao criar/concatenar String usando o operador `+` pois sempre estaremos criando novos objetos no pool, diminuindo assim a performance da aplicação. Uma saída para esse problema é usar o **StringBuilder**, pois ao contrário da String, ele é mutável, o que permite assim que um objeto seja alterado sem que seja criado a cada `append()` uma nova String no pool. O StringBuilder consegue alterar seu próprio estado e retornar uma referencia para ele mesmo quando usando o `append()`.

**Vulnerabilidade do String Pool**: O Pool de Strings tem suas desvantagens, uma vez que o GC não pode limpar essa área do Heap, armazenar dados sensíveis em String não é recomendado, pois se um invasor tiver acesso a essa área da memória, o pool de string vai estar complemento exposto. Para informações sensíveis utilize um `char []`, pois assim o GC consegue limpar esses objetos da memória após eles perderem a referencia.  O problema dessa solução é que a JVM pode demorar para ativar o GC, e caso algum invasor tenha acesso a memória nessa janela de tempo, ainda sim o dado vai estar exposto no Heap. A recomendação é sobrescrever os dados sensíveis armazenados no array de char(setar 0 por exemplo), logo após que eles forem utilizados.



**Default method** 

`default` é uma keyword em java que serve para definir o nível de acesso de métodos ou para definir o corpo de um método em um interface. Caso o `default` seja utilizado para definir o nível de encapsulamento de um método/váriavel, quer dizer q esse método/variavel pode ser visto dentro do mesmo pacote no qual ele se encontra. Caso seja utilizado em interfaces, podemos definir a implementação de um método na interface que será comum a todos que assinarem esse contrato. Esse recurso foi incluído no java 8 para não quebrar a retrocompatibilidade.



**Lamda Function**

Lambda functions adiciona ao java recursos do paradigma funcional de linguagens como Scala, List, etc. Essas funções podem ser utilizadas no lugar de funções anonimas, streams, como parametro de métodos, etc. Nos casos de ser necessário utilizar como parametro de métodos uma lambda, é necessário ter uma **interface funcional**. 

**Interface funcionais** são interfaces com apenas um método abstrato e normalmente anotadas com `@FunctionalInterface`. Essa annotation não é obrigatória, porém é uma boa prática usa-lá, para deixar claro que essa interface é funcional e será utilizada em lambda functions, portanto só pode ter um método abstrato.

Exemplos de uso lambdas como parametro de métodos:

![lambdaAsParameter2](https://github.com/systane/courses/blob/master/javaFundamentals/lambdaAsParameter2.png)

Nesse primeiro exemplo o método `printIfLambdaReturnsTrue` recebe como parametro um lambda function que vai printar os números da `list` que são pares. Para isso ser possível, primeiro foi necessário criar uma interface funcional `FunctionalInterfaceTeste` que tem uma função abstrata. Essa interface será utilizada como parametro para representar a lambda que será passada como parametro no método `printIfLambdaReturnsTrue`.


![lambdaAsParameter1](https://github.com/systane/courses/blob/master/javaFundamentals/lambdaAsParameter1.png)	

Já nesse segundo exemplo foi utilizada a interface `Predicate<integer>` que vem no pacote `java.util.function` e serve como uma forma de generalização de interfaces funcionais. Nesse caso o método abstrato da Predicate recebe como parametro objeto qualquer e retorna sempre um boolean.

Assim como a Predicate, existem outras interfaces funcionais dentro do mesmo pacote como, Consumer e Supplier. A primeira tem um método abstrato void e recebe qualquer objeto como parametro, já a última tem um método que não tem parametro, mas retorna um objeto de qualquer tipo.

![consumer](https://github.com/systane/courses/blob/master/javaFundamentals/consumerInterface.png)

No primeiro exemplo, foi criado uma lambda para apenas printar os valores que forem passados no método abstrato. Já o segundo no segundo exemplo, foi criada uma lambda para multiplicar por 2 cada elemento da lista que o método accept receber.


**Comprator & Comparable**

**Comparable** É uma interface que contém o método `compareTo()`, esse método é sobrescrito nas classes que assinam esse contrato, assim é possível definir como vamos ordenar objetos dessa classe. As classes de tipo de referencia implementam a interface Comparable, mas se quisermos implementar nosso próprio `compareTo()` temos que seguir as três seguintes regras de retorno para esse método:

- retornar 0: Quando o objeto atual é igual ao argumento recebido na função.
- retornar nº negativo < 0: Quando o objeto é menor do que o argumento recebido na função.
- retornar nº positivo > 0: Quando o objeto é maior do que o argumento recebido na função.

**Comparator** É uma interface funcional (logo podemos usar as lambdas functions) que é utilizada quando não queremos assinar a interface Comparable ou quando queremos ordenar um objeto de uma outra maneira, sem ser a que foi definida pela função `compareTo()`.

Todo objeto pode ser comparavel, mas para isso a classe desse objeto precisa implementar a interface comparable ou utilizar algum comparator quando for realizar a ordenação dos elementos.

No próximo exemplo, foi implementado a interface Comparable, assim como também o método compareTo. Assim para ordenar a list, basta utilizar o método sort da Collections. No primeiro trecho foi utilizado a interface consumer para definir uma lambda que realize esse processo de ordenação. 
![comparable](https://github.com/systane/courses/blob/master/javaFundamentals/comparable.png)


Nesse outro exemplo, a classe que define a variável Objeto não implementa a interface Comparable, então para ordenar os elementos dessa lista, foi preciso definir um comparator.

![comparator](https://github.com/systane/courses/blob/master/javaFundamentals/comparator.png)



**Bounds with Generics**

**Wildcard generic type**  é usado para representar um tipo generico desconhecido, e usamos um ponto de interrogação para isso ( `?` ). É através desse operador que conseguimos utilizar generic wildcards e impor limites ao Generics. Existem 3 tipos de limites que podemos setar no generics:

- **Unbounded wildcard** É representado normalmente pela sintaxe `?`. Nesse caso, a `?` representa qualquer tipo de objeto.

![unboundedWildcard](https://github.com/systane/courses/blob/master/javaFundamentals/unboundedWildcard.png)

​	No exemplo, podemos receber uma List de qualquer tipo.

- **Wildcard with an upper bound**  É representado normalmente pela sintaxe `? extends type`. Podemos definir um limite superior conforme a imagem abaixo.
- **Wildcard with a lower bound** É representado normalmente pela sintaxe `? super type`.











Tópicos para rever:

- NOSQL Databases;
- Limite no generics (OCP2 - cap 3);
- Design Patterns (OCP2 - cap 2);
- SOLID;

