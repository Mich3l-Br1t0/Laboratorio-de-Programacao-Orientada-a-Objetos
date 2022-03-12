import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.pdfbox.pdmodel.font.PDType1Font;

import rst.pdfbox.layout.elements.Document;
import rst.pdfbox.layout.elements.Paragraph;
import rst.pdfbox.layout.elements.render.VerticalLayoutHint;
import rst.pdfbox.layout.text.Alignment;
import rst.pdfbox.layout.text.BaseFont;

public class GeraPDF {
	public int geraPDF(String arqReceitas, String arqIngredientes) {
		try {
			Document document = new Document(40, 60, 40, 60);// cria documento
			ContaCaracteres num = new ContaCaracteres();
			BufferedReader lerArqReceitas = new BufferedReader(new FileReader(arqReceitas));
			BufferedReader lerArqIngredientes = new BufferedReader(new FileReader(arqIngredientes));
			Paragraph paragraph = new Paragraph();
			String linhaTxt = lerArqReceitas.readLine();//recebe a receita da respectiva linha
			paragraph.addMarkup("Receitas da Semana", 20, BaseFont.Courier);
			document.add(paragraph, new VerticalLayoutHint(Alignment.Center));

			lerArqIngredientes.mark(num.contaCaracteres(arqIngredientes));// numero necessario p poder retomar a leitura do arquivo

			while(linhaTxt != null) {
				BufferedReader lerReceita = new BufferedReader(new FileReader(linhaTxt));//ler a receita da respectiva linha
				String linhaIngredientes = lerArqIngredientes.readLine();
				String linhaReceita = lerReceita.readLine();
				paragraph = new Paragraph();//pular linha
				paragraph.addText(" ", 20, PDType1Font.COURIER_BOLD);
				document.add(paragraph);

				while(linhaReceita != null) {

					if(linhaReceita.isEmpty() != true) {
						if(linhaReceita.contains("Titulo")) {//adiciona o nome da receita
							paragraph = new Paragraph();
							String[]aux0 = linhaReceita.split(": ");
							paragraph.addText(aux0[1], 20, PDType1Font.COURIER_BOLD);
							document.add(paragraph, new VerticalLayoutHint(Alignment.Center));
							paragraph = new Paragraph();//pular linha
							paragraph.addText(" ", 20, PDType1Font.COURIER_BOLD);
							document.add(paragraph);
							linhaReceita = lerReceita.readLine();
						}
						if (linhaReceita.contains("Ingredientes") || linhaReceita.contains("Modo de preparo") || linhaReceita.contains("Modo de fazer")) {
							paragraph = new Paragraph();
							String[]aux1 = linhaReceita.split(":");
							paragraph.addText(aux1[0], 14, PDType1Font.COURIER_BOLD);
							document.add(paragraph,  new VerticalLayoutHint(Alignment.Center));
							paragraph = new Paragraph();//pular linha
							paragraph.addText(" ", 20, PDType1Font.COURIER_BOLD);
							document.add(paragraph);
							linhaReceita = lerReceita.readLine();
						}
						if(linhaReceita.contains(" - ")) {
							paragraph = new Paragraph();
							String[]aux2 = linhaReceita.split(" - ");//separa as partes da linha, so a parte a esquerda e necessaria 
							while(linhaIngredientes.contains(aux2[0]) == false) {
								linhaIngredientes = lerArqIngredientes.readLine();
								if(linhaIngredientes == null) {//para caso o ingrediente estiver numa posicao q ja passou, lembrando q se o programa chegou ate aqui
									lerArqIngredientes.reset();//quer dizer q todos os ingredientes estao persentes na lista de possiveis
									linhaIngredientes = lerArqIngredientes.readLine();
								}
							}
							String [] unidade = linhaIngredientes.split(" - ");
							paragraph.addText(linhaReceita + unidade[1], 14, PDType1Font.COURIER);
							document.add(paragraph);
							linhaReceita = lerReceita.readLine();
						}
						else {//analise da parte do modo de preparo
							paragraph = new Paragraph();
							paragraph.addText(linhaReceita, 14, PDType1Font.COURIER);
							document.add(paragraph);
							linhaReceita = lerReceita.readLine();
						}
					}
					else {//caso a linha seja vazia 
						paragraph = new Paragraph();
						paragraph.addText(" ", 14, PDType1Font.COURIER);//pula uma linha 
						document.add(paragraph,  new VerticalLayoutHint(Alignment.Center));
						linhaReceita = lerReceita.readLine();
					}
				}
				linhaTxt = lerArqReceitas.readLine();
				lerReceita.close();
			}
			lerArqIngredientes.close();
			lerArqReceitas.close();
			final OutputStream outputStream = new FileOutputStream("Receitas da Semana.pdf");
			document.save(outputStream);
			System.out.println("PDF criado com as receitas da semana");
			return 0;
		}
		catch(IOException e){
			//System.out.println("Erro ao ler arquivo " + e.getMessage());
			e.printStackTrace();
			return 1;
		}
	}
}
