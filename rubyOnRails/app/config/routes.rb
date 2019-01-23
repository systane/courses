Rails.application.routes.draw do
  resources :departamentos
  #get "produtos" => "produtos#index"
  #Obs: produtos#index --> Caso exista um controller com o método 'index' criado, ele que será acessado, 
  #caso contrário o '#index' indica que quero acessar o index(arquivo html) dos produtos.

  root "produtos#index" #
  resources :produtos, only: [:new, :create, :destroy, :edit, :update]
  get "produtos/busca" => "produtos#busca", as: :busca_produto

  # get "produtos/new" => "produtos#new"
  # post "produtos" => "produtos#create"
  # delete "produtos/:id" => "produtos#destroy", as: :produto
end
