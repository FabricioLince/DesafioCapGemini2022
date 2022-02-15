public class Questao02 {

  public static void main() {

    PasswordEvaluator evaluator = new PasswordEvaluator();

    String password = "Ya3";
    int errors = evaluator.evaluateStrength(password);
    // Deve retornar 3 pois é o necessário para chegar ao tamanho minimo de 6
    System.out.println(errors == 3);

    // rodar testes unitários
    unitTests();

  }

  public static void unitTests() {
    
    Tester tester = new Tester();
    
    // Deve retornar 3 pois faltam:
    // > letra maiuscula
    // > letra minuscula
    // > caracter especial
    tester.addTest("123456", 3);

    // Deve retornar 3 pois faltam:
    // > letra maiuscula
    // > letra minuscula
    // > caracter especial
    // apesar de também não possuir tamanho minimo, 
    // ao adicionar os três caracteres acima o tamanho passará do minimo
    tester.addTest("1234", 3);

    // Deve retornar 2 pois falta:
    // > digito
    // > caracter especial
    // apesar de também não possuir tamanho minimo, 
    // ao adicionar os dois caracteres acima o tamanho passará do minimo
    tester.addTest("Lince", 2);

    // Deve retornar 2 pois falta o tamanho minimo de 6 carateres
    tester.addTest("Aa@1", 2);

    // Deve retornar 1 pois falta o caracter especial
    tester.addTest("Lince123", 1);

    // Deve retornar 0 pois não falta nada
    tester.addTest("Lince@123", 0);

    // Deve retornar 1 pois falta digito
    tester.addTest("Lince@Lazuli", 1);

    // Deve retornar 0 pois não falta nada
    // mas vou informar 5 para saber se o testador vai detectar essa diferença
    // tester.addTest("Cap@Gemin1", 5);

    tester.testAll();
  }

  // Classe para guardar e rodar testes unitários
  private static class Tester {

    // Classe para representar um caso de teste
    private static class TestCase {
      public String password;
      public int expectedScore;
    }

    // lista de testes a serem feitos
    private java.util.ArrayList<TestCase> testCases = new java.util.ArrayList<TestCase>();

    private PasswordEvaluator evaluator = new PasswordEvaluator();

    // adiciona um caso de teste a lista
    // password = senha a ser testada
    // expectedScore = valor que o analisador deve retornar
    public void addTest(String password, int expectedScore) {
      TestCase testCase = new TestCase();
      testCase.password = password;
      testCase.expectedScore = expectedScore;
      testCases.add(testCase);
    }


    // metodo que testa todos os casos listados e compara os resultados obtidos com os resultados esperados
    public void testAll() {
      int fails = 0;
      for (TestCase testCase : testCases) {
        int errors = evaluator.evaluateStrength(testCase.password);
        if (errors != testCase.expectedScore) {
          System.out.println("Não passou teste!");
          System.out.println("Senha:" + testCase.password);
          System.out.println("qtde minima esperada: "+ testCase.expectedScore);
          System.out.println("qtde minima detectada: " + errors);
          fails += 1;
        }
      }
      if (fails == 0) {
        System.out.println("Passou todos os testes!");
      }
      else {
        System.out.println("Falhou em " + fails + " testes!");
      }
    }
  }

  // Classe que realiza analise e guarda resultados intermediários sobre a analise de força da senha
  // A vantagem de usar uma classe ao inves de um simples metodo é poder usar o valores da analise
  // para informar ao usuário quais foram as falhas na sua senha
  private static class PasswordEvaluator {

    // tamanho minimo para senha
    private static final int MIN_PASSWORD_LENGTH = 6;

    // string com todos caracteres especiais
    private static final String SPECIAL =  "!@#$%^&*()-+";

    // senha que foi analisada
    private String password;
    
    // diferença entre a quantidade de caracteres e a quantidade minima
    private int minChangeLen = 0;

    // flags para caracteres requeridos na senha
    private boolean hasSpecialChar = false;
    private boolean hasUpperCase = false;
    private boolean hasLowerCase = false;
    private boolean hasDigit = false;
    // qtde de mudanças de acordo com as flags acima
    private int charChanges = 0;

    // quantidade minima necessária para deixar a senha "forte"
    private int neededAmount = 0;
    public int getNeededAmount() {
      return neededAmount;
    }

    // preenche este objeto com os valores associados a esta analise
    // retorna a qtde minima de caracteres a serem adicionados para deixar a senha forte
    public int evaluateStrength(String password) {
      this.password = password;

      // quantos caracteres faltam para chegar no minimo
      minChangeLen = MIN_PASSWORD_LENGTH - password.length();
      minChangeLen = Math.max(0, minChangeLen);

      hasSpecialChar = false;
      hasUpperCase = false;
      hasLowerCase = false;
      hasDigit = false;
      // no maximo será preciso quatro caracteres a mais para deixar a senha forte
      // um caracter para cada condição acima
      charChanges = 4;

      for (char c : password.toCharArray()) {

        if (!hasSpecialChar && SPECIAL.indexOf(c) >= 0) {
          // Tem caracter special
          hasSpecialChar = true;
          charChanges -= 1;
        }

        if (!hasUpperCase && Character.isUpperCase(c)) {
          //Tem letra maiuscula
          hasUpperCase = true;
          charChanges -= 1;
        }

        if (!hasLowerCase && Character.isLowerCase(c)) {
          //Tem letra minuscula
          hasLowerCase = true;
          charChanges -= 1;
        }

        if (!hasDigit && Character.isDigit(c)) {
          //Tem digito
          hasDigit = true;
          charChanges -= 1;
        }

      }

      // A qtde minima de caracteres a ser adicionado é o maior dos dois erros
      neededAmount = Math.max(minChangeLen, charChanges);

      return neededAmount;
    }

  }
  
}