import java.util.InputMismatchException;
import java.util.Scanner;

// Definição da interface para operações aritméticas
interface Operacao {
    double calcular(double... numeros);
}

// Implementações concretas das operações
class Soma implements Operacao {
    @Override
    public double calcular(double... numeros) {
        double resultado = 0;
        for (double numero : numeros) {
            resultado += numero;
        }
        return resultado;
    }
}

class Subtracao implements Operacao {
    @Override
    public double calcular(double... numeros) {
        if (numeros.length < 2) {
            throw new IllegalArgumentException("Pelo menos dois números são necessários para a subtração.");
        }
        double resultado = numeros[0];
        for (int i = 1; i < numeros.length; i++) {
            resultado -= numeros[i];
        }
        return resultado;
    }
}

class Multiplicacao implements Operacao {
    @Override
    public double calcular(double... numeros) {
        if (numeros.length < 2) {
            throw new IllegalArgumentException("Pelo menos dois números são necessários para a multiplicação.");
        }
        double resultado = 1;
        for (double numero : numeros) {
            resultado *= numero;
        }
        return resultado;
    }
}

class Divisao implements Operacao {
    @Override
    public double calcular(double... numeros) {
        if (numeros.length != 2) {
            throw new IllegalArgumentException("A divisão deve ser feita com exatamente dois números.");
        }
        if (numeros[1] == 0) {
            throw new IllegalArgumentException("Não é possível dividir por zero.");
        }
        return numeros[0] / numeros[1];
    }
}

// Classe principal da calculadora
public class Calculadora {
    public double executarOperacao(Operacao operacao, double... numeros) {
        return operacao.calcular(numeros);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculadora calculadora = new Calculadora();

        while (true) {
            double[] numeros = new double[2];
            boolean numerosValidos = false;
            while (!numerosValidos) {
                try {
                    for (int i = 0; i < 2; i++) {
                        System.out.print("Digite o número " + (i + 1) + ": ");
                        while (!scanner.hasNextDouble()) {
                            System.out.println("Entrada inválida. Digite um valor numérico.");
                            scanner.next(); // Descartar entrada inválida
                        }
                        numeros[i] = scanner.nextDouble();
                    }
                    numerosValidos = true;
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Digite apenas números.");
                    scanner.nextLine(); // Limpar o buffer do scanner
                }
            }

            System.out.println("Escolha a operação: ");
            System.out.println("1. Soma");
            System.out.println("2. Subtração");
            System.out.println("3. Multiplicação");
            System.out.println("4. Divisão");
            int escolha = scanner.nextInt();

            Operacao operacao = null;
            switch (escolha) {
                case 1:
                    operacao = new Soma();
                    break;
                case 2:
                    operacao = new Subtracao();
                    break;
                case 3:
                    operacao = new Multiplicacao();
                    break;
                case 4:
                    operacao = new Divisao();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    scanner.nextLine(); // Limpar o buffer do scanner
                    continue; // Voltar ao início do loop
            }

            if (operacao != null) {
                try {
                    double resultado = calculadora.executarOperacao(operacao, numeros);
                    System.out.println("Resultado: " + resultado);
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            }

            boolean escolhaContinuar = false;
            while (!escolhaContinuar) {
                System.out.println("Deseja continuar fazendo operações? (Digite 'sim' para continuar)");
                scanner.nextLine(); // Consumir a quebra de linha pendente
                String resposta = scanner.next();
                if (resposta.equalsIgnoreCase("sim")) {
                    escolhaContinuar = true;
                } else {
                    escolhaContinuar = true;
                    break;
                }
            }
        }
        // scanner.close(); // Você pode optar por não fechar o scanner para evitar problemas de fechamento de fluxo de entrada.
    }
}
