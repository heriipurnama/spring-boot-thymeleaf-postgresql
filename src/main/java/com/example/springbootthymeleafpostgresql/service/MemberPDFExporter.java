package com.example.springbootthymeleafpostgresql.service;

import com.example.springbootthymeleafpostgresql.model.Member;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberPDFExporter {
    private List<Member> members;

    public MemberPDFExporter(List<Member> members) {
        this.members = members;
    }

    public void export(HttpServletResponse response) throws IOException {
        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        Table table = new Table(UnitValue.createPercentArray(new float[]{1, 3, 3, 2})).useAllAvailableWidth();

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();
    }

    private void writeTableHeader(Table table) {
        table.addHeaderCell(new Cell().add("ID").setTextAlignment(TextAlignment.CENTER));
        table.addHeaderCell(new Cell().add("Name").setTextAlignment(TextAlignment.CENTER));
        table.addHeaderCell(new Cell().add("Email").setTextAlignment(TextAlignment.CENTER));
        table.addHeaderCell(new Cell().add("Date of Birth").setTextAlignment(TextAlignment.CENTER));
    }

    private void writeTableData(Table table) {
        for (Member member : members) {
            table.addCell(new Cell().add(String.valueOf(member.getId())));
            table.addCell(new Cell().add(member.getName()));
            table.addCell(new Cell().add(member.getEmail()));
            table.addCell(new Cell().add(member.getDob().toString()));
        }
    }
}
