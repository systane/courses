class Produto < ApplicationRecord
    #Indica que o Produto pertence ao Departamento
    belongs_to :departamento

    #deixa como obrigatório o campo quantidade ao realizar a persistencia
    validates :quantidade, presence: true
    validates :nome, length: { minimum: 5 }
end
