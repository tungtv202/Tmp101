import java.util.UUID;

import org.junit.Test;

import lombok.Getter;

public class IsolationTest {
    private final Tung testee = new Tung();

    public static final String test = "{" +
        "  \"glossary\": {" +
        "    \"title\": \"example glossary\"," +
        "    \"GlossDiv\": {" +
        "      \"title\": \"S\"," +
        "      \"GlossList\": {" +
        "        \"GlossEntry\": {" +
        "          \"ID\": \"SGML\"," +
        "          \"SortAs\": \"SGML\"," +
        "          \"GlossTerm\": \"Standard Generalized Markup Language\"," +
        "          \"Acronym\": \"SGML\"," +
        "          \"Abbrev\": \"ISO 8879:1986\"," +
        "          \"GlossDef\": {" +
        "            \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\"," +
        "            \"GlossSeeAlso\": [" +
        "              \"GML\"," +
        "              \"XML\"" +
        "            ]" +
        "          }," +
        "          \"GlossSee\": \"markup\"" +
        "        }" +
        "      }" +
        "    }" +
        "  }" +
        '}';

    @Test
    public void test1() {
        System.out.println(testee);
    }

    @Test
    public  void test2(){
        System.out.println(testee);
    }

    String berry = "blue";

    public static void main(String[] args) {

    }

    void juicy(String berry) {

    }

}


@Getter
class Tung {
    private String value;
    public Tung() {
        this.value = UUID.randomUUID().toString();
    }
}