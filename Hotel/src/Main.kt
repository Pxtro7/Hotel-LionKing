package Hotel

import kotlin.system.exitProcess

// Variáveis Globais
const val NOME_HOTEL = "Lion King"
var nomeUsuario: String = ""

// Módulo 1: Variável Global para o estado dos 20 quartos
val quartos = MutableList(20) { "Livre" }

fun main() {
    saudacao()
}


// Função: Gerencia o login do usuário, validando a senha 2678.
fun saudacao() {
    println("Bem-vindo ao $NOME_HOTEL!")

    print("Qual seu nome? ")
    nomeUsuario = readln()

    val senhaCorreta = 2678
    var senhaDigitada: Int? = null

    while (senhaDigitada != senhaCorreta) {
        print("Informe a senha: ")
        senhaDigitada = readln().toIntOrNull()

        if (senhaDigitada == senhaCorreta) {
            println("Bem-vindo ao Hotel $NOME_HOTEL, $nomeUsuario. É um imenso prazer ter você por aqui!")
            inicio()
        } else {
            println("Senha incorreta!")
        }
    }
}

// Função: Exibe o menu principal do hotel e gerencia a navegação.
fun inicio() {
    while (true) {
        println("""
            --- MENU PRINCIPAL ---
            Selecione uma opção:
            1. Reserva de Quartos 
            2. Cálculo de Tarifas 
            3. Cadastro de Hóspedes 
            4. Abastecimento de Automóveis
            5. Reservar para evento
            6. Orçamentos para manutenção de Ar condicionado
            7. Sair do Hotel
            ----------------------
            Escolha:
            """)

        val escolha = readln().toIntOrNull()

        when (escolha) {
            1 -> reservaDeQuartos()
            2 -> calculoDeTarifas()
            3 -> cadastrarHospedes()
            4 -> AbastecimentoDeAutomoveis()
            5 -> localEvento()
            6 -> calcularOrcamentos()
            7 -> sairDoHotel()
            else -> erro()
        }
    }
}


// Função: Gerencia o processo de reserva de um quarto, incluindo validação de diárias e checagem de ocupação.
fun reservaDeQuartos() {
    println("\n--- 1. Quantos quartos são? (Reserva de Quartos) ---")

    var valorDiaria: Double
    do {
        print("Qual o valor padrão da diária? ")
        valorDiaria = readln().toDoubleOrNull() ?: -1.0
        if (valorDiaria < 0) {
            println("Valor inválido, $nomeUsuario.")
        }
    } while (valorDiaria < 0)

    var quantDiarias: Int
    do {
        print("Quantas diárias serão necessárias? ")
        quantDiarias = readln().toIntOrNull() ?: -1
        if (quantDiarias < 1 || quantDiarias > 30) {
            println("Valor inválido, $nomeUsuario.")
        }
    } while (quantDiarias < 1 || quantDiarias > 30)

    val total = valorDiaria * quantDiarias
    println("O valor de $quantDiarias dias de hospedagem é de R$${"%.2f".format(total)}")

    print("Qual o nome do hóspede? ")
    val nomeHospedeReserva = readln()

    var quartoEscolhido: Int
    while (true) {
        print("Qual o quarto para reserva? (1 - 20)? ")
        quartoEscolhido = readln().toIntOrNull() ?: 0

        if (quartoEscolhido !in 1..20) {
            println("Número de quarto inválido. Escolha entre 1 e 20.")
            continue
        }

        if (quartos[quartoEscolhido - 1] == "Livre") {
            println("Quarto Livre.")
            break
        } else {
            println("Quarto está ocupado. Escolha outro.")
            exibirStatusQuartos()
        }
    }

    println("$nomeUsuario, você confirma a hospedagem para $nomeHospedeReserva por $quantDiarias dias para o quarto $quartoEscolhido por R$${"%.2f".format(total)}? S/N")
    val confirmacao = readln().uppercase()

    if (confirmacao == "S") {
        quartos[quartoEscolhido - 1] = nomeHospedeReserva
        println("$nomeUsuario, reserva efetuada para $nomeHospedeReserva.")
    } else {
        println("Reserva cancelada. Voltando ao menu inicial.")
        return
    }

    exibirStatusQuartos()
}

// Função: Exibe o status atual de ocupação de todos os 20 quartos.
fun exibirStatusQuartos() {
    print("Lista de quartos e suas ocupações: ")
    quartos.forEachIndexed { index, ocupante ->
        val status = if (ocupante == "Livre") "livre" else "ocupado"
        print("${index + 1}- $status; ")
    }
    println("\n")
}

// Função: Placeholder para o Módulo 5 (Abastecimento de Automóveis).
fun AbastecimentoDeAutomoveis() {
        println("ONDE ABASTECER?")
        println("")
    // Função: Compara os preços de álcool e gasolina em dois postos para determinar a opção mais econômica para abastecer 42 litros.
        val capacidadeTanque = 42.0

        // Posto Wayne Oil
        print("Qual o valor do álcool no posto Wayne Oil? ")
        val alcoolWayne = readln().toDoubleOrNull() ?: 0.0
        print("Qual o valor da gasolina no posto Wayne Oil? ")
        val gasolinaWayne = readln().toDoubleOrNull() ?: 0.0

        // Posto Stark Petrol
        print("Qual o valor do álcool no posto Stark Petrol? ")
        val alcoolStark = readln().toDoubleOrNull() ?: 0.0
        print("Qual o valor da gasolina no posto Stark Petrol? ")
        val gasolinaStark = readln().toDoubleOrNull() ?: 0.0

        // CÁLCULO DE VANTAGEM (ÁLCOOL VS GASOLINA)
        // Regra: Álcool é vantajoso se o preço for menor que 70% (100% - 30%) do preço da gasolina.

        // Posto Wayne Oil
        val melhorCombustivelWayne = if (alcoolWayne <= gasolinaWayne * 0.70) "álcool" else "gasolina"
        val precoWayne = if (melhorCombustivelWayne == "álcool") alcoolWayne else gasolinaWayne
        val custoWayne = precoWayne * capacidadeTanque

        // Posto Stark Petrol
        val melhorCombustivelStark = if (alcoolStark <= gasolinaStark * 0.70) "álcool" else "gasolina"
        val precoStark = if (melhorCombustivelStark == "álcool") alcoolStark else gasolinaStark
        val custoStark = precoStark * capacidadeTanque


        var postoVencedor = ""
        var combustivelVencedor = ""
        var custoMenor: Double

        if (custoWayne < custoStark) {
            postoVencedor = "Wayne Oil"
            combustivelVencedor = melhorCombustivelWayne
            custoMenor = custoWayne
        } else if (custoStark < custoWayne) {
            postoVencedor = "Stark Petrol"
            combustivelVencedor = melhorCombustivelStark
            custoMenor = custoStark
        } else {
            // Em caso de empate, escolhemos o primeiro (Wayne Oil)
            postoVencedor = "Wayne Oil"
            combustivelVencedor = melhorCombustivelWayne
            custoMenor = custoWayne
        }

        println("\n$nomeUsuario, é mais barato abastecer com $combustivelVencedor no posto $postoVencedor.")
        println("O custo total será de R$ ${"%.2f".format(custoMenor)}. para encher o tanque")
    }


// Função: Gerencia a saída do sistema, encerrando o programa se confirmado.
fun sairDoHotel() {
    println("Você deseja sair? (S/N)")
    val confirma = readln().uppercase()
    if (confirma == "S") {
        println("Muito obrigado e até logo, $nomeUsuario.")
        exitProcess(0)
    } else {
        println("Voltando ao menu principal.")
    }
}

// Função: Exibe mensagem de erro para seleção inválida no menu principal.
fun erro() {
    println("Por favor, informe um número entre 1 e 5.")
}

// Função: Gerencia o processo de orçamentos de manutenção de ar-condicionado, calculando e comparando propostas de múltiplas empresas.
fun calcularOrcamentos() {
    println("\n--- 6. Orçamento para manutenção de ar condicionado ---")

    // Lista para armazenar o nome da empresa e o valor orçado
    val orcamentosFinais = mutableListOf<Pair<String, Double>>()
    var continuar = true

    while (continuar) {
        print("Qual o nome da empresa? ")
        val nomeEmpresa = readln()

        print("Qual o valor por aparelho? ")
        val valorAparelho = readln().toDoubleOrNull() ?: 0.0

        print("Qual a quantidade de aparelhos? ")
        val quantidadeAparelhos = readln().toIntOrNull() ?: 0

        print("Qual a porcentagem de desconto? ")
        val porcentagemDesconto = readln().toDoubleOrNull() ?: 0.0

        print("Qual o número mínimo de aparelhos para conseguir o desconto? ")
        val minimoParaDesconto = readln().toIntOrNull() ?: 0

        val custoFinal = orcarServico(valorAparelho, quantidadeAparelhos, porcentagemDesconto, minimoParaDesconto, nomeEmpresa)

        // Armazena o resultado na lista
        orcamentosFinais.add(Pair(nomeEmpresa, custoFinal))

        print("Deseja informar novos dados, $nomeUsuario? (S/N) ")
        val resposta = readln().uppercase()
        if (resposta != "S") {
            continuar = false
        }
    }

    if (orcamentosFinais.isEmpty()) {
        println("Nenhum orçamento foi inserido.")
        return
    }

    // .minByOrNull é um atalho que encontra o par com o menor segundo elemento (o custo)
    val menorOrcamento = orcamentosFinais.minByOrNull { it.second }

    // O Elvis operator (?:) garante que o programa não quebre se a lista for vazia
    val empresaVencedora = menorOrcamento?.first ?: "N/A"
    val valorVencedor = menorOrcamento?.second ?: 0.0

    println("\nO orçamento de menor valor é o da empresa $empresaVencedora por R$ ${"%.2f".format(valorVencedor)}.")
}

// Função: Calcula o custo total do serviço de manutenção, aplicando o desconto se a quantidade mínima for atingida.
fun orcarServico(valorAparelho: Double, quantidadeAparelhos: Int, porcentagemDesconto: Double, minimoParaDesconto: Int, nomeEmpresa: String): Double {

    var custoTotal = valorAparelho * quantidadeAparelhos

    if (quantidadeAparelhos >= minimoParaDesconto) {
        // Aplica o desconto. Ex: 12% = 0.12
        val fatorDesconto = porcentagemDesconto / 100.0
        custoTotal -= custoTotal * fatorDesconto
    }

    println("O serviço de $nomeEmpresa custará R$ ${"%.2f".format(custoTotal)}")
    return custoTotal
}