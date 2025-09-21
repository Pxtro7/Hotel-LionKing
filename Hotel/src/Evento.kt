package Hotel

import kotlin.math.ceil

var convidado = 0
var dia = ""
var horaEvento = 0
var tempo = 0
var nomeEmpresa = ""

fun localEvento(){
    println("Informe a quantidade de convidados para o evento: ")
    convidado = readln().toInt()

    if (convidado in 0..220){
        val cadeirasAdicionais = convidado - 150
        println("O indicado é que seja feito o evento no Auditorio Laranja e será necessário $cadeirasAdicionais cadeiras adicionais")

    }else if (convidado in 220..350){
        println("O indicado é que seja feito o evento no Auditorio Colorado")
    }else if (convidado > 350){
        println("Quantidade de convidados superior à capacidade máxima")
    }else{
        println("Digite uma quantidade de convidados valida!!")
    }
    println("Agora veremos a agenda do evento...")
    horarioEvento()
}

fun horarioEvento(){

    println("HORARIO DE FUNCIONAMENTO PARA EVENTOS")
    println("")
    println("Seg à Sex das 7h às 23h e Sab e Dom 7h às 15h")

    println("Digite o dia da semana do evento: ")
    dia = readln().lowercase()
    println("Digite o horario do evento: (APENAS O ALGARISMO)")
    horaEvento = readln().toInt()
    println("Digite em horas a duração do evento: ")
    tempo = readln().toInt()
    if (dia == "segunda" && horaEvento in 7..23){
        println("Auditorio disponivel")

    } else if (dia == "terça" && horaEvento in 7..23){
        println("Auditorio disponivel")

    }else if (dia == "quarta" && horaEvento in 7..23){
        println("Auditorio disponivel")

    }else if (dia == "quinta" && horaEvento in 7..23){
        println("Auditorio disponivel")

    }else if (dia == "sexta" && horaEvento in 7..23){
        println("Auditorio disponivel")

    }else if (dia == "sabado" && horaEvento in 7..15){
        println("Auditorio disponivel")

    }else if (dia == "domingo" && horaEvento in 7..15){
        println("Auditorio disponivel")

    }else{
        println("Dia invalido ou horario indisponivel, verifique outro dia e horario")
        horarioEvento()
    }

    println("Qual o nome da empresa?")
    nomeEmpresa = readln()
    println("Auditório reservado para $nomeEmpresa. $dia às $horaEvento.\"\n")
    calcularGarcons(convidado, tempo)
    calcularBuffet(convidado)
}

fun calcularGarcons(numeroConvidados: Int, duracaoHoras: Int): Double {
    val custoGarcomHora = 10.50

    val garconsPorConvidado = ceil(numeroConvidados.toDouble() / 12.0).toInt()
    val garconsPorDuracao = duracaoHoras / 2

    val totalGarcons = garconsPorConvidado + garconsPorDuracao
    val custoGarcons = totalGarcons * duracaoHoras * custoGarcomHora

    println("São necessários $totalGarcons garçons.")
    println("Custo: R$ ${"%.2f".format(custoGarcons)}")

    return custoGarcons
}

// Função: Calcula a quantidade de itens do buffet (água, café, salgados) e o custo total.
fun calcularBuffet(numeroConvidados: Int): Double {
    val custoCafeLitro = 0.80
    val custoAguaLitro = 0.40
    val custoSalgadoCento = 34.00

    val cafeLitros = numeroConvidados * 0.2
    val aguaLitros = numeroConvidados * 0.5
    val salgados = numeroConvidados * 7

    val custoCafe = cafeLitros * custoCafeLitro
    val custoAgua = aguaLitros * custoAguaLitro
    val custoSalgados = (salgados / 100.0) * custoSalgadoCento

    val custoTotalBuffet = custoCafe + custoAgua + custoSalgados

    println("O evento precisará de ${"%.1f".format(cafeLitros)} litros de café, ${"%.1f".format(aguaLitros)} litros de água, ${salgados.toInt()} salgados.")

    return custoTotalBuffet
}