package VoisTask;
import org.sikuli.script.*;



public class SortAndRemoveDuplicates {

    public void SortAndRemove() throws Exception {

        Screen PcScreen = new Screen();

        PcScreen.type(Key.ALT + "F4");
        Pattern EnableEditing = new Pattern("C:\\TemplateMavenProject\\img\\EnableEdit.png");
        Pattern SearchButton = new Pattern("C:\\TemplateMavenProject\\img\\SearchButton.png").similar(0.10);
        Pattern ColumnD = new Pattern("C:\\TemplateMavenProject\\img\\Column.png").similar(0.2);
        Pattern SortCells = new Pattern("C:\\TemplateMavenProject\\img\\SortandFilter.png");
        Pattern DescendingSort = new Pattern("C:\\TemplateMavenProject\\img\\SortAtoZ.png");
        Pattern ColumnB = new Pattern("C:\\TemplateMavenProject\\img\\BColumn.png").similar(0.2);
        Pattern ColumnB1 = new Pattern("C:\\TemplateMavenProject\\img\\BColumn1.png").similar(0.2);
        Pattern AroundName = new Pattern("C:\\TemplateMavenProject\\img\\NameButton.png");
        Pattern NameButton = new Pattern("C:\\TemplateMavenProject\\img\\Name.png");
        Pattern ExitButton = new Pattern("C:\\TemplateMavenProject\\img\\exitButton.png");


        PcScreen.click(SearchButton);
        Thread.sleep(1000);
        PcScreen.type("cmd");
        Thread.sleep(1000);
        PcScreen.type(Key.ENTER);
        Thread.sleep(2000);
        PcScreen.type("C:\\TemplateMavenProject\\TaskData.xlsx");
        PcScreen.type(Key.ENTER);

        if(PcScreen.exists(EnableEditing) != null){
            PcScreen.click(EnableEditing);
        }
        Thread.sleep(10000);
        PcScreen.click(ColumnD);
        PcScreen.click(SortCells);
        PcScreen.click(DescendingSort);
        PcScreen.type(Key.ENTER);
        if(PcScreen.exists(ColumnB) != null) {
            PcScreen.click(ColumnB);
        }
        if(PcScreen.exists(ColumnB1) != null) {
            PcScreen.click(ColumnB1);
        }
        PcScreen.type(Key.ALT);
        PcScreen.type("A");
        PcScreen.type("M");
        Thread.sleep(1000);
        PcScreen.type(Key.ENTER);
        Thread.sleep(10000);
        PcScreen.type(Key.TAB);
        PcScreen.type(Key.ENTER);
        PcScreen.click(AroundName);
        PcScreen.click(NameButton);
        PcScreen.type(Key.ENTER);
        PcScreen.type(Key.ENTER);
        Thread.sleep(1000);
        PcScreen.click(ExitButton);
        Thread.sleep(1000);
        PcScreen.type(Key.ENTER);

    }
}
