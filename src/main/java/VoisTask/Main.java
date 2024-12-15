package VoisTask;


public class Main {
    public static void main(String[] args) throws Exception{

        String FilePath = "C:\\TemplateMavenProject\\TaskData.xlsx";

        CalculateYearsHiring Calc = new CalculateYearsHiring();
        SortAndRemoveDuplicates SortingAndRemoving = new SortAndRemoveDuplicates();
        SendEmail sendEmail = new SendEmail();

        Calc.CalculateYears(FilePath);
        SortingAndRemoving.SortAndRemove();
        sendEmail.Send();
    }
}


