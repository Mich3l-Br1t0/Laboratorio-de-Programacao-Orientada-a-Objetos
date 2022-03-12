import java.util.List;

abstract class Decorador implements AnaliseInput, AnaliseOutput {
	
	protected final static String IV = "CHANGEPROTECTION";//garante integridade do arquivo
	protected AnaliseInput tempEntrada;
	protected AnaliseOutput tempSaida;
	protected static String informacao;
	
	public Decorador(AnaliseInput newEntrada) {
		tempEntrada = newEntrada;
	}
	public Decorador(AnaliseOutput newSaida) {
		tempSaida = newSaida;
	}
	
	public static void setInfo(String info) {
		informacao = info.replace("-", "");
	}
	
	public List<String> leituraInput(String arquivo) {
		return tempEntrada.leituraInput(arquivo);
	}
	
	public void copiaOutput(List<String> conteudo) {
		tempSaida.copiaOutput(conteudo);
	}
	

}
