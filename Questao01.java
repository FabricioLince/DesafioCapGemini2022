
public class Questao01 {
  
  public static void main() {
    // para ler a entrada do usuario
    java.util.Scanner in = new java.util.Scanner(System.in);

    System.out.println("Digite altura da escada:");
    System.out.print("n = ");
    try {
      int n = in.nextInt();
      escada(n);
      
    } catch (java.util.InputMismatchException e) {
      // Scanner não conseguiu detectar um numero na entrada
      System.out.println("Você deve digitar um número.");
    }
    
  }


  // dois testes simples de impressão
  public static void testes() {
    // imprime uma escada de altura = 5
    escada(5);
    
    // Como não foi especificado o comportamente para n <= 0
    // o comportamente padrão é imprimir uma string vazia
    System.out.println(escadaString(0).length() == 0);
  }

  
  // Como se trata de um algoritmo que não precisa e nem deve guardar um estado
  // um metodo estático é preferencial
  
  // n = numero de linhas(degraus) na escada
  public static void escada(int n) {
    // imprime na saída padrão o resultado do algoritmo
    // não estou usando println pois a ultima linha já vem com '\n' no final
    System.out.print(escadaString(n));
  }

  // Uma função separada que executa o algoritmo e coloca o resultado em uma String
  // caso se torne necessário usar o resultado em algum outro lugar que não seja na saída padrão (System.out)
  // n = numero de linhas(degraus) na escada
  public static String escadaString(int n) {
    String resultado = "";
    for (int line = 0; line < n; line++) {
      // repete o código a seguir para cada linha 
      
      // na primeira linha: qtde de espacos = n-1; qtde de astericos = 1
      // em todas as linhas: qtde de espaços + qtde de asteriscos = qtde de linhas (n)
      // a qtde de espaços diminui a medida que a qtde de asteriscos aumenta
      
      int espacos = n - (line+1);

      int asteriscos = line+1;
      
      // concatena a qtde calculada de espacos para a linha
      resultado += " ".repeat(espacos); 

      // concatena a qtde calculada de asteriscos para a linha
      resultado += "*".repeat(asteriscos);

      // fim do degrau
      resultado += "\n"; 
    }
    
    return resultado;
  }

}