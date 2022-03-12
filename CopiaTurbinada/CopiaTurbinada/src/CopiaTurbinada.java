import java.util.ArrayList;
import java.util.List;

public class CopiaTurbinada {
	public static void main(String args[]) {
		List<String> conteudo = new ArrayList<>();
		String[]fields;
		String auxiliar;
		int i;

		/*****LEITURA DA ENTRADA******/
		if(args[1].contains("arquivo")) {
			if(args[2].contains("comprimido")) {
				if(args[3].contains("criptografado")) {//arquivo comprimido e criptografado
					fields = args[6].split("\\.");
					auxiliar = args[5] + " " + args[6];//passando linha com senha e nome do arquivo
					AnaliseInput analise = new CompactaDescompacta((AnaliseInput)new CriptografaDescriptografa
							(new LeituraEntradaArquivo()));
					conteudo = analise.leituraInput(auxiliar);
					CriaTxt.deletaArquivo(fields[0] + ".txt");
					CriaTxt.deletaArquivo(fields[0] + ".cript");
				}
				else {//arquivo comprimido
					fields = args[3].split("\\.");
					AnaliseInput analise = new CompactaDescompacta(new LeituraEntradaArquivo());
					conteudo = analise.leituraInput(fields[0]);
					CriaTxt.deletaArquivo(fields[0] + ".txt");//deleta txt sobrando
				}

			}
			else if(args[2].contains("criptografado")) {//arquivo criptografado
				fields = args[5].split("\\.");
				auxiliar = args[4] + " " + args[5];//passando linha com senha e nome do arquivo
				AnaliseInput analise = new CriptografaDescriptografa(new LeituraEntradaArquivo());
				conteudo = analise.leituraInput(auxiliar);
				CriaTxt.deletaArquivo(fields[0] + ".txt");
			}
			else {//arquivo
				fields = args[2].split("\\.");
				AnaliseInput analise = new LeituraEntradaArquivo();
				conteudo = analise.leituraInput(fields[0]);
			}

		}
		else{//teclado
			AnaliseInput analise = new LeituraEntradaTeclado();
			conteudo = analise.leituraInput(null);
		}
		/*****LEITURA DA SAIDA******/
		for(i = 0; i < args.length; i++) {
			if(args[i].contains("destino"))//acha a linha em que comeca a analise da saida
				break;
		}
		if(args[i+1].contains("arquivo")) {
			if(args[i+2].contains("comprimido")) {
				if(args[i+3].contains("criptografado")) {//arquivo comprimido e criptografado
					AnaliseOutput output = new CompactaDescompacta((AnaliseOutput)new CriptografaDescriptografa(new CopiaSaidaArquivo()));
					CopiaSaidaArquivo.setInfo(args[i+6]);//diz o nome do arquivo
					Decorador.setInfo(args[i+5] + " " + args[i+6]);
					output.copiaOutput(conteudo);


				}
				else {//arquivo comprimido
					AnaliseOutput output = new CompactaDescompacta(new CopiaSaidaArquivo());
					CopiaSaidaArquivo.setInfo(args[i+3]);//diz o nome do arquivo
					Decorador.setInfo(args[i+3]);
					output.copiaOutput(conteudo);
				}

			}
			else if (args[i+2].contains("criptografado")) {//arquivo criptografado
				AnaliseOutput output = new CriptografaDescriptografa(new CopiaSaidaArquivo());
				CopiaSaidaArquivo.setInfo(args[i+5]);//diz o nome do arquivo
				Decorador.setInfo(args[i+4] + " " + args[i+5]);//diz a senha e o nome do arquivo
				output.copiaOutput(conteudo);
			}
			else {//arquivo
				AnaliseOutput output = new CopiaSaidaArquivo();
				CopiaSaidaArquivo.setInfo(args[i+2]);
				output.copiaOutput(conteudo);
			}

		}
		else{//tela
			AnaliseOutput output = new CopiaSaidaTela();
			output.copiaOutput(conteudo);
		}
		System.out.println("Copia efetuada com sucesso!");
	}
}
