Spring cloud config server

Spring Cloud Config Server suporta vários tipos de configurações. Você pode definir escopos e/ou spring profiles de configurações para os seus clientes.

No arquivo {application} será colocada configurações genérica para todos os clientes. Já no arquivo {application}-{profile} os arquivos nomeados sob esse padrão serão servidos apenas para os clientes com o msm profile. É possível mapear no cliente o {application} e o {profile} por meio das propriedades spring.application.name e spring.profiles.active.
Para poder agrupar os arquivos de configuração pode se usar {label} (que tbm é mapeado no cliente), sendo assim a nomeação dos arquivos de grupos seriam: {application}-{profiles}-{label}. Além disso, é possível consultar esses arquivos através de endpoints: GET /application/profile/label sendo o label opicional.