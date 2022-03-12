import java.util.List;

public class CopiaSaidaArquivo implements AnaliseOutput {
	
	private static String informacao;
	
	public static void setInfo(String info) {
		informacao = info.replace("-", "");
	}
	
	public void copiaOutput(List<String> conteudo) {//copia base sempre estara presente
		String[] nome = informacao.split("\\.");
		CriaTxt.setNomeArquivo(nome[0]);//recebe nome do arquivo a ser modificado
		CriaTxt.escritor(conteudo);//modifica o arquivo desejado
	}

}
