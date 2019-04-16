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


