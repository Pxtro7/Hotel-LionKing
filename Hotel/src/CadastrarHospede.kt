package Hotel

// Variável Global: Lista de Hóspedes
val listaHospedes = mutableListOf(
    "Carlos Villagran", "Maria Antonieta de las Nieves", "Roberto Gómez Bolaños", "Florinda Meza", "Ramón Valdés", "Rubén Aguirre", "Angelines Fernández", "Edgar Vivar", "Horácio Gómez Bolaños", "Raúl Padilla"
)


// Função: Calcula o valor total da diária para uma família, aplicando descontos por idade, e cadastra os hóspedes.
fun calculoDeTarifas() {
    println("\n--- 2. Cálculo de Tarifas e Cadastro de Hóspedes ---")

    var valorDiaria: Double
    do {
        print("Qual o valor padrão da diária? ")
        valorDiaria = readln().toDoubleOrNull() ?: -1.0
        if (valorDiaria <= 0) {
            println("Valor da diária inválido. Por favor, insira um valor positivo.")
        }
    } while (valorDiaria <= 0)

    var totalHospedagem = 0.0
    var gratuidades = 0
    var meiasHospedagens = 0
    var continuar = true

    while (continuar) {
        print("\nQual o nome do Hóspede? (Digite 'PARE' para encerrar): ")
        val nomeHospede = readln().trim()

        if (nomeHospede.equals("PARE", ignoreCase = true)) {
            continuar = false
            continue
        }

        listaHospedes.add(nomeHospede)

        print("Qual a idade do Hóspede? ")
        val idade = readln().toIntOrNull()

        if (idade == null || idade < 0) {
            println("Idade inválida. Hóspede cadastrado sem tarifa calculada. Tente novamente.")
            continue
        }

        var valorAPagar = valorDiaria
        var mensagemDesconto = ""

        if (idade < 6) {
            valorAPagar = 0.0
            gratuidades++
            mensagemDesconto = "possui gratuidade"
        } else if (idade > 60) {
            // /= 2.0 é um atalho para 'valorAPagar = valorAPagar / 2.0'
            valorAPagar /= 2.0
            meiasHospedagens++
            mensagemDesconto = "paga meia"
        }

        // += valorAPagar é um atalho para 'totalHospedagem = totalHospedagem + valorAPagar'
        totalHospedagem += valorAPagar

        println("${nomeHospede} cadastrada(o) com sucesso. ${nomeHospede} $mensagemDesconto")
    }

    println("\nO valor da diária é: R$${"%.2f".format(totalHospedagem)}; ${gratuidades} gratuidade(s); ${meiasHospedagens} meia(s)")
}


// Função: Gerencia o submenu de cadastro, pesquisa e listagem de hóspedes (Módulo 3).
fun cadastrarHospedes() {
    while (true) {
        println("""
            --- 3. (Cadastro/Pesquisa) ---
            Selecione uma opção:
            1. Cadastrar
            2. Pesquisar
            3. Listar
            4. Sair (Voltar ao Menu Principal)
            ------------------------------------------------
            """)

        val escolha = readln().toIntOrNull()

        when (escolha) {
            1 -> cadastrarHospede()
            2 -> pesquisarHospede()
            3 -> listarHospedes()
            4 -> break
            else -> erroCadastroDeHospedes()
        }
    }
}

// Função: Adiciona um novo hóspede à lista, respeitando o limite máximo de 15.
fun cadastrarHospede() {
    val maxCadastros = 15
    if (listaHospedes.size >= maxCadastros) {
        println("Máximo de cadastros ($maxCadastros) atingido.")
        return
    }

    println("Cadastro de Hóspedes.\nPor favor, informe o nome da Hóspede:")
    val novoHospede = readln()

    listaHospedes.add(novoHospede)

    println("Hóspede '$novoHospede' foi cadastrada(o) com sucesso!")
}

// Função: Pesquisa um hóspede na lista por nome exato (case sensitive).
fun pesquisarHospede() {
    println("Pesquisa de Hóspedes.\nPor favor, informe o nome do Hóspede:")
    val nomeHospede = readln()

    // .any() é um atalho que verifica se existe pelo menos um elemento na lista que satisfaça a condição (it.equals...).
    val encontrado = listaHospedes.any { it.equals(nomeHospede, ignoreCase = false) }

    if (encontrado) {
        println("Hóspede $nomeHospede foi encontrado!")
    } else {
        println("Hóspede $nomeHospede não foi encontrado!")
    }
}

// Função: Exibe todos os hóspedes cadastrados, um por linha.
fun listarHospedes() {
    println("\n--- Hóspedes Atualmente Cadastrados ---")
    if (listaHospedes.isEmpty()) {
        println("A lista está vazia.")
    } else {
        // .forEach é um atalho para iterar sobre a lista.
        listaHospedes.forEach { println(it) }
    }
    println("----------------------------------------\n")
}

// Função: Exibe mensagem de erro para seleção inválida no submenu de cadastro de hóspedes.
fun erroCadastroDeHospedes() {
    println("Por favor, informe um número entre 1 e 4.")
}