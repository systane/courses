## Infraestrutura

- Regions: Local físico no mundo, no qual a AWS agrupa datacenters, sendo que cada datacenter forma um grupo lógico conhecido como zona de disponibilidade (Availability Zones - AZs).Cada região da AWS consiste em, no mínimo em 3 AZs isoladas e separadas fisica e geograficamente, e no máximo 6 AZs;
- Availability Zones: É um ou mais datacenters distintos com energia, rede e conectividade redundantes em uma region da AWS. Cada AZs está conectada entre si com uma alta banda e baixa latencia de rede
- AWS Data Centers: Cada datacenter fica separada um do outro em um raio de até 100km, e eles oferecem uma baixa latência;
- AWS Edge Locations/Points of Presence: São baiscamente servidores de cache que são localizados em grandes cidades ou grandes centros industriais ao redor do mundo. O objetivo principal das edge locations é diminuir a latência, deixando a aplicação/servidor próxima ao usuário, pois as vezes o usuário mora longe de uma region, mas pode estar perto de um edge location.


#### How to choose an AWS Region?
Vai depender de vários fatores, sendo alguns deles:
- Compliance com o governo e questões legais: Alguns governos exigem que os dados da aplicação residam no país no qual ela está sendo desenvolvida;
- Proximidade com os usuários finais para reduzir latencia;
- Disponibilidade dos serviços: Algumas regiões da AWS não possuem certos serviços habilitados;
- Preços: Varia de acordo com a região;


## AWS Organizations
Serviço global que permite vc gerenciar multiplas contas AWS, sendo que a conta principal é conhecida como "conta de gerenciamento" (managemente account), enquanto que as outras contas, são conhecimentos "contas membros (member accounts). As member accounts podem pertecer apenas a um organização.
O AWS Organizations considera a cobrança de todas as contas com apenas um método de pagamento, além disso, é possível conseguir descontos nas cobranças dos serviços, devido a alta utilização nas members accounts (EC2, S3, e etc). Vc também pode ter compartilhamento de instancias reservadas e descontos através de multiplas contas com "Saving Plans". Além disso, com o AWS Organization, vc pode ter APIs para automatizar o processo de criação de contas AWS
Com o AWS Organizations, vc pode ter "Unidades Organizacionais" (Organizational Units - OU) para organizar melhor suas contas AWS. E essa divisão pode ser feita de várias maneiras (divisão por negócio, ambiente ou projetos), conforme a imagem abaixo:
![Alt text](exemplo_OU.png)

#### Vantagens do AWS Organizations:
- Maior segurança e organização, pois multiplas contas separadas são mais seguras e mais fáceis de gerenciar do que uma única conta com múltiplas VPC;
- Facilidade no gerenciamentos de logs: Vc pode habilitar "CloudTrail" em todas as contas, envio de logs para um conta central de logging com S3 e cloudwatch;
- Habilitar iam roles compartilhadas entre contas para melhor administração das contas;
- Service Control Policies (SCP): Políticas do IAM aplicadas em OU ou Contas AWS (exceto a management account) para restringir Users e Roles do IAM. Essas políticas por default não liberam nenhum acesso, e precisam ser explicitadas. Abaixo tem um exemplo de cenário de uso para SCP:
![Alt text](SCP.png)
Nesse caso, temos 4 OUs (Root, Prod, HR e Finance) e 4 accounts (Management, A, B e C). A Root OU tem uma SCP que concede acesso de admin completo, e a management account está dentro dessa OU porém, com uma SCP que bloqueia o acesso ao Athena. Nesse caso, a management account vai ter total acesso aos serviços da AWS, inclusive ao Athena, pois a SCP da OU é mais prioritária, garantindo total acesso. Para a account A, vamos ter um acesso total ao serviços da AWS (herdou da Root OU), porém não teremos acesso ao RedShift, pois a OU Prod tem uma SCP negando esse acesso e mesmo a account A permitindo com uma SCP o acesso ao RedShift, o acesso será negado, pois a negativa do SCP é mais prioritária.
Para a account B, vms ter total acesso aos serviços da AWS (herdou da Root OU), não teremos acesso ao RedShif (herdou da OU Prod), e também não teremos acesso à Lambda (herdou da OU HR). E por fim, para Account C, vamos ter total acesso aos serviços da AWS (herdou da Root OU) e bloqueio ao RedShift (herdou da OU Prod); 

Abaixo temos dois exemplos diferentes de SCPs:
![Alt text](exemplo_SCP.png)
Na imagem mais à esquerda, temos um cenário no qual apenas o dynamodb será bloqueado nessa conta ou OU, e os demais serviços serão liberados. Já na imagem mais à direita, apenas o ec2 e cloudwatch serão liberados, os demais serviços serão bloqueados, pois a SCPs trabalha com permissões explicitas.


## IAM (Identity and Access Management)

IAM (Identity and Access Management) é um serviço global que permite vc criar usuário e grupos para gerenciar esses usuários.
Os grupos podem conter apenas usuários, porém um usuário pode pertencer à zero ou mais grupos;

#### Policies: 
são documentos json que vão negar ou conceder permissão para que usuários ou grupos consigam acessar/executar determinados serviços da aws;
A policy consiste na seguinte estrutura, conforme a imagem:
![Alt text](exemplo_IAM_policy.png)
Version: Versão da linguagem de policy;
Id: Identificador da policy (opcional);
Statements: Um ou mais declarações (obrigatório)

Cada Statement consiste em:
Sid: Um identificador do statement (opcional);
Effect: Se o statement vai permitir ou negar o acesso;
Principal: account/user/role na qual a policy vai ser aplicada;
Action: Lista de ações que essa policy permite ou nega;
Resource: Lista de recursos no qual as ações serão aplicadas;

As policies podem ser aplicadas à nível de recurso ou à nível de objeto, por exemplo, na policy abaixo temos duas permissões diferentes, sendo a primeira uma policy à nível de recurso da aws (bucket) e a outra à nível de objetos que estão dentro do bucket:

![Alt text](exemplo_IAM_policy_2.png)

### Protegando os usuários do IAM:
#### Password Policy:
Com o IAM, vc pode criar políticas de senhas para garantir a segurança dos usuários do IAM. E essas políticas, podem definir um número mínimo de caracteres, exigir caracteres especificos (números, maiuscula ou minusculas, não alfanúmericos e etc), rotação de senha (senha expira), prevenção de reuso de senha e etc;

#### MFA (Multi Factor Authentication):
Com IAM, vc pode proteger seus usuários com o MFA, e a AWS aceita os seguintes tipos de MFAs:

- Virtual MFA device: São softwares/apps que geram um token de tempo em tempo (Google Authenticator e Authy, por exemplo); Nessa opção, vc pode configurar diversos tokens para diferentes usuários em um msm dispositivo (celular);
- FIDO Security Key: São dispositivos usb físicos que geram um token para ser utilizado no login a partir de um toque, sem a necessidade de digitar o código manualmente; Esse dispositivo consegue gerar token para diversos usuários do IAM em um msm dispositivo (Yubico é um exemplo);
- Hardware TOTP Token: São dispositivos físicos, porém eles não precisam estar conectados ao computador (Exemplo o RSA). Eles geram tokens que permitem que o usuário faça o login, porém precisa ter um dispositivo para cada usuário;

### IAM Roles:
Alguns serviços da AWS precisam executar ações (uma lambda, por exemplo, precisa enviar dados para o bucket do S3). Assim, precisamos liberar permissões para esses serviços através do IAM Roles. Fazendo um paralelo, é como se alguns serviços da AWS fossem utilizar usuários para executar determinadas ações, porém os serviços utilizam as roles ao invés dos users.

### IAM Security tools
- IAM Relatório de credenciais (account-level): Um relatório que lista todas as seus usuários de contas e os status de suas credencias;

- IAM Consultor de acesso (user-level): Tabela que mostra as permissões de serviços aws garantidas para um usuário e quando esses serviços foram pela última vez acessados; Com esse relatório, é possível revisar as políticas (policies) para cada usuário;

### IAM Conditions
É possível restringir acessos de um cliente através do IP que ele utiliza para fazer chamadas em uma API. Com o recurso "aws:SourceIp", conseguimos, por exemplo, montar a seguinte policy:

![Alt text](exemplo_IAM_conditions.png)

Nesse caso, estamos negando qualquer acesso aos serviços da AWS para todos os ranges de IPs que não forem "192.0.2.0/24" e "203.0.113.0/24". Um outro exemplo, é que podemos restringir clientes que estão fazendo chamadas a partir de uma região especifica da aws com o "aws:RequestedRegion":

![Alt text](exemplo_IAM_conditions_2.png)

Já nesse exemplo abaixo, podemos também restringir serviços baseados em tags de algum serviço da própria aws e/ou tags do próprio usuário com os recursos "[serviço]:ResourceTag/[Tag]" e "aws:PrincipalTag/[Tag]" respectivamente, sendo que [serviço] é o nome do serviço da aws na qual a tag está atrelada e [Tag] é o nome da tag em si. No exemplo abaixo, temos uma condição que permite iniciar ou parar instancias ec2, caso o recurso do ec2 tenha a tag com a chave e valor "Project = DataAnalytics" e caso o usuário tenha a tag com a chave e valor "Departament = Data".

![Alt text](exemplo_IAM_conditions_3.png)

Também conseguimos utilizar como condição das politicas do IAM o "aws:MultiFactorAuthPresent" que força a verificar se o usuário tem ou não o MFA habilitado, exemplo:

![Alt text](exemplo_IAM_conditions_4.png)

Podemos também retringir os acessos em certas contas que são membros de uma AWS Organization com o "aws:PrincipalOrgID". Vamos supor que temos uma politica que permite ações de put e get nos objetos do bucket S3 a partir de chamadas que são realizadas em contas que são "membros ou filhas" de uma AWS organization com id "o-yyyyyyy", nesse caso, teremos:

![Alt text](exemplo_IAM_conditions_5.png)

#### IAM Roles vs Resouce-Based Policies
Quando você assume uma role (usuário, aplicação ou serviço), você perde as suas permissões originais and toma possa das permissões garantidas pela role. Por outro lado, quando você usa uma política baseada em recurso (resource-based policy), você não perde suas permissões.

**Exemplo:** Suponha que um usuário da conta A (conta aws), precisa ler os dados de uma tabela do dynamodb que se encontra também na conta A e depois transferi-los para um bucket do S3 de backup que se encontra em uma conta B.
Caso o usuário da conta A assuma uma role da conta B para conseguir transferir os dados para o bucket de backup da conta B, ele vai perder as permissões de leitura da tabela do dynamodb (que se encontra na conta A). Neste cenário, o ideal é utilizar uma resource-based policies para que o usuário não perca as permissões de leitura do dynamodb em detrimento de ganhar o acesso ao bucket de backup do S3 na conta B.

Alguns serviços da AWS já permitem o "resource-based policy" como por exemplo: SNS, SQS, CloudWatch Logs, API Gateway e etc. Por outro lado, alguns serviços como Kinesis stream, ECS task, System Manager Run Commands e etc ainda não permitem essa opção, sendo assim, é necessário criar iam roles para acessar esses serviços;

**Exemplo:** Suponha que você tenha configurado o EventBrigde para executar algum tipo de regra em algum recurso. Você vai precisar saber se o recurso alvo (s3, sns, kineses e etc) aceita ou não resource based policy, pois isso vai decidir se o EventBridge vai precisar assumir uma role ou se ele vai apenas utilizar resouce based policy para executar a regra no recurso alvo.

#### IAM Permission Boundaries (Limite de permissões do IAM)
 Com essa funcionalidade você consegue impor limites de permissões para users ou roles do IAM.

 ** Exemplo:** Vamos supor que um usuário de uma conta AWS está com permissão de admin, porém foi configurado para ele um limite de permissão (permission boundary) dando acesso apenas ao S3 (através de uma policy). Esse usuário, só vai conseguir acessar o S3, pois suas permissões foram limitadas.

Você pode utilizar o IAM Permission boundaries junto com a SCP (Service Control Policies) do AWS Organizations.

![Alt text](exemplo_IAM_permission_boundaries_scp.png)

Alguns casos de uso:

- Permitir que developers consigam liberar e gerenciar suas próprias permissões, ao mesmo tempo que garanta que eles não vão conseguir escalar suas próprias permissões (se tornarem admin);
- Restringir um usuário especifico ao invés de aplicar essa restrição para toda uma conta através do uso do SCP;
- Delegar responsabilidades para usuários comuns (sem permissão de admin) dentro do limite de suas permissões, por exemplo criar novos usuários IAM;

Abaixo temos um diagrama que mostra a lógica de liberação de acesso do IAM Policy
![Alt text](exemplo_IAM_evalution_logic.png)

**Exemplo:**
O que essa policy faz? 
![Alt text](exemplo_IAM_policy_3.png)
1 - É possível executar a criação de um tópico sqs (sqs:CreateQueue)? 
R: Não, pois temos uma negativa explicita para qqlr ação do sqs (sqs:*)
2 - É possível fazer uma deleção de tópico (sqs:DeleteQueue)?
R: Apesar do conflito do allow e do deny, não seria possível deletar um tópico um tópico. Segundo a imagem que mostra a lógica de liberação de acesso do IAM Policy, sempre que tiver um deny explicito, o acesso será bloqueado;
3 - É possível ver as instâncias do ec2 (ec2:DescribeInstances)?
R: Segunda a lógica do IAM Policy, por não ter uma permissão explicita de acesso, não é possível executar essa ação, pois implicitamente, essa ação já negada por padrão (principio do menor acesso)

## AWS IAM Identity Center
Ele é o sucessor do AWS Single Sign-On, e com ele você consegue ter apenas um login para todas as suas contas AWS que ficam dentro de uma AWS Organization. Além disso, é possível conectar com outras cloud applications (salesforce, box, microsoft 365, e etc) ou até mesmo em qqlr outra aplicação, desde que seja realizada uma integração através do SAML2.0. Além disso, é possível também acessar instancias EC2 Windows;

O IAM Identity Center também permite que você escolha qual provedor de identidade (identity provider) será utilizado. Esse identity provider serve para determinar na onde ficará salvo os dados do usuário para o login. Uma opção viável seria utilizar uma funçao do próprio IAM Identity Center chamada identity store. Além dela, é possível utilizar outros identity providers como por exemplo: Active Directory (AD), OneLogin, Okta e etc.

**Exemplo:**
![Alt text](exemplo_Identity_center_1.png)

Na imagem acima é possível entender como o Single Sign-On do IAM Identity Center funciona. Primeiro seria necessário acessar o Identity Center, através do browser, e realizar o login. O Identity Center vai consultar o identity provider para verificar e validar esse usuário em uma base (na imagem, fica claro que é possível usar o identity store ou um provedor externo como o Active Directory). Após esse processo, o Identity Center vai conseguir fornecer acesso à contas AWS que estejam com o AWS Organization configuradas, Aplicações Cloud (Salesforce, dropbox e etc) e até mesmo aplicações customizadas (necessário integração atravé sdo SAML2.0). É claro que nem todos os usuário vão conseguir ter acesso a todas aplicações ou contas, você pode controlar as permissões desse usuário através do IAM Identity Center a partir do Permission Sets (Conjunto de permissões).

**Exemplo:**
Na figura abaixo temos um exemplo de funcionamento das Permission Set. Criamos 2 usuários e adicionamos em um grupo "developers"e a partir disso, foi criada uma permission set que vai fornecer aos usuários desse grupo acesso total às contas aws que estão dentro da OU "Development". Além disso, foi gerada uma outra permission set que vai conceder acesso apenas de leitura para os usuários do grupo "developers" em relação as contas aws que estão dentro da OU "Production"
![Alt text](exemplo_Identity_center_2.png)

**Porém, como essas permission sets funcionam?**
 Basicamente cada permission set é um conjunto de uma ou mais IAM Policies que vão ser designadas para os usuários ou grupos que acessarem a conta AWS.
 A cada vez que um usuário loga pelo Identity Center, ele vai estar assumindo uma IAM Role que será criada a partir das policies que são definidas nas permission sets. Um problema que o permission set consegue resolver, é a centralização do gerenciamento de acessos em multiplas contas aws dentro da AWS Organization. A imagem abaixo exemplifica bem um cenário no qual um grupo de database admins precisam ter acesso apenas ao RDS e ao Aurora de multiplas contas AWS que estão em OU diferentes dentro uma AWS Organization. Nesse caso, foi necessário apenas criar uma permission set a partir do Identity Center e designar ela para o grupo de database admins, assim toda vez que algum usuário realizar o login, ele vai estar assumindo um role que vai garantir a ele as permissões necessárias para o RDS e Aurora. No exemplo foi utilizado o RDS e o Aurora, mas o mesmo se vale para aplicações externas (Salesforce, aplicações com SALM2.0 e etc);

 ![Alt text](exemplo_Identity_center_3.png) 

 Por fim, com o IAM Identity Center, é possível ter um controle fino e granular de permissões. Além do permissoin sets, você consegue também controlar permissões através de tags que podem ser atreladas à um usuário do IAM Identity Center, esse recurso é o Attribute-Based Access Control (ABAC). E a partir dessas tags, é possível fornecer acesso à determinados serviços ou recursos da AWS. E o mais legal disso tudo é que com o ABAC, você não vai precisar modificar uma permissão, caso você precise trocar algum acesso de um usuário, ao invés disso, basta editar o valor da tag. Assim, é possível definir uma permissão apenas uma vez, e depois apenas modificar as tags/atributos do usuário do IAM Identity Center. 

## AWS Directory Services

Antes de falar sobre o AWS Directory Service em si, é necessário antes explicar o que é o Microsoft AD.
**Microsoft AD** é um sistema largamente utilizado em estrutura on premises, e ele serve como um gerenciador central de segurança, conseguindo criar contas, designar permissões, e ele consegue gerenciar usuário, contas, grupos de segurança, arquivos compartilhados, impressoras e etc.
Um feature básica de funcionamento, é permitir a validação de login para computadores que estão dentro de uma mesma rede. Por exemplo, na imagem abaixo, temos um usuário john com um password. Esse usuário foi criado no controlador de dominios do microsoft AD, e a partir disso, é possível realizar o login desse usuário em qqlr outro computador que estiver dentro da rede on premise. 

![Alt text](exemplo_microsoft_ad_1.png)

Voltando para o AWS Directory Service (AWS AD), a AWS oferece 3 tipos diferente de uso para esse serviço, sendo eles:
- AWS Managed Microsoft AD: Nesse caso, você consegue criar seu próprio AD na AWS, e a partir disso, estabelecer uma relação de confiança entre o AWS AD e o on-premise AD. A vantagem dessa abordagem é que é possível gerenciar usuários tanto no serviço on-premise quanto no serviço da aws. Caso o usuário realize um login em um ec2 windows na aws, é possível buscar o login desse usuário no serviço AWS AD e no servidor on-premise e vice-versa; Com essa opção é possível configurar também MFA;
Exemplo: 
![Alt text](exemplo_microsoft_ad_2.png)
- AD Connector: Já nessa abordagem, não temos o AWS AD para criar o seu AD na cloud. Nesse caso, temos um proxy (gateway) que vai redirecionar toda parte de gerenciamento de usuários para o on-premise AD; Com essa opção é possível configurar também MFA;
Exemplo:
![Alt text](exemplo_microsoft_ad_3.png)
- Simple AD: E por fim, temos somente o AWS AD, que vai permitir que você crie e gerencie seus usuários na AWS, sem a necessidade de ter um servidor on-premise do microsoft AD;

**Integração Identity Center e AWS AD**
É possível conectar o AWS AD com o IAM Identity Center, e existem algumas formas de se fazre isso, dependendo do tipo de AWS AD que está sendo utilizado.
O primeiro cenário, seria uma conexão direta entre o AWS AD e o IAM Identity Center, uma vez que são dois serviços AWS, a integração é facilitada.
O segundo cenário seria uma conexão na qual o AWS AD fique no meio da conexão entre o Identity Center e o on-premise AD, conforme mostrado na imagem abaixo:
![Alt text](exemplo_microsoft_ad_4.png)

Nesse cenário, podemos ter 2 subcasos, sendo que no primeiro, seria necessário estabelecer a relação de confiança de duas vias entre o on-premise AD e o AWS Managed Microsoft AD. Já no segundo subcaso, ao invés de estabelecer essa relação, seria necessário apenas configurar o AD Connector para atuar como proxy, repassando todo gerenciamento de usuários para o on-premise AD.

## AWS Control Tower
É um serviço da AWS que permite você configurar e governar de modo seguro e fácil os ambientes de várias contas AWS. O Control Tower usa o AWS Organization para criar contas aws e com ele você tem alguns beneficios:
- Automatizar a configuração do ambiente da conta aws com apenas alguns clicks;
- Automatizar gerenciamento de políticas através de guardrails
- Detectar violações de políticas e remedia-las;
- Monitorar atividades por meio de um dashboard interativo;

Com o AWS Control Tower, é possível utilizar dois tipos de guard rails, sendo eles:
- Guardrail preventivo: Nesse guardrail, vc define algumas restrições para todas as contas AWS de uma vez só, como por exemplo, restringir as contas AWS a utilizares somente algumas regiões da AWS. Isso pode ser feito através do SCP do Aws Organizations;
- Guardrail detectivo: Nesse guardrail, você consegue identificar recursos que estão fora de algum complaince ou regra definida, e isso é feito através do AWS Config. Um exemplo seria identificar recursos aws que não estão tageados e a partir disso tomar alguma ação como notificar algum adiministrador ou executar alguma lambda para adicoinar tags ao recurso;

## Amazon EC2 (Elastic Compute Cloud)
Serviço de IaaS (Infrastructure as a Service) da aws e ele permite que você consiga:
- Alugar máquinas virtuais (EC2);
- Armazenar dados em drives virtuais (EBS);
- Distribuir a carga entre as máquinas virtuais (ELB);
- Escalar seus serviços com autoscaling group (ASG);
- Rodar scripts de inicialização (bootstrapping) conhecidos como "EC2 user data script" para automatizar tarefas de inicialização

#### Tipos de instancias EC2
A AWS oferece diferentes tipos de instancias que são otimizadas para casos de usos diferentes (armazenamento, memoria, computação e etc), e ela classifica essas instâncias usando o seguinte padrão:

**Exemplo:** m5.2xlarge
- m: representa a classe da instancia;
- 5: representa a geração;
- 2xlarge: representa o tamanho da instancia dentro de uma classe;

A AWS oferece diversos tipos de instâncias, sendo alguns deles:

- Uso generico: Oferecem um balanço entre processamento, rede e memória, tendo um bom match com servidores web ou repositórios de código. Alguns classes conhecidas são: T2, M4, T3, e etc;

- Processamento otimizado: Oferece alta performance para tarefas que exigem bastante processamento, como processos batches, machine learning, gaming servers, web servers que exigem alta performances e etc. Algumas classes conhecidas: C4, C5, e etc;

- Memória otimizada: Alta performance para tarefas que trabalham com grande quantidade de dados em memória, como por exemplo, base de dados não relacional de alta performance, caches em memoria e etc. Algumas classes são R4, R5 e etc;

- Armazenamento otimizado: Perfeitas para tarefas que exigem uma grande quantidade de leitura e escrita de arquivos com grande quantidade de dados, alguns exemplos são: aplicações de Data warehouse, sistemas distruidos de arquivos e etc. Algumas classes são: D2, D3, e etc;

