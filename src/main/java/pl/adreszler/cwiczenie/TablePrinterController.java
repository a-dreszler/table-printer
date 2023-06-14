package pl.adreszler.cwiczenie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
class TablePrinterController {

    @PostMapping("/print")
    @ResponseBody
    String print(@RequestParam String headerrow,
                 @RequestParam String datarows) {
        String[] headerCells = headerrow.split(";");
        String[][] dataRows = Arrays.stream(datarows.split("\n+(\r+)?|\r+(\n+)?"))
                .map(row -> row.split(";"))
                .toArray(String[][]::new);

        if (isTableValid(headerCells, dataRows)) {
            Table table = new Table(headerCells, dataRows);
            return "<pre>" + table + "</pre>";
        }
        return "<h1>Tabela w nieprawidłowym formacie!</h1>";
    }

    private boolean isTableValid(String[] headerCells, String[][] dataRows) {
        return Arrays.stream(dataRows)
                .filter(row -> row.length == headerCells.length)
                .count() == dataRows.length;
    }
}
