import java.util.List;

public class CopiaSaidaTela implements AnaliseOutput {

	public void copiaOutput(List<String> conteudo) {
		for(String linha: conteudo) {
			System.out.println(linha);
		}
	}

}
