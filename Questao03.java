import java.util.HashMap;
import java.util.ArrayList;

public class Questao03 {

  public static void main() {

    test("ovo");
    test("ifailuhkqq");
    test("lazuli");
    test("abcdef");

  }

  // Teste simples para imprimir qtde de anagramas e quais são os anagramas para a string passada
  public static void test(String string) {
    var list = listAnagrams(string);
    System.out.println(string + ": " + list.size() + " anagramas " + list);
  }

  // Classe que representa um anagrama encontrado
  public static class Anagram {

    // substrings que são anagramas entre si
    public String sub1;
    public String sub2;

    public Anagram(String sub1, String sub2) {
      this.sub1 = sub1;
      this.sub2 = sub2;
    }

    public String toString() {
      return sub1 + " - " + sub2;
    }
  }

  // Lista todos as substrings que são anagramas entre si
  // retorna a lista com os pares de substrings
  public static ArrayList<Anagram> listAnagrams(String string) {

    int len = string.length();

    // computar todas a substrings possiveis 
    ArrayList<String> substrings = new ArrayList<String>();
    for (int start = 0; start < len; start++) {
      for (int end = start+1; end < len+1; end++) {
        String sub = string.substring(start, end);
        substrings.add(sub);
      }
    }

    // listar os diferentes pares de substrings que são anagramas
    ArrayList<Anagram> anagrams = new ArrayList<Anagram>();
    for (int i = 0; i < substrings.size(); i++) {
      for (int j = i+1; j < substrings.size(); j++) {
        String sub1 = substrings.get(i);
        String sub2 = substrings.get(j);
        if (isAnagram(sub1, sub2)) {
          anagrams.add(new Anagram(sub1, sub2));
        }
      }
    }
    return anagrams;
  }

  // contar ocorrências de cada caracter na string passada
  // retorna um mapa associando cada caracter encontrado com a quantidade correspondente
  public static HashMap<Character, Integer> countLetters(String str) {
    HashMap<Character, Integer> count = new HashMap<Character, Integer>();
    for (Character c : str.toCharArray()) {
      count.put(c, count.getOrDefault(c, 0) + 1);
    }
    return count;
  }

  // Testa se duas strings são anagramas
  public static boolean isAnagram(String str1, String str2) {
    HashMap<Character, Integer> count1 = countLetters(str1);
    HashMap<Character, Integer> count2 = countLetters(str2);
    return count1.equals(count2);
  }

}