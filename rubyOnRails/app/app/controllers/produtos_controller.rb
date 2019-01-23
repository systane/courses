class ProdutosController < ApplicationController
    
    before_action :set_produto, only: [:edit, :update, :destroy]

    def index
        @produtos = Produto.order(:nome).limit 10
        #O '@' serve para marcar que a variável será da instância do controller Produto,
        #caso queira acessa-la no frontend, também devo utilizar o '@'.
    end


    def new
        @produto = Produto.new
        @departamentos = Departamento.all
    end


    def busca
        @nome = params[:nome]
        @produtos = Produto.where "nome like ?", "%#{@nome}%"
    end


    def create
        #Pega o produto vindo da request e permite que todos os atributos sejam utilizados
        #produto = params.require("produto").permit!
        #produto = Produto.create produto
        
        @produto = Produto.new produto_params
        if @produto.save

            #No flash podemos colocar variaveis que duram até na proxima requisição (2 requisições de lifetime)
            #E ela tem um conceito bem parecido com o flash do NodeJs Express
            flash[:notice] = "Produto salvo com sucesso"
            redirect_to root_url
        else
            renderiza :new
        end
    end


    def destroy
        @produto.destroy
        redirect_to root_url
    end

    
    def edit
        renderiza :edit
    end

    def update
        if @produto.update produto_params
            flash[:notice] = "Atualização realizada com sucesso"
            redirect_to root_url
        else
            renderiza :edit
        end
    end

    private
    def renderiza(view)
        @departamentos = Departamento.all
        render view
    end

    def set_produto
        id = params[:id]
        @produto = Produto.find(id)
    end

    def produto_params
        #Método q restringe quais atributos serão permitidos (nome, descrição, preco e quantidade)
        #Nesse passo foi utilizado o 'symbols' (:nome, :descricao, :preco, :quantidade) ao invés de setar os parametros via string ("nome", "descricao", "preco", "quantidade")
        params.require("produto").permit :nome, :descricao, :preco, :quantidade, :departamento_id
    end
end
