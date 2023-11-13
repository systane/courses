package main

import "fmt"

func main() {
	//podemos usar o comando "go vet comandos.go" para verificar possíveis smellcode
	fmt.Printf("Outro programa em %s!\n", "Go")

	/*
		podemos criar o executavel desse arquivo, através do comando "go build comandos.go"
		e a partir disso, executar o arquivo "./comandos"
	*/
}
