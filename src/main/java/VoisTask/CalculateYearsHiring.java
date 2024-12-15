package VoisTask;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculateYearsHiring {

    static public Set<Map.Entry<String, String>> MonthMap(){
        Map<String, String> monthMap = new HashMap<>();
        monthMap.put("January", ",01-");
        monthMap.put("February", ",02-");
        monthMap.put("March", ",03-");
        monthMap.put("April", ",04-");
        monthMap.put("May", ",05-");
        monthMap.put("June", ",06-");
        monthMap.put("July", ",07-");
        monthMap.put("August", ",08-");
        monthMap.put("September", ",09-");
        monthMap.put("October", ",10-");
        monthMap.put("November", ",11-");
        monthMap.put("December", ",12-");
        return monthMap.entrySet();
    }

    public void CalculateYears(String FilePath) throws IOException {
        //Set and Map of Months and days.
        String[] DaysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        //get today date.
        LocalDate TodayDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        //Open The file
        try (
            FileInputStream fileInputStream = new FileInputStream(FilePath); Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            Sheet ExcelSheet = workbook.getSheet("Sheet1");
            //Pattern to match the required data
            Pattern DatePattern = Pattern.compile("\\w+,\\s\\w+\\s\\d+,\\s\\d+");
            //review the data and then handle it.
            for (Row row : ExcelSheet) {
                for (Cell cell : row) {
                    Matcher DateMatcher = DatePattern.matcher(cell.toString());
                    if (DateMatcher.find()) {
                        String DateInCell = DateMatcher.group();
                        if (cell.toString().contains(DateInCell)) {
                            for (String Day : DaysOfWeek) {
                                if (DateInCell.contains(Day)) {
                                    for (Map.Entry<String, String> NewMap : CalculateYearsHiring.MonthMap()) {
                                        String MonthName = NewMap.getKey();
                                        String MonthNumber = NewMap.getValue();
                                        if (DateInCell.contains(MonthName)) {
                                            String CleanDate = DateInCell.replace(Day, "").
                                                    replace(MonthName, MonthNumber).
                                                    replace(" ", "").
                                                    replace(", ", "").
                                                    replace(",,", "").
                                                    replace(",", "-");
                                            LocalDate HiringDate = LocalDate.parse(CleanDate, dateTimeFormatter);
                                            Period period = Period.between(HiringDate, TodayDate);
                                            Cell NextCell = row.createCell(cell.getColumnIndex() + 1);
                                            if (NextCell != null) {
                                                NextCell.setCellValue(period.getYears() + " years");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //update the file
            FileOutputStream fileOutputStream = new FileOutputStream(FilePath);
            workbook.write(fileOutputStream);
        }
    }
}
