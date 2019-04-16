Zuul é um subprojeto do springCloudNetflix e é muito utilizado 
como gateway para os microserviços, fazendo toda parte de 
balanceamento de carga e de roteamento.

Uma request para /foo, o Zuul irá mapear essa request para o
serviço com sprint.application.name=foo

Uma request para /categories/1, o Zuul irá mapear e enviar /1 
para o serviço categories. Isso acontece pois por padrão
o zuul não aceita prefixos, é necessário configurar
zuul.stripPrefix=false para desabilitar esse comportamento.

Todos os serviços são automaticamente adicionados ao gateway
por padrão, para ignorar, basta mapear os serviços com
zuul.ignoredervices= <pattern>



======================================================
Zuul utiliza a biblioteca Ribbon para fazer o load balance das requests
e esse load balance é um client-side load balancer. Nesse caso,
não existe um servidor entre as requisições do cliente e o servidor
distruindo a carga (AWS Load Balance). Ao invés disso, existe
uma lista de instancias no cliente fazendo com que ele saiba
quais outras instancias existem e ele mesmo (client) é responsável
por fazer o balanceamento da carga. O client-side só pode
ser realizado via software.

Existem duas principais annotation para configurar o load balance:
@LoadBalanced --> Usado para definir um restTemplate que 
vai suportar tbm load balancing.
@RibbonClient --> usado para customizar configurações and quando
o Service Discovery está ausente. 

