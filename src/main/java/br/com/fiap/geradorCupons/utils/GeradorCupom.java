package br.com.fiap.geradorCupons.utils;

import br.com.fiap.geradorCupons.persistense.model.Item;
import br.com.fiap.geradorCupons.persistense.model.Pedidos;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeradorCupom {

	public static void generate(Pedidos pedido) {
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		if (pedido != null) {

				Document document = new Document();

				try {

					String fileName = "c:\\cupons\\cupom_" + pedido.getId() + ".pdf";
					PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
					document.open();

					// adicionando um paragrafo no documento
					Paragraph nm = new Paragraph("FIAP ROUPAS");
					nm.setAlignment(Element.ALIGN_CENTER);
					document.add(nm);
					Paragraph addr = new Paragraph("Av. Lins de Vasconcelos, 383 - Aclimação");
					addr.setAlignment(Element.ALIGN_CENTER);
					document.add(addr);
					Paragraph stt = new Paragraph("São Paulo / SP");
					stt.setAlignment(Element.ALIGN_CENTER);
					document.add(stt);

					document.add(new Paragraph("CNPJ: 11.319.526/0001-55"));
					document.add(new Paragraph("IE: 222.222.222.222"));
					document.add(new Paragraph("IM: 3.333.333-3"));

					document.add(new Paragraph(" "));
					DottedLineSeparator dottedline = new DottedLineSeparator();
					document.add(dottedline);
					document.add(new Paragraph(" "));

					PdfPTable table = new PdfPTable(4);
					PdfPCell cell = new PdfPCell(new Phrase(pedido.getData().format(formatters)));
					cell.setBorderColor(BaseColor.WHITE);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase("10:01:01"));
					cell.setBorderColor(BaseColor.WHITE);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase("CCF: 010333"));
					cell.setBorderColor(BaseColor.WHITE);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase("COO: " + pedido.getId()));
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setBorderColor(BaseColor.WHITE);
					table.addCell(cell);
					table.setWidthPercentage(100);
					document.add(table);

					document.add(new Paragraph(" "));
					document.add(new Paragraph("CUPOM FISCAL"));
					document.add(new Paragraph(" "));

					Font tblheadfont = new Font();
					tblheadfont.setSize(8.5f);

					PdfPTable tbh = new PdfPTable(9);
					tbh.setWidths(new float[] { 5, 10, 40, 5, 5, 15, 5, 5, 10 });

					PdfPCell itemCell = new PdfPCell(new Phrase("ITEM", tblheadfont));
					itemCell.setBorderColor(BaseColor.WHITE);
					itemCell.setBorderColorBottom(BaseColor.BLACK);
					tbh.addCell(itemCell);

					PdfPCell codCell = new PdfPCell(new Phrase("CODIGO", tblheadfont));
					codCell.setBorderColor(BaseColor.WHITE);
					codCell.setBorderColorBottom(BaseColor.BLACK);
					tbh.addCell(codCell);

					PdfPCell desCell = new PdfPCell(new Phrase("DESCRICAO", tblheadfont));
					desCell.setBorderColor(BaseColor.WHITE);
					desCell.setBorderColorBottom(BaseColor.BLACK);
					tbh.addCell(desCell);

					PdfPCell qtCell = new PdfPCell(new Phrase("QTD.", tblheadfont));
					qtCell.setBorderColor(BaseColor.WHITE);
					qtCell.setBorderColorBottom(BaseColor.BLACK);
					tbh.addCell(qtCell);

					PdfPCell unCell = new PdfPCell(new Phrase("UN.", tblheadfont));
					unCell.setBorderColor(BaseColor.WHITE);
					unCell.setBorderColorBottom(BaseColor.BLACK);
					tbh.addCell(unCell);

					PdfPCell vluCell = new PdfPCell(new Phrase("VL UNIT.(R$)", tblheadfont));
					vluCell.setBorderColor(BaseColor.WHITE);
					vluCell.setBorderColorBottom(BaseColor.BLACK);
					tbh.addCell(vluCell);

					PdfPCell stCell = new PdfPCell(new Phrase("ST", tblheadfont));
					stCell.setBorderColor(BaseColor.WHITE);
					stCell.setBorderColorBottom(BaseColor.BLACK);
					tbh.addCell(stCell);

					PdfPCell vlCell = new PdfPCell(new Phrase("VL", tblheadfont));
					vlCell.setBorderColor(BaseColor.WHITE);
					vlCell.setBorderColorBottom(BaseColor.BLACK);
					tbh.addCell(vlCell);

					PdfPCell itCell = new PdfPCell(new Phrase("ITEM(R$)", tblheadfont));
					itCell.setBorderColor(BaseColor.WHITE);
					itCell.setBorderColorBottom(BaseColor.BLACK);
					tbh.addCell(itCell);

					tbh.setWidthPercentage(100);
					document.add(tbh);

					LineSeparator ls = new LineSeparator();
					document.add(ls);

					// loop items
					int i = 1;
					double granTotal = 0;
					for (Item item : pedido.getItens()) {

						PdfPTable tbhi = new PdfPTable(9);
						tbhi.setWidths(new float[] { 5, 10, 40, 5, 5, 15, 5, 5, 10 });

						PdfPCell cell7 = new PdfPCell(new Phrase(i + "", tblheadfont));
						cell7.setBorderColor(BaseColor.WHITE);
						tbhi.addCell(cell7);

						PdfPCell cell8 = new PdfPCell(new Phrase(item.getId() + "", tblheadfont));
						cell8.setBorderColor(BaseColor.WHITE);
						tbhi.addCell(cell8);

						PdfPCell cell9 = new PdfPCell(new Phrase(item.getDescricao(), tblheadfont));
						cell9.setBorderColor(BaseColor.WHITE);
						tbhi.addCell(cell9);

						PdfPCell cell1 = new PdfPCell(new Phrase(item.getQuantidade() + "", tblheadfont));
						cell1.setBorderColor(BaseColor.WHITE);
						tbhi.addCell(cell1);

						PdfPCell cell2 = new PdfPCell(new Phrase(" ", tblheadfont));
						cell2.setBorderColor(BaseColor.WHITE);
						tbhi.addCell(cell2);

						PdfPCell cell3 = new PdfPCell(new Phrase(String.format("%.2f", item.getValor()), tblheadfont));
						cell3.setBorderColor(BaseColor.WHITE);
						tbhi.addCell(cell3);

						PdfPCell cell4 = new PdfPCell(new Phrase(" ", tblheadfont));
						cell4.setBorderColor(BaseColor.WHITE);
						tbhi.addCell(cell4);

						PdfPCell cell5 = new PdfPCell(new Phrase(" ", tblheadfont));
						cell5.setBorderColor(BaseColor.WHITE);
						tbhi.addCell(cell5);

						PdfPCell cell6 = new PdfPCell(new Phrase(" ", tblheadfont));
						cell6.setBorderColor(BaseColor.WHITE);
						tbhi.addCell(cell6);

						tbhi.setWidthPercentage(100);
						document.add(tbhi);
						i++;
					}

					// display total
					document.add(new Paragraph(" "));
					ls.setPercentage(50);
					ls.setAlignment(LineSeparator.ALIGN_RIGHT);
					document.add(ls);
					PdfPTable total = new PdfPTable(2);

					PdfPCell strTo = new PdfPCell(new Phrase("TOTAL R$"));
					strTo.setBorderColor(BaseColor.WHITE);
					total.addCell(strTo);

					PdfPCell to = new PdfPCell(new Phrase(String.format("%.2f", pedido.getValor())));
					to.setHorizontalAlignment(Element.ALIGN_RIGHT);
					to.setBorderColor(BaseColor.WHITE);
					total.addCell(to);

					total.setWidthPercentage(100);
					document.add(total);

					// HASH
					document.add(new Paragraph(" "));
					String chv = pedido.getId() + pedido.getValor() + "";
					String hsh = GeraHash.gera(chv);
					document.add(new Paragraph(hsh));

					// dados da empresa
					document.add(new Paragraph(" "));
					document.add(new Paragraph("FIAP ROUPAS S/A MF. 4000 178 M1 ECF42"));
					document.add(new Paragraph("VERSÃO 01.00.01	ECF: 8233	LJ: 8233"));
					document.add(new Paragraph("QQQQQQQQQQQRTV 12/06/2018	20:01:56"));
					document.add(new Paragraph("FAB: BE000000000000000000000000 00"));

					// copyright
					document.addSubject("Framework e utilitários");
					document.addKeywords("www.fiap.com.br");
					document.addCreator("by 30SCJ");
					document.addAuthor("Alunos - Marli Lucena, Lucas Alexandre, Tatiane dos Santos");

				} catch (DocumentException de) {
					System.err.println(de.getMessage());
				} catch (IOException ioe) {
					System.err.println(ioe.getMessage());
				}
				document.close();		

		}
	}

}
